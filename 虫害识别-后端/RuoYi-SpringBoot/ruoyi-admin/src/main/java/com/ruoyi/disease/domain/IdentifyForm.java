package com.ruoyi.disease.domain;

public class IdentifyForm {
    private Integer cropId;     //农作物id
    private String extName;     //图片扩展名
    private String fileBase64;  //Base64编码的图片


    public IdentifyForm() {
    }

    public IdentifyForm(Integer cropId, String extName, String fileBase64) {
        this.cropId = cropId;
        this.extName = extName;
        this.fileBase64 = fileBase64;
    }

    /**
     * 获取
     * @return cropId
     */
    public Integer getCropId() {
        return cropId;
    }

    /**
     * 设置
     * @param cropId
     */
    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    /**
     * 获取
     * @return extName
     */
    public String getExtName() {
        return extName;
    }

    /**
     * 设置
     * @param extName
     */
    public void setExtName(String extName) {
        this.extName = extName;
    }

    /**
     * 获取
     * @return fileBase64
     */
    public String getFileBase64() {
        return fileBase64;
    }

    /**
     * 设置
     * @param fileBase64
     */
    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String toString() {
        return "IdentifyForm{cropId = " + cropId + ", extName = " + extName + ", fileBase64 = " + fileBase64 + "}";
    }
}
