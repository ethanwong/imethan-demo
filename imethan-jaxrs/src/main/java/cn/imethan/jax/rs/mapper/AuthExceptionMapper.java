package cn.imethan.jax.rs.mapper;

import java.util.logging.Logger;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.apache.cxf.message.Message;

import cn.imethan.jax.rs.exception.AuthException;
import cn.imethan.jax.rs.model.Result;

/**
 * AuthExceptionMapper.java
 *
 * @author Ethan Wong
 * @time 2015年11月13日下午4:06:48
 */
@Provider
public class AuthExceptionMapper implements ExceptionMapper<AuthException> {

	private static final Logger LOG = LogUtils.getL7dLogger(AuthExceptionMapper.class);
	
	@Context
    private MessageContext mc;
	
	@Override
	public Response toResponse(AuthException exception) {
		String contentType = (String) mc.get(Message.CONTENT_TYPE);
		String message = "";
		Response.Status errorStatus = Response.Status.INTERNAL_SERVER_ERROR;
		if (exception instanceof AuthException) {
			LOG.info("******AuthExceptionMapper******");
			errorStatus = Response.Status.UNAUTHORIZED;
			message = exception.getMessage();
		}
		ResponseBuilder responseBuilder = new ResponseBuilderImpl();
		Result result = new Result();
		result.setMessage(message);
		responseBuilder.entity(result);
		responseBuilder.status(errorStatus);
		responseBuilder.header("Content-Type", contentType+";charset=UTF-8");
		return responseBuilder.build();
	}
}
