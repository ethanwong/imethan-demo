package cn.mongodb.data;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

import cn.log4j.Bar;

/**
 * MongoApp.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年9月29日上午10:24:10
 */
public class MongoApp {
	private static final Log log = LogFactory.getLog(MongoApp.class);

	
	  public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(new Mongo(), "database");
	    mongoOps.insert(new Person("Joe", 34));

	    log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));
	    
//	    mongoOps.dropCollection("person");
	    
	    while(true){
	    	Thread.currentThread().sleep(5000);
		    log.trace("Entering application.");
	        Bar bar = new Bar();
	        if (!bar.doIt()) {
	        	log.error("Didn't do it.");
	        }
	        log.trace("Exiting application.");
	       
	    }

	  }
}
