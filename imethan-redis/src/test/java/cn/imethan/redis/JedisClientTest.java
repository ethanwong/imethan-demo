package cn.imethan.redis;

import redis.clients.jedis.Jedis;

/**
 * JedisClientTest.java
 *
 * @author Ethan Wong
 * @time 2015年11月8日下午6:42:36
 */
public class JedisClientTest {
	
	public static void main(String[] args) {
//		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//		//Jedis Cluster will attempt to discover cluster nodes automatically
//		jedisClusterNodes.add(new HostAndPort("192.168.42.133", 7379));
//		JedisCluster jc = new JedisCluster(jedisClusterNodes);
//		jc.set("foo", "imethan");
//		String value = jc.get("foo");
//		
		
		Jedis jedis = new Jedis("192.168.42.133");
		jedis.set("foo", "imethan");
		String value = jedis.get("foo");
		System.out.println(value);
	}

}


