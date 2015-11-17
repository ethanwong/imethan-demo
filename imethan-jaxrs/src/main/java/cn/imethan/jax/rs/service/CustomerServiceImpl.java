package cn.imethan.jax.rs.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;

import cn.imethan.jax.rs.model.Parameter;
import cn.imethan.jax.rs.model.Result;

/**
 * CustomerServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2015年11月12日下午6:56:30
 */
public class CustomerServiceImpl implements  CustomerService{
	
	@Context
    private MessageContext mc;
	
	
	public CustomerServiceImpl(){
		
	}

	@Override
	public Result testOne(String id,String extend,HttpServletRequest servletRequest) {
		List<String> list = new ArrayList<String>();
		System.out.println("---------------------id:"+id);
		System.out.println("---------------------extend:"+extend);
		System.out.println("---------------------getQueryString:"+servletRequest.getQueryString());
		System.out.println(mc.getUriInfo());
		list.add(id);
		list.add(id);
		list.add(id);
		list.add(id);
		list.add(id);
		Result result = new Result();
		result.setSucceed(true);
		result.setMessage("alalallalalla");
		return result;
	}

	@Override
	public Result testTwo(String id, Parameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String testThree(String param, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String testFour(String id) {
		System.out.println("-----------------id:"+id);
		
		return "zhegeslsl";
	}

}