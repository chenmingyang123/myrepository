package com.guangxunet.shop.base.service.impl;


import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.guangxunet.shop.base.service.IVerifyCodeService;
import com.guangxunet.shop.base.util.BidConst;
import com.guangxunet.shop.base.util.DateUtil;
import com.guangxunet.shop.base.util.UserContext;
import com.guangxunet.shop.base.vo.VerifyCodeVO;

/**验证码服务实现类
 * Created by King on 2016/10/11.
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {
    @Value("${sms.url}")//简单值注入方式：属性文件中配置了url=http://localhost:8082/send.do
    private String url;

    @Value("${sms.username}")//sms.username=xmg
    private String username;

    @Value("${sms.password}")//sms.password=1111
    private String password;

    @Value("${sms.apikey}")//sms.apikey=1111
    private String apiKey;

    @Value("${alicloud.YourAccessId}")//账号
    private String YourAccessId;
    
    @Value("${alicloud.YourAccessKey}")//秘钥
    private String YourAccessKey;
    
    @Value("${alicloud.YourMNSEndpoint}")//url
    private String YourMNSEndpoint;
    
    @Value("${alicloud.YourTopic}")//主题
    private String YourTopic;
    
    @Value("${alicloud.YourSignName}")//签名
    private String YourSignName;
    
    @Value("${alicloud.YourSMSTemplateCode}")//短信模板
    private String YourSMSTemplateCode;
    
    @Override
    public boolean verifyCode(String phoneNumber, String verifyCode) {
        VerifyCodeVO vc =UserContext.getVerifyCode();
        System.out.println("===VerifyCodeVO==session="+vc);
        return vc!=null//发过短信
                && vc.getPhoneNumber().equals(phoneNumber)//并且手机号和之前输入的一样
                && vc.getCode().equals(verifyCode)//并且验证么相等
                && DateUtil.getBetweenSecond(vc.getSendTime(),new Date()) <= 60*1;//并且验证码没有过期，全部满足时返回true
    }


    public void sendVerifyCode(String phoneNumber) throws Exception{
        //得到session中的verifyVo
        VerifyCodeVO vo = UserContext.getVerifyCode();
        if (vo == null || (vo != null && DateUtil.getBetweenSecond(vo.getSendTime(), new Date()) >= BidConst.SEND_VERIFY_INTERVAL)) {
            //生产一个验证码
            String code = UUID.randomUUID().toString().substring(0, 4);
            System.out.println("验证码是 "+code+"，打死也不能告诉别人哦！【阿里巴巴】");
            
            vo = new VerifyCodeVO(code, phoneNumber, new Date());
            //把vo放到session中
            UserContext.putVerifyCode(vo);
            
            // 发送短信
            try {
                // 创建一个URL对象
                URL url = new URL(this.url);
                // 通过URL得到一个HTTPURLConnection连接对象;
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 拼接POST请求的内容
                StringBuilder content = new StringBuilder(100)
                        .append("username=").append(username)
                        .append("&password=").append(password)
                        .append("&apikey=").append(apiKey).append("&mobile=")
                        .append(phoneNumber).append("&content=")
                        .append("验证码是:").append(code).append(",有效时间为")
                        .append(BidConst.SEND_VERIFY_INTERVAL).append("秒");
                // 发送POST请求,POST或者GET一定要大写
                conn.setRequestMethod("POST");
                // 设置POST请求是有请求体的
                conn.setDoOutput(true);
                // 写入POST请求体
                conn.getOutputStream().write(content.toString().getBytes());
                // 得到响应流(其实就已经发送了)
                String response = StreamUtils.copyToString(
                        conn.getInputStream(), Charset.forName("UTF-8"));
                if (response.startsWith("success")) {
                    // 发送成功
                    // 把手机号码,验证码,发送时间装配到VO中并保存到session
                    vo = new VerifyCodeVO(code, phoneNumber, new Date());
                    //把vo放到session中
                    UserContext.putVerifyCode(vo);
                } else {
                    // 发送失败
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                throw new RuntimeException("短信发送失败!");
            }
        } else {
            throw new RuntimeException("发送过于频繁!");
        }

    }


    /**
     * 批量发送短信 
     */
	@Override
	public void batchPublishSMSMessage() {
		/**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(YourAccessId, YourAccessKey, YourMNSEndpoint);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef(YourTopic);
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName(YourSignName);
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode(YourSMSTemplateCode);
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("name", "耿术强");
        smsReceiverParams.setParam("code", "736221");
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver("18211674995", smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
	}
}
