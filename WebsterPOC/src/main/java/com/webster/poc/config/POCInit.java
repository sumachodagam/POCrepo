package com.webster.poc.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.webster.poc.pojos.CustomerStatus;


@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages="com.webster.poc.*")
@EntityScan(basePackages="com.webster.poc.pojos")
public class POCInit implements CommandLineRunner{

	public static final String MAILBOX_TOPIC = "inboxTopic";
	
	@Autowired
	private Sender sender;
	 
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dbDriverClassName;

	@Value("${DB_URL}")
	private String dbUrl;
	
	@Value("${DB_PORT}")
	private String dbPort;
	
	@Value("${DB_SCHEMA}")
	private String dbSchema;

	@Value("${DB_USER}")
	private String dbUsername;

	@Value("${DB.PASSWORD}")
	private String dbPassword;
	
	
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
    
    @Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriverClassName);
		dataSource.setUrl("jdbc:mysql://"+dbUrl+":"+dbPort+"/"+dbSchema);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(readPassword(dbPassword));
		return dataSource;
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
    
	private String readPassword(String path) {
		String password = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return password;
	}
}
