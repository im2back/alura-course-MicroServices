package br.com.alurafood.order.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alurafood.order.model.Order;
import br.com.alurafood.order.model.OrderItem;
import br.com.alurafood.order.model.Status;

public record OrderDto(
		Long id,	
		LocalDateTime dateHour,		
		Status status,	
		
		List<OrderItemDto> itens
		
		) {
	
	public OrderDto(Order order) {
		this(order.getId(),order.getDateHour(),order.getStatus(), convert(order));
	}
	
	private static List<OrderItemDto> convert(Order order){
			List<OrderItem> orderlist = order.getItens();
			List<OrderItemDto> dtolist = new ArrayList<>();	
			
			for(OrderItem x :orderlist ) {
				dtolist.add(new OrderItemDto( x.getQuantity(), x.getDescription()));
			}
			return dtolist;
		
	}
}
