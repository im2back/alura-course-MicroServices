package br.com.alurafood.order.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAMQPConfiguration {

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
	public RabbitTemplate rabbitTemplate(ConnectionFactory connnectionFactory,
			Jackson2JsonMessageConverter messageConverter) {

		RabbitTemplate rabbitTemplate = new RabbitTemplate(connnectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	@Bean
	public Queue filaDetalhesPedido() {
		return QueueBuilder
				.nonDurable("pagamentos.detalhes-pedido")
				.deadLetterExchange("pagamentos.dlx")
				.build();
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("pagamentos.ex");
	}

	@Bean
	public Binding binding(FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(filaDetalhesPedido()).to(fanoutExchange);
	}

	
	// fila de erros

	@Bean
	public Queue deadLetterQueue() {
		return QueueBuilder.nonDurable("pagamentos.detalhes-order-dlq").build();
	}

	@Bean
	public FanoutExchange deadLetterExchange() {
		return new FanoutExchange("pagamentos.dlx");
	}

	@Bean
	public Binding bindingError() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
	}
}
