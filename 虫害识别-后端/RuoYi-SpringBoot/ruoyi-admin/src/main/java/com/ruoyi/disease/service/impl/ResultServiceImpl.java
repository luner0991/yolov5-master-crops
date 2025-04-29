package com.ruoyi.disease.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.disease.domain.Disease;
import com.ruoyi.disease.mapper.ResultMapper;
import com.ruoyi.disease.domain.Result;
import com.ruoyi.disease.service.IResultService;

/**
 * 识别记录Service业务层处理
 * 
 * @author yzx
 * @date 2025-01-17
 */
@Service
public class ResultServiceImpl implements IResultService 
{
    @Autowired
    private ResultMapper resultMapper;

    /**
     * 查询识别记录
     * 
     * @param resultId 识别记录主键
     * @return 识别记录
     */
    @Override
    public Result selectResultByResultId(Integer resultId)
    {
        return resultMapper.selectResultByResultId(resultId);
    }

    /**
     * 查询识别记录列表
     * 
     * @param result 识别记录
     * @return 识别记录
     */
    @Override
    public List<Result> selectResultList(Result result)
    {
        return resultMapper.selectResultList(result);
    }

    /**
     * 新增识别记录
     * 
     * @param result 识别记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertResult(Result result)
    {
        int rows = resultMapper.insertResult(result);
        insertDisease(result);
        return rows;
    }

    /**
     * 修改识别记录
     * 
     * @param result 识别记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateResult(Result result)
    {
        resultMapper.deleteDiseaseByDiseaseId(result.getResultId());
        insertDisease(result);
        return resultMapper.updateResult(result);
    }

    /**
     * 批量删除识别记录
     * 
     * @param resultIds 需要删除的识别记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteResultByResultIds(Integer[] resultIds)
    {
        resultMapper.deleteDiseaseByDiseaseIds(resultIds);
        return resultMapper.deleteResultByResultIds(resultIds);
    }

    /**
     * 删除识别记录信息
     * 
     * @param resultId 识别记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteResultByResultId(Integer resultId)
    {
        resultMapper.deleteDiseaseByDiseaseId(resultId);
        return resultMapper.deleteResultByResultId(resultId);
    }

    /**
     * 新增病虫害管理信息
     * 
     * @param result 识别记录对象
     */
    public void insertDisease(Result result)
    {
        List<Disease> diseaseList = result.getDiseaseList();
        Integer resultId = result.getResultId();
        if (StringUtils.isNotNull(diseaseList))
        {
            List<Disease> list = new ArrayList<Disease>();
            for (Disease disease : diseaseList)
            {
                disease.setDiseaseId(resultId);
                list.add(disease);
            }
            if (list.size() > 0)
            {
                resultMapper.batchDisease(list);
            }
        }
    }
}
