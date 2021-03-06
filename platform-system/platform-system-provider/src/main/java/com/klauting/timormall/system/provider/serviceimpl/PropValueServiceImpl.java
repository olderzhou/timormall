package com.klauting.timormall.system.provider.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.klauting.timormall.system.provider.mapper.PropValueMapper;
import com.klauting.timormall.system.api.entity.PropValue;
import com.klauting.timormall.system.api.service.IPropValueService;



@Service("propValueService")
public class PropValueServiceImpl implements IPropValueService {
	@Autowired
	private PropValueMapper propValueMapper;
	
	@Override
	public PropValue queryObject(Long id){
		return propValueMapper.queryObject(id);
	}
	
	@Override
	public List<PropValue> queryList(Map<String, Object> map){
		return propValueMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return propValueMapper.queryTotal(map);
	}
	
	@Override
	public void save(PropValue propValue){
		propValueMapper.save(propValue);
	}
	
	@Override
	public void update(PropValue propValue){
		propValueMapper.update(propValue);
	}
	
	@Override
	public void delete(Long id){
		propValueMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		propValueMapper.deleteBatch(ids);
	}
	
}
