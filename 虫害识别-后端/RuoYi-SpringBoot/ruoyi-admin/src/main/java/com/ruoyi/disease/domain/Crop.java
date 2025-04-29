package com.ruoyi.disease.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 农作物管理对象 t_crop
 * 
 * @author yzx
 * @date 2025-01-18
 */
public class Crop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作物ID */
    private Integer cropId;

    /** 作物名称 */
    @Excel(name = "作物名称")
    private String cropName;

    /** 封面图 */
    @Excel(name = "封面图")
    private String imgUrl;

    public void setCropId(Integer cropId) 
    {
        this.cropId = cropId;
    }

    public Integer getCropId() 
    {
        return cropId;
    }
    public void setCropName(String cropName) 
    {
        this.cropName = cropName;
    }

    public String getCropName() 
    {
        return cropName;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cropId", getCropId())
            .append("cropName", getCropName())
            .append("imgUrl", getImgUrl())
            .toString();
    }
}
