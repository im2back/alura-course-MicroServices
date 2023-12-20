package br.com.alurafood.order.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.alurafood.order.dto.PaymentDto;

@Component
public class PaymentListener {

	
	@RabbitListener(queues = "pagamento.concluido")
	public void receberMenssagem(PaymentDto dto) {
			
		System.out.println("Id do pagamento: "+ dto.id());		

	}
}
