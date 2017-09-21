package com.webster.poc.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.webster.poc.pojos.CustomerStatus;

@Component
public class Sender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void send(String destination, CustomerStatus customerStatus) {
	    jmsTemplate.convertAndSend(new ActiveMQTopic(destination), customerStatus);
	 }
}
