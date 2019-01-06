package com.aihua.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.aihua.modules.sys.entity.SysDictionaryEntity;
import com.aihua.modules.sys.service.SysDictionaryService;
import com.aihua.common.utils.PageUtils;
import com.aihua.common.utils.R;



/**
 * 系统词典
 */
@RestController
@RequestMapping("sys/sysdictionary")
@Api(tags="系统",description="词典")
public class SysDictionaryController {
    @Autowired
    private SysDictionaryService sysDictionaryService;
	
    /**
   	 * 选择菜单(添加、修改菜单)
   	 */
   	@GetMapping("/select")
   	public R select(){
   		//查询列表数据
   		List<SysDictionaryEntity> treeList = sysDictionaryService.queryTreeList();
   		
   		//添加顶级菜单
   		SysDictionaryEntity root = new SysDictionaryEntity();
   		root.setCode("0");
   		root.setName("根节点");
   		root.setPcode("-1");
   		treeList.add(root);
   		System.out.println(JSON.toJSONString(R.ok().put("treeList", treeList)));
   		return R.ok().put("treeList", treeList);
   	}
       /**
        * 列表
        */
       @RequestMapping("/list")
       @RequiresPermissions("sys:sysdictionary:list")
       public R list(@RequestParam Map<String, Object> params){
       	//查询列表数据
       	List<SysDictionaryEntity> treeList = sysDictionaryService.queryTreeList();
           PageUtils page = new PageUtils(treeList, 1, treeList.size(), 1);
           return R.ok().put("page", page);
       }
       
       
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:sysdictionary:info")
    public R info(@PathVariable("id") Long id){
			SysDictionaryEntity sysDictionary = sysDictionaryService.selectById(id);

        return R.ok().put("sysDictionary", sysDictionary);
    }
    /**
     * 列表byPcode,获取下一层子节点
     */
    @RequestMapping("/listByPcodeNext")
    @ApiOperation(value="pcode获取词典及子集",httpMethod="GET")
    public R listByPcodeNext(@RequestParam String pcode){
        EntityWrapper<SysDictionaryEntity> ew=new EntityWrapper<SysDictionaryEntity>();
        ew.eq("is_show", true);
        ew.eq("pcode",pcode);
 	   //查询列表数据
 	   List<SysDictionaryEntity> sde=sysDictionaryService.selectList(ew);
        return R.ok().put("datas", sde);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:sysdictionary:save")
    public R save(@RequestBody SysDictionaryEntity sysDictionary){
			sysDictionaryService.insertDictionary(sysDictionary);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:sysdictionary:update")
    public R update(@RequestBody SysDictionaryEntity sysDictionary){
			sysDictionaryService.updateDictionary(sysDictionary);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:sysdictionary:delete")
    public R delete(@RequestBody Long[] organizationIds){
			sysDictionaryService.deleteBatchIds(Arrays.asList(organizationIds));

        return R.ok();
    }
}
