package cn.imethan.jax.rs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotBlank;

import cn.imethan.jax.rs.model.Parameter;
import cn.imethan.jax.rs.model.Result;

/**
 * CustomerService.java
 *
 * @author Ethan Wong
 * @time 2015年11月12日下午6:47:02
 */
@Path(value = "/customer")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML ,MediaType.APPLICATION_FORM_URLENCODED})
public interface CustomerService {
	

    @POST
    @Path("/testOne")
    Result testOne(@NotBlank(message="ID不能为空") @FormParam("id") String id,@FormParam("extend") String extend,@Context HttpServletRequest servletRequest);
    
    @GET
    @Path("/testTwo")
    @NotNull  Result testTwo(@Valid @NotNull @FormParam("id") String id,@Valid @NotNull @FormParam("parameter")Parameter parameter);
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/testThree/{param}")
    public String testThree(@PathParam("param") String param, 
            @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
    
    
    @GET
    @Path("/testFour")
    public @NotNull(message="返回值不能为空") @Valid  String testFour(@Size(min = 1, max = 5) @Valid @NotBlank(message="ID不能为空") @FormParam("id") String id);
    
}


