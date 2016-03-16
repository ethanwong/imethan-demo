package org.imethan.test;

import junit.framework.Assert;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String args[]) {
		int i = 1;
        //断言1结果为true，则继续往下执行
        assert i > 2:"i不大于1";
        
        Assert.assertEquals("i不大于1",i > 2, true);
        
        
        System.out.println("断言1没有问题，Go！");
 
        System.out.println("\n-----------------\n");
 
        //断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");
    }
}
