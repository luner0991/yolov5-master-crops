package com.ruoyi.disease.service;

import java.util.List;
import com.ruoyi.disease.domain.Result;

/**
 * 识别记录Service接口
 * 
 * @author yzx
 * @date 2025-01-17
 */
public interface IResultService 
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
     * 批量删除识别记录
     * 
     * @param resultIds 需要删除的识别记录主键集合
     * @return 结果
     */
    public int deleteResultByResultIds(Integer[] resultIds);

    /**
     * 删除识别记录信息
     * 
     * @param resultId 识别记录主键
     * @return 结果
     */
    public int deleteResultByResultId(Integer resultId);
}
