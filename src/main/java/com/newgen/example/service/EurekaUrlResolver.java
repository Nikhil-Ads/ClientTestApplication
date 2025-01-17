//package com.newgen.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;
//import com.netflix.discovery.shared.Application;
//
//@Service
//public class EurekaUrlResolver {
//
//	private static final Logger logger = LoggerFactory.getLogger(EurekaUrlResolver.class);
//
//	@Autowired
//    private EurekaClient eurekaClient;
//	
//	private String metadataCoreServiceUrl;
//	
//	public String procureUrl(String serviceName) {
//		logger.debug("Getting requested url from Eureka Server");
//		//Get Metadata service url from Eureka Server
//		Application application = eurekaClient.getApplication(serviceName);
//	    InstanceInfo instanceInfo = application.getInstances().get(0);
//	    metadataCoreServiceUrl = "http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort();
//		return metadataCoreServiceUrl;	
//	}
//}
