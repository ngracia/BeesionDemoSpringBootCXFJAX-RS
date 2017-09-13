package com.beesion.servicios;

import java.util.List;

import javax.activation.DataHandler;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.beesion.dto.ResponseSort;
import com.beesion.dto.ResponseGet;
import com.beesion.dto.ResponseAdd;
 
@Path("/serviciosBeesion")
@Service
public interface ServiciosBeesion {
 
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("/jar")
	public DataHandler jar();

	
	@GET
	@Path("/sort")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public ResponseSort sort(@QueryParam("elements") String elements);
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public ResponseAdd add(@QueryParam("value") String value);
	
	@GET
	@Path("/get")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public ResponseGet get(@QueryParam("value") String value);
}
