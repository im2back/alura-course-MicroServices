 package br.com.alurafood.order.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.alurafood.order.dto.PaymentDto;

@Component
public class PaymentListener {

	@RabbitListener(queues = "pagamentos.detalhes-pedido")
	public void receberMenssagem(PaymentDto dto) {
		
		if(dto.id()>1) {
			throw new RuntimeException("Falhou");
		}
		
		System.out.println("======>  Pagamento <=======");
		System.out.println("Dados do pagamento:");
		System.out.println("Id: " + dto.id());
		System.out.println("Numero do pedido: " + dto.pedidoId());
		System.out.println("Valor do pagamento: " + dto.amount());
		
	}
}
