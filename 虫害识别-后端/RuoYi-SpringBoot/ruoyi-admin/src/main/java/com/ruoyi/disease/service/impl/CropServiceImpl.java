package com.ruoyi.disease.service.impl;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.disease.mapper.CropMapper;
import com.ruoyi.disease.domain.Crop;
import com.ruoyi.disease.service.ICropService;

/**
 * 农作物管理Service业务层处理
 * 
 * @author yzx
 * @date 2025-01-18
 */
@Service
public class CropServiceImpl implements ICropService
{
    @Autowired
    private CropMapper cropMapper;

    /**
     * 查询农作物管理
     * 
     * @param cropId 农作物管理主键
     * @return 农作物管理
     */
    @Override
    public Crop selectCropByCropId(Integer cropId)
    {
        return cropMapper.selectCropByCropId(cropId);
    }

    /**
     * 查询农作物管理列表
     * 
     * @param crop 农作物管理
     * @return 农作物管理
     */
    @Override
    public List<Crop> selectCropList(Crop crop)
    {
        return cropMapper.selectCropList(crop);
    }

    /**
     * 新增农作物管理
     * 
     * @param crop 农作物管理
     * @return 结果
     */
    @Override
    public int insertCrop(Crop crop)
    {
        return cropMapper.insertCrop(crop);
    }

    /**
     * 修改农作物管理
     * 
     * @param crop 农作物管理
     * @return 结果
     */
    @Override
    public int updateCrop(Crop crop)
    {
        return cropMapper.updateCrop(crop);
    }

    /**
     * 批量删除农作物管理
     * 
     * @param cropIds 需要删除的农作物管理主键
     * @return 结果
     */
    @Override
    public int deleteCropByCropIds(Integer[] cropIds)
    {
        return cropMapper.deleteCropByCropIds(cropIds);
    }

    /**
     * 删除农作物管理信息
     * 
     * @param cropId 农作物管理主键
     * @return 结果
     */
    @Override
    public int deleteCropByCropId(Integer cropId)
    {
        return cropMapper.deleteCropByCropId(cropId);
    }


}
