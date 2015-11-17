package cn.imethan.redis;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Example.java
 *
 * @author Ethan Wong
 * @time 2015年11月8日下午5:20:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/redis/applicationContext-redis.xml" })
public class Example {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	// inject the template as ListOperations
	// can also inject as Value, Set, ZSet, and HashOperations
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;

	@Test
	public void addLink() throws MalformedURLException {
		String userId = "imethan";
		URL url = new URL("www.imethan.cn");
		listOps.leftPush(userId, url.toExternalForm());
		// or use template directly
		redisTemplate.boundListOps(userId).leftPush(url.toExternalForm());
		// System.out.println(redisTemplate.getClientList());

	}
	
	@Test
	public void useCallback() {

		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				Long size = connection.dbSize();
				// Can cast to StringRedisConnection if using a
				// StringRedisTemplate
				((StringRedisConnection) connection).set("key", "value");
				
				connection.get("key".getBytes());
				System.out.println("size:"+size);
				return size;
			}
		});
	}

}
