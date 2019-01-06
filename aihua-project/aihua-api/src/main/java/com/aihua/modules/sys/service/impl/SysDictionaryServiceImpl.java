package com.aihua.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aihua.common.utils.PageUtils;
import com.aihua.common.utils.Query;
import com.aihua.modules.sys.dao.SysDictionaryDao;
import com.aihua.modules.sys.entity.SysDictionaryEntity;
import com.aihua.modules.sys.service.SysDictionaryService;


@Service("sysDictionaryService")
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryDao, SysDictionaryEntity> implements SysDictionaryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysDictionaryEntity> page = this.selectPage(
                new Query<SysDictionaryEntity>(params).getPage(),
                new EntityWrapper<SysDictionaryEntity>()
        );

        return new PageUtils(page);
    }
	@Override
	public List<SysDictionaryEntity> queryTreeList() {
		return baseMapper.queryTreeList();
	}
	@Override
	public boolean insertDictionary(SysDictionaryEntity sysDictionary) {
		String lastCode = baseMapper.queryLastCodeByPcode(sysDictionary.getPcode());
		if(null==lastCode){
			lastCode = (sysDictionary.getPcode().equals("0")?"":sysDictionary.getPcode())+"00";
		}
		String nextCode = String.format("%0"+lastCode.length()+"d", Integer.valueOf(lastCode)+1);
		sysDictionary.setCode(nextCode);
		sysDictionary.setCtime(new Date());
		baseMapper.insert(sysDictionary);
		return false;
	}
	@Override
	public boolean updateDictionary(SysDictionaryEntity sysDictionary) {
		SysDictionaryEntity oldDic = baseMapper.selectById(sysDictionary.getId());
		if(null != oldDic.getPcode() && !oldDic.getPcode().equals(sysDictionary.getPcode())){
			String lastCode = baseMapper.queryLastCodeByPcode(sysDictionary.getPcode());
			if(null==lastCode){
				lastCode = (sysDictionary.getPcode().equals("0")?"":sysDictionary.getPcode())+"00";
			}
			String nextCode = String.format("%0"+lastCode.length()+"d", Integer.valueOf(lastCode)+1);
			sysDictionary.setCode(nextCode);
		}
		sysDictionary.setCtime(new Date());
		baseMapper.updateById(sysDictionary);
		return false;
	}
}
