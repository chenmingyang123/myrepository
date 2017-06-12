package com.guangxunet.shop.base.service;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月9日 下午11:50:41 
* 类说明 阿里云短信发送示例
*/
public class BatchPublishSMSMessageDemo {
	private static String YourAccessId = "LTAI2bGnu9lbWKE2";
	private static String YourAccessKey = "mLB2NOwUgZHcpFPNgBNyRAPMrCUbNB";
	private static String YourMNSEndpoint = "https://1195938657583057.mns.cn-hangzhou.aliyuncs.com/";
	private static String YourTopic = "sms.topic-cn-hangzhou";
	private static String YourSignName = "光续云购APP";
	private static String YourSMSTemplateCode = "SMS_70545298";
	
	public static void main(String[] args) {
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
