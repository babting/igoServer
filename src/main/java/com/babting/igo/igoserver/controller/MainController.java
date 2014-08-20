package com.babting.igo.igoserver.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babting.igo.igoserver.dao.JdoDAO;
import com.babting.igo.igoserver.model.jdo.LocationInfo;
import com.googlecode.flickrjandroid.Parameter;
import com.googlecode.flickrjandroid.REST;
import com.googlecode.flickrjandroid.oauth.OAuthUtils;

@Controller
public class MainController {
	@Autowired
	private JdoDAO jdoDAO;
	
	@RequestMapping(value = "/index")
	public String index(Model model, String oauth_callback_confirmed, String oauth_token, String oauth_token_secret) {
		model.addAttribute("oauth_callback_confirmed", oauth_callback_confirmed);
		model.addAttribute("oauth_token", oauth_token);
		model.addAttribute("oauth_token_secret", oauth_token_secret);
		
		return "index";
	}
	
	@RequestMapping(value="/auth")
	public String auth(Model model) {
		try {
			String requestUrl = "http://api.flickr.com/services/oauth/request_token";
			
			List<Parameter> parameters = new ArrayList<Parameter>();
            parameters.add(new Parameter("oauth_callback", "http://excellent-bolt-614.appspot.com/index"));
            parameters.add(new Parameter("oauth_consumer_key", "49bcc5914e10fde0b132230bb5c2142b"));
            OAuthUtils.addBasicOAuthParams(parameters);
            
			String signature2 = OAuthUtils.getSignature(
					"POST",
					URLEncoder.encode(requestUrl, OAuthUtils.ENC), 
					parameters, 
					URLEncoder.encode(OAuthUtils.format(parameters, OAuthUtils.ENC), OAuthUtils.ENC), 
					"6f84861f713571d2");
			
			parameters.add(new Parameter("oauth_signature", signature2));
			
			REST rest = new REST("api.flickr.com");
			String responseStr = rest.sendPost("/services/oauth/request_token", parameters);
			
			System.out.println(responseStr);
			
			model.addAttribute("responseStr", responseStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "auth";
	}
}
