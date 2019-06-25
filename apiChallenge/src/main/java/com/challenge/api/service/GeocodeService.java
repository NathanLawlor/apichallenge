package com.challenge.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodeService {
	
	private RestTemplate restTemplate;
	
	@Autowired
	public GeocodeService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public String[] getLocation(String coordinates) throws Exception{
	    String api = "https://geocode.xyz/" + coordinates + "?json=1&auth=685179041514839662529x2771";
	    Map<String, String> location = new HashMap<String, String>();
        
        location = restTemplate.getForObject(api, HashMap.class);     
       
        String country = location.get("country");
        String city = location.get("city");
        
        String[] locationValues = {country, city};
        System.out.println(locationValues.toString());
        
		return locationValues ;
	}
}
