package com.babting.igo.igoserver.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babting.igo.igoserver.dao.JdoDAO;
import com.babting.igo.igoserver.model.ApiResult;
import com.babting.igo.igoserver.model.RegistLocationApiResult;
import com.babting.igo.igoserver.model.jdo.LocationInfo;

@Controller
@RequestMapping(value = "api")
public class ApiController {
	@Autowired
	private JdoDAO jdoDAO;
	
	@Autowired
	private ObjectMapper mapper;
	
	@RequestMapping(value = "/registLoc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String registLoc(Model model, Double latitude, Double longitude, String locName, String desc) throws Exception {
		LocationInfo info = new LocationInfo();
		info.setPlaceName(locName);
		info.setDesc(desc);
		info.setLatitude(latitude);
		info.setLongitude(longitude);

		jdoDAO.store(info);
		
		RegistLocationApiResult apiResult = new RegistLocationApiResult();
		apiResult.setResultCode(ApiResult.STATUS_SUCCESS);
		apiResult.setTitle(locName);
		
		return mapper.writeValueAsString(apiResult);
	}
}
