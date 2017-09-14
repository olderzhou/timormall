package zhizhu.cy.platform.admin.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import zhizhu.cy.platform.admin.web.common.controller.BaseController;
import zhizhu.cy.platform.admin.web.util.PageUtils;
import zhizhu.cy.platform.admin.web.util.Query;
import zhizhu.cy.platform.admin.web.util.R;
import zhizhu.cy.platform.system.api.entity.Property;
import zhizhu.cy.platform.system.api.service.IPropertyService;


/**
 * 
 * 
 * @author niklaus mikaelson
 * @email niklausjulie@gmail.com
 * @date 2017-09-12 16:27:48
 */
@RestController
@RequestMapping("property")
public class PropertyController extends BaseController{
	@Autowired
	private IPropertyService propertyService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('property:list')")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Property> propertyList = propertyService.queryList(query);
		int total = propertyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(propertyList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@PreAuthorize("hasAuthority('property:info')")
	public R info(@PathVariable("id") Long id){
		Property property = propertyService.queryObject(id);
		
		return R.ok().put("property", property);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	//@RequiresPermissions("property:save")
	@PreAuthorize("hasAuthority('property:save')")
	public R save(@RequestBody Property property){
		propertyService.save(property);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	//@RequiresPermissions("property:update")
	@PreAuthorize("hasAuthority('property:update')")
	public R update(@RequestBody Property property){
		propertyService.update(property);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("property:delete")
	@PreAuthorize("hasAuthority('property:delete')")
	public R delete(@RequestBody Long[] ids){
		propertyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}