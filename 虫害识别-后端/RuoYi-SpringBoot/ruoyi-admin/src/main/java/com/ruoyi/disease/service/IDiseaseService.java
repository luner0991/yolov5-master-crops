package com.ruoyi.disease.service;

import java.util.List;
import com.ruoyi.disease.domain.Disease;

/**
 * 病虫害管理Service接口
 * 
 * @author yzx
 * @date 2025-01-16
 */
public interface IDiseaseService 
{
    /**
     * 查询病虫害管理
     * 
     * @param diseaseId 病虫害管理主键
     * @return 病虫害管理
     */
    public Disease selectDiseaseByDiseaseId(Integer diseaseId);

    /**
     * 查询病虫害管理列表
     * 
     * @param disease 病虫害管理
     * @return 病虫害管理集合
     */
    public List<Disease> selectDiseaseList(Disease disease);

    /**
     * 新增病虫害管理
     * 
     * @param disease 病虫害管理
     * @return 结果
     */
    public int insertDisease(Disease disease);

    /**
     * 修改病虫害管理
     * 
     * @param disease 病虫害管理
     * @return 结果
     */
    public int updateDisease(Disease disease);

    /**
     * 批量删除病虫害管理
     * 
     * @param diseaseIds 需要删除的病虫害管理主键集合
     * @return 结果
     */
    public int deleteDiseaseByDiseaseIds(Integer[] diseaseIds);

    /**
     * 删除病虫害管理信息
     * 
     * @param diseaseId 病虫害管理主键
     * @return 结果
     */
    public int deleteDiseaseByDiseaseId(Integer diseaseId);
}
