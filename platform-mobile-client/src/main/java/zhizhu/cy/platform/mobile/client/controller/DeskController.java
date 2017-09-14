package zhizhu.cy.platform.mobile.client.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import zhizhu.cy.platform.mobile.client.common.controller.BaseController;
import zhizhu.cy.platform.mobile.client.util.PageUtils;
import zhizhu.cy.platform.mobile.client.util.Query;
import zhizhu.cy.platform.mobile.client.util.R;
import zhizhu.cy.platform.system.api.entity.Desk;
import zhizhu.cy.platform.system.api.service.IDeskService;


/**
 * 
 * 
 * @author niklaus miakelson
 * @email niklausjulie@gmail.com
 * @date 2017-09-07 16:53:39
 */
@RestController
@RequestMapping("/api/{version}/desk")
@Api(tags = "桌子")
@Validated
public class DeskController extends BaseController {
	@Autowired
	private IDeskService deskService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "查看椅子列表")
	public R list(
			@ApiParam(required = true, value = "版本", defaultValue = "v1") @PathVariable("version") String version,
			@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Desk> deskList = deskService.queryList(query);
		int total = deskService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(deskList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
//	@RequiresPermissions("desk:info")
	public R info(
			@ApiParam(required = true, value = "版本", defaultValue = "v1") @PathVariable("version") String version,
			@PathVariable("id") Long id){
		Desk desk = deskService.queryObject(id);
		
		return R.ok().put("desk", desk);
	}
	
	
}
