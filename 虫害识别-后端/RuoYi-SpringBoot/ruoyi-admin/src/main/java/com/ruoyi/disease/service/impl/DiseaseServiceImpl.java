package com.ruoyi.disease.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.disease.mapper.DiseaseMapper;
import com.ruoyi.disease.domain.Disease;
import com.ruoyi.disease.service.IDiseaseService;

/**
 * 病虫害管理Service业务层处理
 * 
 * @author yzx
 * @date 2025-01-16
 */
@Service
public class DiseaseServiceImpl implements IDiseaseService 
{
    @Autowired
    private DiseaseMapper diseaseMapper;

    /**
     * 查询病虫害管理
     * 
     * @param diseaseId 病虫害管理主键
     * @return 病虫害管理
     */
    @Override
    public Disease selectDiseaseByDiseaseId(Integer diseaseId)
    {
        return diseaseMapper.selectDiseaseByDiseaseId(diseaseId);
    }

    /**
     * 查询病虫害管理列表
     * 
     * @param disease 病虫害管理
     * @return 病虫害管理
     */
    @Override
    public List<Disease> selectDiseaseList(Disease disease)
    {
        return diseaseMapper.selectDiseaseList(disease);
    }

    /**
     * 新增病虫害管理
     * 
     * @param disease 病虫害管理
     * @return 结果
     */
    @Override
    public int insertDisease(Disease disease)
    {
        return diseaseMapper.insertDisease(disease);
    }

    /**
     * 修改病虫害管理
     * 
     * @param disease 病虫害管理
     * @return 结果
     */
    @Override
    public int updateDisease(Disease disease)
    {
        return diseaseMapper.updateDisease(disease);
    }

    /**
     * 批量删除病虫害管理
     * 
     * @param diseaseIds 需要删除的病虫害管理主键
     * @return 结果
     */
    @Override
    public int deleteDiseaseByDiseaseIds(Integer[] diseaseIds)
    {
        return diseaseMapper.deleteDiseaseByDiseaseIds(diseaseIds);
    }

    /**
     * 删除病虫害管理信息
     * 
     * @param diseaseId 病虫害管理主键
     * @return 结果
     */
    @Override
    public int deleteDiseaseByDiseaseId(Integer diseaseId)
    {
        return diseaseMapper.deleteDiseaseByDiseaseId(diseaseId);
    }
}
