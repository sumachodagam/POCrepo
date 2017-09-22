package com.vam.webster.config;

import static com.vam.webster.config.POCInit.MAILBOX_TOPIC;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.vam.webster.pojos.CustomerStatus;;

@Component
public class Receiver {
	
	private final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	private Map<Integer, CustomerStatus> statusMap = new HashMap<Integer, CustomerStatus>();
	
	@JmsListener(destination = MAILBOX_TOPIC, containerFactory = "myFactory")
	public void receive(CustomerStatus customerStatus) {
		logger.info("Receiver::receive::start");
		statusMap.put(customerStatus.getCustomerId(), customerStatus);
		logger.info("Receiver::receive::end");
	}
	
	public CustomerStatus getCustomerStatus(int customerId){
		logger.info("Receiver::getCustomerStatus::start");
		CustomerStatus customerStatus = null;
		if(!statusMap.isEmpty()){
			customerStatus = statusMap.get(customerId);
		}
		logger.info("Receiver::getCustomerStatus::end");
		return customerStatus;
	}
}
