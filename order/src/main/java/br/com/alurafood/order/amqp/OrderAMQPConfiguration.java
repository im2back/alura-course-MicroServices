package br.com.alurafood.order.amqp;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAMQPConfiguration {
	  @Bean
	    public Jackson2JsonMessageConverter messageConverter() {
	    	return new Jackson2JsonMessageConverter();
	    }
	    
	    @Bean 
	    public RabbitTemplate rabbitTemplate(ConnectionFactory connnectionFactory, Jackson2JsonMessageConverter messageConverter ) {
	    	
	    	RabbitTemplate rabbitTemplate= new RabbitTemplate(connnectionFactory);
	    	rabbitTemplate.setMessageConverter(messageConverter);
	    	return rabbitTemplate;
	    }

}
