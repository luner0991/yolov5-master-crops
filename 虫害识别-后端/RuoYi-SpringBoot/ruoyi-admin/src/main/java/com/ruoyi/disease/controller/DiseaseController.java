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
import com.ruoyi.disease.domain.Disease;
import com.ruoyi.disease.service.IDiseaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 病虫害管理Controller
 * 
 * @author yzx
 * @date 2025-01-16
 */
@RestController
@RequestMapping("/disease/disease")
public class DiseaseController extends BaseController
{
    @Autowired
    private IDiseaseService diseaseService;

    /**
     * 查询病虫害管理列表
     */
    @PreAuthorize("@ss.hasPermi('disease:disease:list')")
    @GetMapping("/list")
    public TableDataInfo list(Disease disease)
    {
        startPage();
        List<Disease> list = diseaseService.selectDiseaseList(disease);
        return getDataTable(list);
    }

    /**
     * 导出病虫害管理列表
     */
    @PreAuthorize("@ss.hasPermi('disease:disease:export')")
    @Log(title = "病虫害管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Disease disease)
    {
        List<Disease> list = diseaseService.selectDiseaseList(disease);
        ExcelUtil<Disease> util = new ExcelUtil<Disease>(Disease.class);
        util.exportExcel(response, list, "病虫害管理数据");
    }

    /**
     * 获取病虫害管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('disease:disease:query')")
    @GetMapping(value = "/{diseaseId}")
    public AjaxResult getInfo(@PathVariable("diseaseId") Integer diseaseId)
    {
        return success(diseaseService.selectDiseaseByDiseaseId(diseaseId));
    }

    /**
     * 新增病虫害管理
     */
    @PreAuthorize("@ss.hasPermi('disease:disease:add')")
    @Log(title = "病虫害管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Disease disease)
    {
        return toAjax(diseaseService.insertDisease(disease));
    }

    /**
     * 修改病虫害管理
     */
    @PreAuthorize("@ss.hasPermi('disease:disease:edit')")
    @Log(title = "病虫害管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Disease disease)
    {
        return toAjax(diseaseService.updateDisease(disease));
    }

    /**
     * 删除病虫害管理
     */
    @PreAuthorize("@ss.hasPermi('disease:disease:remove')")
    @Log(title = "病虫害管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{diseaseIds}")
    public AjaxResult remove(@PathVariable Integer[] diseaseIds)
    {
        return toAjax(diseaseService.deleteDiseaseByDiseaseIds(diseaseIds));
    }
}
