package br.com.alurafood.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.alurafood.order.model.Order;
import br.com.alurafood.order.model.Status;

public record OrderDto(
		Long id,	
		LocalDateTime dateHour,		
		Status status,	
		List<OrderItemDto> itens
		
		) {
	
	public OrderDto(Order order, List<OrderItemDto> dto ) {
		this(order.getId(),order.getDateHour(), order.getStatus(), dto);
	}
}
