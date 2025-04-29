package com.ruoyi.disease.domain;

public class IdentifyVO {
    private String identifyResult;
    private String diseaseName;


    public IdentifyVO() {
    }

    public IdentifyVO(String identifyResult, String diseaseName) {
        this.identifyResult = identifyResult;
        this.diseaseName = diseaseName;
    }

    /**
     * 获取
     * @return identifyResult
     */
    public String getIdentifyResult() {
        return identifyResult;
    }

    /**
     * 设置
     * @param identifyResult
     */
    public void setIdentifyResult(String identifyResult) {
        this.identifyResult = identifyResult;
    }

    /**
     * 获取
     * @return diseaseName
     */
    public String getDiseaseName() {
        return diseaseName;
    }

    /**
     * 设置
     * @param diseaseName
     */
    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String toString() {
        return "IdentifyVO{identifyResult = " + identifyResult + ", diseaseName = " + diseaseName + "}";
    }
}
