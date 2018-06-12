package com.clustering.project.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clustering.project.dao.MemberDao;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;

	public Object getList(Object dataMap) {
		String  sqlMapId = "member.list";
		Object resultObject = dao.getList(sqlMapId, dataMap);

		return resultObject;
	}
	public Object getObject(Object dataMap) {
		String sqlMapId = "member.read";
		Object resultObject =  dao.getObject(sqlMapId, dataMap);
		
		return resultObject;
	}
	public Object updateObject(Object dataMap) {
		String sqlMapId = "member.update";
		Object resultObject =  dao.updateObject(sqlMapId, dataMap);
		
		return resultObject;
	}
	
	public Object createObject(Object dataMap) {
		String sqlMapId = "member.insert";
		
		Object resultObject =  dao.createObject(sqlMapId, dataMap);

			return resultObject;
		
	}
	public Object deleteObject(Object dataMap) {
		
		String sqlMapId = "member.delete";
		Object resultObject =  dao.deleteObject(sqlMapId, dataMap);
		
		return resultObject;
		
	}

	

}
