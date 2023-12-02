package br.com.alurafood.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.alurafood.order.dto.OrderDto;
import br.com.alurafood.order.model.Order;
import br.com.alurafood.order.model.Status;
import br.com.alurafood.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public List<OrderDto> findAll() {
		List<OrderDto> dto = new ArrayList<>();
	  repository.findAll().forEach(x ->{
		 dto.add(new OrderDto(x));
	  });;

	    return dto;
	}


	public OrderDto findById(Long id) {
		var order = repository.findById(id).get();
		return new OrderDto(order);
	}

	public OrderDto createOrder(OrderDto dto) throws JsonMappingException, JsonProcessingException {
		LocalDateTime dataHour = LocalDateTime.now();
		Status status = Status.REALIZADO;
		Order order = new Order(dataHour, status, dto);
		order.getItens().forEach(x ->{
			x.setOrder(order);
		});
		
		repository.save(order);
		
		return new OrderDto(order);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
