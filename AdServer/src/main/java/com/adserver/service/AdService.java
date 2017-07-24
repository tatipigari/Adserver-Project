package com.adserver.service;

import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import com.adserver.controller.Partner;
import com.adserver.controller.PartnerAdMessage;

@Service
public class AdService {

	HashMap<String,Object> hm=new HashMap<String,Object>();
	public String addPartner(Partner par){
			
		if (hm.containsKey(par.getPartner_id()))
		{
			return "Sorry,Only one active campaign can exist for partner. We are not processing your request this time. ";
		}else{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("mm/dd/yyyy HH:mm:ss");
		DateTime addTime =new DateTime().plusSeconds(Integer.parseInt(par.getDuration()));
		String accessTime = addTime.toString();
		par.setTime_limit(accessTime);
		hm.put(par.getPartner_id(),par);
		return "Your campaign is created successfully ";
		}
	}
	
	public PartnerAdMessage getAd(String id){
		
		PartnerAdMessage pm=null;
		DateTime currentTime = new DateTime();
		
		if (hm.get(id)!=null)
		{
		Partner par=(Partner)hm.get(id);
	    String str = par.getTime_limit();
	    DateTime timeLimit = new DateTime(str);
		
	    if(timeLimit.isAfter(currentTime)){
	       	pm =new PartnerAdMessage();
	       	pm.setAdMessage(((Partner)hm.get(id)).getAd_content());
	       	return pm;
	       	 
	        }else{
	        hm.remove(id);
	        System.out.println("Latest Size"+hm.size());
	       	pm =new PartnerAdMessage();
	       	pm.setAdMessage("No active ad campaigns exist for the specified partner");
	       	return pm;
	         }
	    }
		else{
			pm =new PartnerAdMessage();
			pm.setAdMessage("Sorry,We could not found any campaign with requested partner.");
			return pm;
		 }
	}
	
}
