package com.wts.config;

import com.wts.webservice.IWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.xml.ws.Endpoint;

/**
 * Created by weitaosheng on 2017/3/19.
 */

@Configuration
@ComponentScan("com.wts.webservice")
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint(IWebService webServiceImpl) {
        Endpoint endpoint = new EndpointImpl(bus, webServiceImpl);
        endpoint.publish("/webservice");
        return endpoint;
    }

}
