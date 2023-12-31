package br.com.alurafood.payments.dto;



import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alurafood.payments.model.Payment;
import br.com.alurafood.payments.model.Status;

public record PaymentDto(

		
		Long id,
		
		@NotNull
		BigDecimal amount,
		
		@NotBlank @Size(max = 100)
		String name,
		
		@NotBlank @Size(max = 19)
		String number,
		
		@NotBlank @Size(max = 7)
		String expiration,
		
		@NotBlank @Size(min =3,max = 3)
		String code,

		
		Status status,
		
		@NotNull
		Long pedidoId,
		
		@NotNull
	    Long formaDePagamentoId
		
		
		) {
	public PaymentDto(Payment payment){
		   this(payment.getId(),
				payment.getAmount(),
				payment.getName(),
				payment.getNumber(),
				payment.getExpiration(),
				payment.getCode(),
				payment.getStatus(),
				payment.getPedidoId(),
				payment.getFormaDePagamentoId());	
	}

}