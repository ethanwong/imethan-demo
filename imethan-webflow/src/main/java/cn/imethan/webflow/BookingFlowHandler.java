package cn.imethan.webflow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

/**
 * BookingFlowHandler.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月5日上午11:39:47
 */
public class BookingFlowHandler extends AbstractFlowHandler {
	
	public String handleExecutionOutcome(FlowExecutionOutcome outcome, HttpServletRequest request, HttpServletResponse response) {
		if (outcome.getId().equals("bookingConfirmed")) {
			return "/booking/show?bookingId=" + outcome.getOutput().get("bookingId");
		} else {
			return "/hotels/index";
		}
	}
}