/**
 * It's Designed For incubation Center
 * @author ohsanghun
 * @version     %I%, %G%
 * @since       1.0
 */
package com.clustering.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.clustering.project.service.OrganizationService;
import com.clustering.project.util.CommonUtil;

/**
 * @author ohsanghun get it Mapping classlevel (JavaBean, HttpServletRequest,
 *         Model, View, ModelAndView)
 */

// ? delete @Controller,
@Controller
public class OrganizationController {

	private final static String MAPPING = "/organization";
	@Autowired
	private OrganizationService service;


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
				paramMap.put("ORGANIZATION_SEQ", (new CommonUtil()).getUniqueSequence());
				System.out.println("파람맵 시퀀스 :"+paramMap.get("ORGANIZATION_SEQ"));
				modelandView.addObject("resultMap", paramMap);
			}else {
			try {
				resultMap = (Map<String, Object>) service.getObject(paramMap);
			} catch (Exception e) {
				// TODO: handle exception
			}}
		} else if ("read".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			resultMap = (Map<String, Object>) service.getObject(paramMap);
			System.out.println(service.getObject(paramMap));
		} else if ("list".equalsIgnoreCase(action)) {
			viewName = viewName + action;
			resultList = (List<Object>) service.getList(paramMap);

		} else if ("insert".equalsIgnoreCase(action)) {
			viewName = viewName + "read";
			try {
				service.saveObject(paramMap);
				resultMap = (Map<String, Object>) service.getObject(paramMap);
			} catch (Exception e) {
			}

		} else if ("delete".equalsIgnoreCase(action)) {
			viewName = viewName + "list";
			try {
				service.deleteObject(paramMap);
				resultList = (List<Object>) service.getList(paramMap);
			} catch (Exception e) {
			}
		} else if ("update".equalsIgnoreCase(action)) {
			service.updateObject(paramMap);
			viewName = viewName + "list";
			resultList = (List<Object>) service.getList(paramMap);
		}

		modelandView.setViewName(viewName);
		modelandView.addObject("paramMap", paramMap);
		modelandView.addObject("resultMap", resultMap);
		modelandView.addObject("resultList", resultList);
		System.out.println("파람맵 시퀀스 2222222222222222:"+paramMap.get("ORGANIZATION_SEQ"));

		return modelandView;
	}
}