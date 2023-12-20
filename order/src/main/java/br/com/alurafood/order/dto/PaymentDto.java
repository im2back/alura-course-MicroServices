package br.com.alurafood.order.dto;

import java.math.BigDecimal;

public record PaymentDto(Long id,

		BigDecimal amount,

		String name,

		String number,

		String expiration,

		String code,

		StatusPagamento status,

		 Long pedidoId,

		 Long formaDePagamentoId
		) {

}
