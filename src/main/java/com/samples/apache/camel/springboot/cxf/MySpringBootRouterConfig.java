package com.samples.apache.camel.springboot.cxf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * Connectors configuration class which reads from application.yml 
 * with prefix as camel configuration based on the active profile.  
 * 
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="camelconf")
public class MySpringBootRouterConfig {

	private String host;
	private int port;
	private String clienthost;
	private int clientport;
	
	public String getClienthost() {
		return clienthost;
	}

	public void setClienthost(String clienthost) {
		this.clienthost = clienthost;
	}

	public int getClientport() {
		return clientport;
	}

	public void setClientport(int clientport) {
		this.clientport = clientport;
	}
	
	public MySpringBootRouterConfig() {}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
