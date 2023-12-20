package br.com.alurafood.payments.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentAMQPConfiguration {
	
	@Bean
    public Queue criaFila(){
    	 return new Queue ("pagamento.concluido", false);
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
   return new RabbitAdmin(conn);
}
    
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) {
    	return event -> rabbitAdmin.initialize();
    } 
    
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
