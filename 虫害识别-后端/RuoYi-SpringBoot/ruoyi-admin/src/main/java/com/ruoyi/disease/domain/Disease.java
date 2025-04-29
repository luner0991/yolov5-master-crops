package com.ruoyi.disease.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病虫害管理对象 t_disease
 * 
 * @author yzx
 * @date 2025-01-16
 */
public class Disease extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Integer diseaseId;

    /** 病害名称 */
    @Excel(name = "病害名称")
    private String diseaseName;

    /** 病害介绍 */
    @Excel(name = "病害介绍")
    private String introducement;

    /** 解决方法 */
    @Excel(name = "解决方法")
    private String resolution;

    /** 预防措施 */
    @Excel(name = "预防措施")
    private String prevention;

    /** 农作物类型 */
    @Excel(name = "农作物类型")
    private String croptype;

    public void setDiseaseId(Integer diseaseId) 
    {
        this.diseaseId = diseaseId;
    }

    public Integer getDiseaseId() 
    {
        return diseaseId;
    }
    public void setDiseaseName(String diseaseName) 
    {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() 
    {
        return diseaseName;
    }
    public void setIntroducement(String introducement) 
    {
        this.introducement = introducement;
    }

    public String getIntroducement() 
    {
        return introducement;
    }
    public void setResolution(String resolution) 
    {
        this.resolution = resolution;
    }

    public String getResolution() 
    {
        return resolution;
    }
    public void setPrevention(String prevention) 
    {
        this.prevention = prevention;
    }

    public String getPrevention() 
    {
        return prevention;
    }
    public void setCroptype(String croptype) 
    {
        this.croptype = croptype;
    }

    public String getCroptype() 
    {
        return croptype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("diseaseId", getDiseaseId())
            .append("diseaseName", getDiseaseName())
            .append("introducement", getIntroducement())
            .append("resolution", getResolution())
            .append("prevention", getPrevention())
            .append("croptype", getCroptype())
            .toString();
    }
}
