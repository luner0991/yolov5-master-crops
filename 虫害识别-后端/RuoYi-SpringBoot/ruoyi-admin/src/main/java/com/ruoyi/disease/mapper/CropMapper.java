package com.ruoyi.disease.mapper;

import java.util.List;
import com.ruoyi.disease.domain.Crop;

/**
 * 农作物管理Mapper接口
 * 
 * @author yzx
 * @date 2025-01-18
 */
public interface CropMapper 
{
    /**
     * 查询农作物管理
     * 
     * @param cropId 农作物管理主键
     * @return 农作物管理
     */
    public Crop selectCropByCropId(Integer cropId);

    /**
     * 查询农作物管理列表
     * 
     * @param crop 农作物管理
     * @return 农作物管理集合
     */
    public List<Crop> selectCropList(Crop crop);

    /**
     * 新增农作物管理
     * 
     * @param crop 农作物管理
     * @return 结果
     */
    public int insertCrop(Crop crop);

    /**
     * 修改农作物管理
     * 
     * @param crop 农作物管理
     * @return 结果
     */
    public int updateCrop(Crop crop);

    /**
     * 删除农作物管理
     * 
     * @param cropId 农作物管理主键
     * @return 结果
     */
    public int deleteCropByCropId(Integer cropId);

    /**
     * 批量删除农作物管理
     * 
     * @param cropIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCropByCropIds(Integer[] cropIds);
}
