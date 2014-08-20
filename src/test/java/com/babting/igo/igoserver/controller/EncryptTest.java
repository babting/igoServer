package com.babting.igo.igoserver.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.googlecode.flickrjandroid.Parameter;
import com.googlecode.flickrjandroid.REST;
import com.googlecode.flickrjandroid.oauth.OAuthUtils;

public class EncryptTest {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	@Test
	public void encryptTest1() {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
