package com.ruoyi.disease.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.disease.domain.Result;
import com.ruoyi.disease.service.IResultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 识别记录Controller
 * 
 * @author yzx
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/result/result")
public class ResultController extends BaseController
{
    @Autowired
    private IResultService resultService;

    /**
     * 查询识别记录列表
     */
    @PreAuthorize("@ss.hasPermi('result:result:list')")
    @GetMapping("/list")
    public TableDataInfo list(Result result)
    {
        startPage();
        List<Result> list = resultService.selectResultList(result);
        return getDataTable(list);
    }

    /**
     * 导出识别记录列表
     */
    @PreAuthorize("@ss.hasPermi('result:result:export')")
    @Log(title = "识别记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Result result)
    {
        List<Result> list = resultService.selectResultList(result);
        ExcelUtil<Result> util = new ExcelUtil<Result>(Result.class);
        util.exportExcel(response, list, "识别记录数据");
    }

    /**
     * 获取识别记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('result:result:query')")
    @GetMapping(value = "/{resultId}")
    public AjaxResult getInfo(@PathVariable("resultId") Integer resultId)
    {
        return success(resultService.selectResultByResultId(resultId));
    }

    /**
     * 新增识别记录
     */
    @PreAuthorize("@ss.hasPermi('result:result:add')")
    @Log(title = "识别记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Result result)
    {
        return toAjax(resultService.insertResult(result));
    }

    /**
     * 修改识别记录
     */
    @PreAuthorize("@ss.hasPermi('result:result:edit')")
    @Log(title = "识别记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Result result)
    {
        return toAjax(resultService.updateResult(result));
    }

    /**
     * 删除识别记录
     */
    @PreAuthorize("@ss.hasPermi('result:result:remove')")
    @Log(title = "识别记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resultIds}")
    public AjaxResult remove(@PathVariable Integer[] resultIds)
    {
        return toAjax(resultService.deleteResultByResultIds(resultIds));
    }
}
