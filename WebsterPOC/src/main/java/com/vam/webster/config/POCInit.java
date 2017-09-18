package com.vam.webster.config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vam.webster.pojos.CustomerStatus;


@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages="com.vam.webster.*")
@EntityScan(basePackages="com.vam.webster.pojos")
public class POCInit implements CommandLineRunner{

	public static final String MAILBOX_TOPIC = "inboxTopic";
	
	 @Autowired
	 private Sender sender;
	 
	public static void main(String[] args) {
		SpringApplication.run(POCInit.class, args);
    }
	
	@Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
    @Override
    public void run(String... arg0) throws Exception {
        Thread.sleep(5000); // wait for subscriptions, unless they are durable
        sender.send(MAILBOX_TOPIC, new CustomerStatus(1023, "Active"));
        sender.send(MAILBOX_TOPIC, new CustomerStatus(1033, "Inactive"));
        sender.send(MAILBOX_TOPIC, new CustomerStatus(1043, "Active"));
        sender.send(MAILBOX_TOPIC, new CustomerStatus(1053, "Inactive"));
        sender.send(MAILBOX_TOPIC, new CustomerStatus(1063, "Active"));
        sender.send(MAILBOX_TOPIC, new CustomerStatus(1073, "Inactive"));
        Thread.sleep(5000);
    }
}
