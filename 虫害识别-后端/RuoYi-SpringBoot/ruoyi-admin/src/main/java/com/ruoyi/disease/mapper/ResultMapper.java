package com.ruoyi.disease.mapper;

import java.util.List;
import com.ruoyi.disease.domain.Result;
import com.ruoyi.disease.domain.Disease;

/**
 * 识别记录Mapper接口
 * 
 * @author yzx
 * @date 2025-01-17
 */
public interface ResultMapper 
{
    /**
     * 查询识别记录
     * 
     * @param resultId 识别记录主键
     * @return 识别记录
     */
    public Result selectResultByResultId(Integer resultId);

    /**
     * 查询识别记录列表
     * 
     * @param result 识别记录
     * @return 识别记录集合
     */
    public List<Result> selectResultList(Result result);

    /**
     * 新增识别记录
     * 
     * @param result 识别记录
     * @return 结果
     */
    public int insertResult(Result result);

    /**
     * 修改识别记录
     * 
     * @param result 识别记录
     * @return 结果
     */
    public int updateResult(Result result);

    /**
     * 删除识别记录
     * 
     * @param resultId 识别记录主键
     * @return 结果
     */
    public int deleteResultByResultId(Integer resultId);

    /**
     * 批量删除识别记录
     * 
     * @param resultIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResultByResultIds(Integer[] resultIds);

    /**
     * 批量删除病虫害管理
     * 
     * @param resultIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDiseaseByDiseaseIds(Integer[] resultIds);
    
    /**
     * 批量新增病虫害管理
     * 
     * @param diseaseList 病虫害管理列表
     * @return 结果
     */
    public int batchDisease(List<Disease> diseaseList);
    

    /**
     * 通过识别记录主键删除病虫害管理信息
     * 
     * @param resultId 识别记录ID
     * @return 结果
     */
    public int deleteDiseaseByDiseaseId(Integer resultId);
}
