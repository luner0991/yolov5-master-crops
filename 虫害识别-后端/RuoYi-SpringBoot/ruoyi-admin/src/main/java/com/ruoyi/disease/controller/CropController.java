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
import com.ruoyi.disease.domain.Crop;
import com.ruoyi.disease.service.ICropService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农作物管理Controller
 * 
 * @author yzx
 * @date 2025-01-18
 */
@RestController
@RequestMapping("/disease/crop")
public class CropController extends BaseController
{
    @Autowired
    private ICropService cropService;

    /**
     * 查询农作物管理列表
     */
    @PreAuthorize("@ss.hasPermi('disease:crop:list')")
    @GetMapping("/list")
    public TableDataInfo list(Crop crop)
    {
        startPage();
        List<Crop> list = cropService.selectCropList(crop);
        return getDataTable(list);
    }

    /**
     * 导出农作物管理列表
     */
    @PreAuthorize("@ss.hasPermi('disease:crop:export')")
    @Log(title = "农作物管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Crop crop)
    {
        List<Crop> list = cropService.selectCropList(crop);
        ExcelUtil<Crop> util = new ExcelUtil<Crop>(Crop.class);
        util.exportExcel(response, list, "农作物管理数据");
    }

    /**
     * 获取农作物管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('disease:crop:query')")
    @GetMapping(value = "/{cropId}")
    public AjaxResult getInfo(@PathVariable("cropId") Integer cropId)
    {
        return success(cropService.selectCropByCropId(cropId));
    }

    /**
     * 新增农作物管理
     */
    @PreAuthorize("@ss.hasPermi('disease:crop:add')")
    @Log(title = "农作物管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Crop crop)
    {
        return toAjax(cropService.insertCrop(crop));
    }

    /**
     * 修改农作物管理
     */
    @PreAuthorize("@ss.hasPermi('disease:crop:edit')")
    @Log(title = "农作物管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Crop crop)
    {
        return toAjax(cropService.updateCrop(crop));
    }

    /**
     * 删除农作物管理
     */
    @PreAuthorize("@ss.hasPermi('disease:crop:remove')")
    @Log(title = "农作物管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cropIds}")
    public AjaxResult remove(@PathVariable Integer[] cropIds)
    {
        return toAjax(cropService.deleteCropByCropIds(cropIds));
    }
}
