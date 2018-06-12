package com.clustering.project.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clustering.project.dao.OrganizationDao;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationDao dao;

	public Object getList(Object dataMap) {
		String  sqlMapId = "organization.list";
		Object resultObject = dao.getList(sqlMapId, dataMap);

		return resultObject;
	}
	public Object getObject(Object dataMap) {
		String sqlMapId = "organization.read";
		Object resultObject =  dao.getObject(sqlMapId, dataMap);
		
		return resultObject;
	}
	public Object updateObject(Object dataMap) {
		String sqlMapId = "organization.update";
		Object resultObject =  dao.updateObject(sqlMapId, dataMap);
		
		return resultObject;
	}
	
	public Object saveObject(Object dataMap) {
		String sqlMapId = "organization.insert";
		Object resultObject =  dao.saveObject(sqlMapId, dataMap);

			return resultObject;
		
	}
	public Object deleteObject(Object dataMap) {
		
		
		String sqlMapId = "organization.delete";
		Object resultObject =  dao.deleteObject(sqlMapId, dataMap);
		
		return resultObject;
		
	}

	

}
