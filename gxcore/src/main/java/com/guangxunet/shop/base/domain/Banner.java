package com.guangxunet.shop.base.domain;

public class Banner {
    private Integer id;

    private String imagePath;

    private String type;

    private String adUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl == null ? null : adUrl.trim();
    }

	@Override
	public String toString() {
		return "Banner [id=" + id + ", imagePath=" + imagePath + ", type=" + type + ", adUrl=" + adUrl + "]";
	}
    
}