/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.imethan.jax.rs.mapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.apache.cxf.message.Message;

import cn.imethan.jax.rs.model.Result;

/**
 * @see org.apache.cxf.jaxrs.validation.ValidationExceptionMapper
 * 扩展：添加返回response信息自定义
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年11月14日下午3:15:48
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper< ValidationException > {
    private static final Logger LOG = LogUtils.getL7dLogger(ValidationExceptionMapper.class);
    
	@Context
    private MessageContext mc;
	
    @Override
    public Response toResponse(ValidationException exception) {
    	String contentType = "";
    	String message = "出现异常！";
        Response.Status errorStatus = Response.Status.INTERNAL_SERVER_ERROR;
        
        contentType = (String) mc.get(Message.CONTENT_TYPE);
        
        if (exception instanceof ConstraintViolationException) { 
            
            final ConstraintViolationException constraint = (ConstraintViolationException) exception;
            
            for (final ConstraintViolation< ? > violation: constraint.getConstraintViolations()) {
            	
                LOG.log(Level.WARNING, 
                    violation.getRootBeanClass().getSimpleName() 
                    + "." + violation.getPropertyPath() 
                    + ": " + violation.getMessage());
                
                message = violation.getMessage();
            }
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
