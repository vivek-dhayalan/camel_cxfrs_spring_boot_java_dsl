package com.samples.apache.camel.springboot.helpers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.samples.apache.camel.springboot.helpers.MeasurementEventDTO;

@Path("/api")
public interface MeasurementEventEndpoint {

    @POST
    @Path("/measurementEvents")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String measurementEvent(MeasurementEventDTO me);
	
}
