package com.babting.igo.igoserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babting.igo.igoserver.dao.JdoDAO;
import com.babting.igo.igoserver.model.jdo.LocationInfo;

@Controller
public class MainController {
	@Autowired
	private JdoDAO jdoDAO;
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		LocationInfo info = new LocationInfo();
		info.setPlaceName("테테");

		jdoDAO.store(info);
		
		List<LocationInfo> locInfoList = jdoDAO.getAll();
		
		model.addAttribute("locInfoList", locInfoList);
		
		return "index";
	}
}
