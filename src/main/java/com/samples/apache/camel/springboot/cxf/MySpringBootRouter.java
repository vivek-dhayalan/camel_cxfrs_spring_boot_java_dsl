package com.samples.apache.camel.springboot.cxf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.component.cxf.spring.SpringJAXRSClientFactoryBean;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spring.boot.FatJarRouter;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;


import com.samples.apache.camel.springboot.helpers.CustomConverter;
import com.samples.apache.camel.springboot.helpers.MonnitData;

@SpringBootApplication
public class MySpringBootRouter extends FatJarRouter {

	@Autowired
	private MySpringBootRouterConfig routerConfig;
	
	@Override
    public void configure() throws JAXBException {
    	    	
    	//Camel rest server configuration
    	restConfiguration()
    		.component("restlet")
    		.port(routerConfig.getPort())
    		.host(routerConfig.getHost());
    	
    	//rest endpoint configuration
    	rest("/monnitmysqltsdb")
    		.post()
    		.to("direct:monnitmysqltsdb");
    	
    	//unmarshal configuration
    	JaxbDataFormat jaxb = new JaxbDataFormat(JAXBContext.newInstance(MonnitData.class));    	
    	jaxb.setContextPath(CustomConverter.class.getPackage().getName());    	

    	//multicast parallel processing thread pool size configuration
    	ExecutorService executor = Executors.newFixedThreadPool(10);
    	
        from("direct:monnitmysqltsdb")
        	.multicast()
        	.parallelProcessing()
        	.executorService(executor)
        	.to("direct:monnittsdb", "direct:monnitmysql" );
        
        from("direct:monnitmysql")
        	.split()
        	.tokenizeXML("APIDataMessage").streaming()
        	.unmarshal(jaxb)
        	.convertBodyTo(com.samples.apache.camel.springboot.helpers.MeasurementEventDTO.class)
        	.setHeader("Content-Type", constant("application/json"))
        	.to("cxfrs://bean://monnitClient");

        from("direct:monnittsdb")
	    	.split()
	    	.tokenizeXML("APIDataMessage").streaming()
	    	.unmarshal(jaxb)
	    	.convertBodyTo(com.samples.apache.camel.springboot.helpers.MeasurementEventDTO.class)
	    	.setHeader("Content-Type", constant("application/json"))
	    	.to("cxfrs://bean://tsdbData");
    }
    
    @Bean
    public SpringJAXRSClientFactoryBean monnitClient()
    {
    	SpringJAXRSClientFactoryBean springJAXRSClientFactoryBean = new SpringJAXRSClientFactoryBean();
    	String measurementEventURL = "http://" + routerConfig.getClienthost() + 
				":" + routerConfig.getClientport() + "/api/measurementEvents";
    	
    	springJAXRSClientFactoryBean.setBeanId("monnitClient");
    	springJAXRSClientFactoryBean.setAddress(measurementEventURL);
    	springJAXRSClientFactoryBean.setServiceClass(com.samples.apache.camel.springboot.helpers.MeasurementEventEndpoint.class);
    	springJAXRSClientFactoryBean.setLoggingFeatureEnabled(true);
    	springJAXRSClientFactoryBean.setSkipFaultLogging(true);
    	springJAXRSClientFactoryBean.setProvider(jsonProvider());    	
    	return springJAXRSClientFactoryBean;
    }
    
    @Bean
    public SpringJAXRSClientFactoryBean tsdbData()
    {
    	SpringJAXRSClientFactoryBean springJAXRSClientFactoryBean = new SpringJAXRSClientFactoryBean();
    	String measurementEventURL = "http://" + routerConfig.getClienthost() + 
			":" + routerConfig.getClientport() + "/api/opentsdb";
    	    	
    	springJAXRSClientFactoryBean.setBeanId("tsdbData");
    	springJAXRSClientFactoryBean.setAddress(measurementEventURL);
    	springJAXRSClientFactoryBean.setServiceClass(com.samples.apache.camel.springboot.helpers.MeasurementEventEndpoint.class);
    	springJAXRSClientFactoryBean.setLoggingFeatureEnabled(true);
    	springJAXRSClientFactoryBean.setSkipFaultLogging(true);
    	springJAXRSClientFactoryBean.setProvider(jsonProvider());
    	return springJAXRSClientFactoryBean;
    }
        
    JacksonJsonProvider jsonProvider()
    {
    	return new JacksonJsonProvider();
    }

}
