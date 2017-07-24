package com.adserver.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adserver.service.AdService;

@RestController
public class AdserverController {
	
	@Autowired
	private AdService adService;
	static HashMap<String,Object> hm=new HashMap<String,Object>();
	
	@RequestMapping(method=RequestMethod.POST,value="/ad")
	public String AddPartner(@RequestBody Partner par){
			
		return adService.addPartner(par);
		
	}
	@RequestMapping("/ad/{id}")
	public PartnerAdMessage GetPartner(@PathVariable String id){
		
		return adService.getAd(id);
		
	}
	
}
	