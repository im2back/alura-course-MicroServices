package br.com.alurafood.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alurafood.order.dto.OrderDto;
import br.com.alurafood.order.dto.OrderItemDto;
import br.com.alurafood.order.model.Order;
import br.com.alurafood.order.model.OrderItem;
import br.com.alurafood.order.model.Status;
import br.com.alurafood.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public Page<Order> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Order findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public OrderDto createOrder(OrderDto dto) {
		LocalDateTime dataHour = LocalDateTime.now();
		Status status = Status.REALIZADO;	
		List<OrderItem> list = new ArrayList<>();
		Order order = new Order(dataHour, status, list);
		
		for(OrderItemDto x :dto.itens()) {		
			var novo = new OrderItem(x, order);  
			order.getItens().add(novo);
		}
		repository.save(order);
		
		return new OrderDto(order, dto.itens());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
