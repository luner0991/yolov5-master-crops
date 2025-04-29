package com.ruoyi.disease.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 识别记录对象 t_result
 * 
 * @author yzx
 * @date 2025-01-17
 */
public class Result extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Integer resultId;

    /** 使用UUID来命名 */
    private String pictureName;

    /** 病虫害名称 */
    @Excel(name = "病虫害名称")
    private String diseaseName;

    /** 病虫害ID */
    private Long diseaseId;

    /** 识别结果 */
    @Excel(name = "识别结果")
    private String result;

    /** 状态 */
    private String status;

    /** 上传图片 */
    @Excel(name = "上传图片")
    private String imgPath;

    /** 识别时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "识别时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 病虫害管理信息 */
    private List<Disease> diseaseList;

    public void setResultId(Integer resultId) 
    {
        this.resultId = resultId;
    }

    public Integer getResultId() 
    {
        return resultId;
    }
    public void setPictureName(String pictureName) 
    {
        this.pictureName = pictureName;
    }

    public String getPictureName() 
    {
        return pictureName;
    }
    public void setDiseaseName(String diseaseName) 
    {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() 
    {
        return diseaseName;
    }
    public void setDiseaseId(Long diseaseId) 
    {
        this.diseaseId = diseaseId;
    }

    public Long getDiseaseId() 
    {
        return diseaseId;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setImgPath(String imgPath) 
    {
        this.imgPath = imgPath;
    }

    public String getImgPath() 
    {
        return imgPath;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    public List<Disease> getDiseaseList()
    {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList)
    {
        this.diseaseList = diseaseList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resultId", getResultId())
            .append("pictureName", getPictureName())
            .append("diseaseName", getDiseaseName())
            .append("diseaseId", getDiseaseId())
            .append("result", getResult())
            .append("status", getStatus())
            .append("imgPath", getImgPath())
            .append("uploadTime", getUploadTime())
            .append("diseaseList", getDiseaseList())
            .toString();
    }
}
