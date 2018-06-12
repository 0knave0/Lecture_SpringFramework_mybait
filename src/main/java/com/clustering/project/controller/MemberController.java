/**
 * It's Designed For incubation Center
 * @author ohsanghun
 * @version     %I%, %G%
 * @since       1.0
 */
package com.clustering.project.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.clustering.project.service.MemberService;
import com.clustering.project.util.CommonUtil;

/**
 * @author ohsanghun get it Mapping classlevel (JavaBean, HttpServletRequest,
 *         Model, View, ModelAndView)
 */

// ? delete @Controller,
@Controller
public class MemberController {

	private final static String MAPPING = "/member";
	@Autowired
	private MemberService service;


	@RequestMapping(value = MAPPING, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView actionMethod(@RequestParam Map<String, Object> paramMap, ModelAndView modelandView) {

		String viewName = MAPPING + "/";
		String action = (String) paramMap.get("action");
		System.out.println("얘가 파람맵@@@@@@@@@@@" + paramMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> resultList = new ArrayList<Object>();
		System.out.println("사이즈는~~~" + paramMap.size());
		// divided depending on action value
		if ("edit".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			if (paramMap.size() < 2) {
				paramMap.put("MEMBER_SEQ", (new CommonUtil()).getUniqueSequence());
				System.out.println("멤버 파람맵 시퀀스 :"+paramMap.get("MEMBER_SEQ"));
				modelandView.addObject("resultMap", paramMap);
			}else {
			try {
				resultMap = (Map<String, Object>) service.getObject(paramMap);
				System.out.println("으아아아앙 ㅣㅇ샛기 어딨어~"+resultMap.get("MEMBER_SEQ"));
			} catch (Exception e) {
				// TODO: handle exception
			}}
		} else if ("read".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			System.out.println("파람맵 뭐임?????"+paramMap);
			System.out.println("으아아아앙 ㅣㅇ샛기 어딨어~"+paramMap.get("MEMBER_SEQ"));
			resultMap = (Map<String, Object>) service.getObject(paramMap);
			System.out.println("으아아아앙 ㅣㅇ샛기 어딨어~"+resultMap.get("MEMBER_SEQ"));
			System.out.println("read 화면 이야~~~~~~"+resultMap);
		} else if ("list".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			resultList = (List<Object>) service.getList(paramMap);

		} else if ("insert".equalsIgnoreCase(action)) {
			viewName = viewName + "read";
				Calendar cal= Calendar.getInstance();
				
				paramMap.put("REGISTER_SEQ", "UUID-1111-1111111");
				paramMap.put("MODIFIER_SEQ", "UUID-1111-1111111");
				paramMap.put("ORGANIZATION_SEQ", "UUID-11-CIPI9M");
				System.out.println("얘가 파람맵@@@@@@@@@@@" + paramMap);
				service.createObject(paramMap);
				resultMap = (Map<String, Object>) service.getObject(paramMap);
			

		} else if ("delete".equalsIgnoreCase(action)) {
			viewName = viewName + "list";
			try {
				service.deleteObject(paramMap);
				resultList = (List<Object>) service.getList(paramMap);
			} catch (Exception e) {
			}
		} else if ("update".equalsIgnoreCase(action)) {
			service.updateObject(paramMap);
			paramMap.put("MODIFIER_SEQ", "UUID-1111-1111111");
			paramMap.put("MODIFIY_DATE", new Date().getTime());
			viewName = viewName + "list";
			resultList = (List<Object>) service.getList(paramMap);
		}

		modelandView.setViewName(viewName);
		modelandView.addObject("paramMap", paramMap);
		modelandView.addObject("resultMap", resultMap);
		modelandView.addObject("resultList", resultList);
		System.out.println("멤버 파람맵 시퀀스 2222222222222222:"+paramMap.get("MEMBER_SEQ"));

		return modelandView;
	}
}