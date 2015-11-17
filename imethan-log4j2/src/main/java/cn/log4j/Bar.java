package cn.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Bar.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年9月29日上午10:45:48
 */
public class Bar {
	static final Logger logger = LogManager.getLogger(Bar.class.getName());
	private static final Marker FILTER_MARKER = MarkerManager.getMarker("FLOW");
	public boolean doIt() {
		logger.entry();
		logger.error("Did it again!");
		logger.debug(FILTER_MARKER, "Bar filter marker test");
		return logger.exit(false);
	}
}