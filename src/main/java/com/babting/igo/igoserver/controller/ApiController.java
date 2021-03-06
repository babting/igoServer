package com.babting.igo.igoserver.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babting.igo.igoserver.dao.JdoDAO;
import com.babting.igo.igoserver.model.ApiResult;
import com.babting.igo.igoserver.model.GetLocationListApiResult;
import com.babting.igo.igoserver.model.LocationInfoVO;
import com.babting.igo.igoserver.model.RegistLocationApiResult;
import com.babting.igo.igoserver.model.jdo.LocationInfo;
import com.babting.igo.igoserver.model.jdo.LocationPhoto;
import com.babting.igo.igoserver.model.jdo.PhotoInfo;

@Controller
@RequestMapping(value = "api")
public class ApiController {
	@Autowired
	private JdoDAO jdoDAO;
	
	@Autowired
	private ObjectMapper mapper;
	
	@RequestMapping(value = "/registLoc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String registLoc(Model model, Double latitude, Double longitude, String locName, String desc, String comment, String categoryList) throws Exception {
		LocationInfo info = new LocationInfo();
		info.setPlaceName(locName);
		info.setDesc(desc);
		info.setLatitude(latitude);
		info.setLongitude(longitude);
		info.setCategorys(categoryList);

		jdoDAO.store(info);
		
		RegistLocationApiResult apiResult = new RegistLocationApiResult();
		apiResult.setResultCode(ApiResult.STATUS_SUCCESS);
		apiResult.setTitle(locName);
		
		return mapper.writeValueAsString(apiResult);
	}
	
	@RequestMapping(value = "/selectLoc", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectLoc(
			Model model, 
			Double smallLatitude, 
			Double smallLongitude, 
			Double largeLatitude, 
			Double largeLongitude) throws Exception {
		
				
		List<LocationInfo> locationJDOList = jdoDAO.getLocList(smallLatitude, smallLongitude, largeLatitude, largeLongitude);
		
		GetLocationListApiResult apiResult = new GetLocationListApiResult();
		apiResult.setResultCode(ApiResult.STATUS_SUCCESS);
		apiResult.setCount(locationJDOList.size());
		
		List<LocationInfoVO> rstLocationList = new ArrayList<LocationInfoVO>();
		for(int i = 0 ; i < locationJDOList.size() ; i++) {
			LocationInfoVO locationInfo = new LocationInfoVO();
			locationInfo.setDesc(locationJDOList.get(i).getDesc());
			locationInfo.setLatitude(locationJDOList.get(i).getLatitude());
			locationInfo.setLongitude(locationJDOList.get(i).getLongitude());
			locationInfo.setPlaceName(locationJDOList.get(i).getPlaceName());
			locationInfo.setCategorys(locationJDOList.get(i).getCategorys());
			
			rstLocationList.add(locationInfo);
		}
		apiResult.setLocationList(rstLocationList);
		
		return mapper.writeValueAsString(apiResult);
	}
	
	@RequestMapping(value = "/registPhoto", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String registPhoto(Double latitude, Double longitude, String locName, String imgTitle, String imgUrl) throws Exception {
		PhotoInfo info = new PhotoInfo();
		info.setTitle(imgTitle);
		info.setUrl(imgUrl);
		
		LocationPhoto locationPhoto = new LocationPhoto();
		locationPhoto.setLatitude(latitude);
		locationPhoto.setLongitude(longitude);
		locationPhoto.setPlaceName(locName);
		List<PhotoInfo> photoList = new ArrayList<PhotoInfo>();
		photoList.add(info);
		
		locationPhoto.setPhotoInfoList(photoList);
		
		jdoDAO.store(locationPhoto);
		
		ApiResult apiResult = new ApiResult();
		apiResult.setResultCode(ApiResult.STATUS_SUCCESS);
		
		return mapper.writeValueAsString(apiResult);
	}
}
