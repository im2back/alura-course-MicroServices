package br.com.alurafood.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.alurafood.order.dto.OrderDto;
import br.com.alurafood.order.service.OrderService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService service;

	@GetMapping
	public ResponseEntity<List<OrderDto>> findAll(){
		var orderDto = service.findAll();		
		return ResponseEntity.ok(orderDto);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto oderDto, UriComponentsBuilder uriBuilder) throws JsonMappingException, JsonProcessingException{
		var orderDto = service.createOrder(oderDto);
		var uri = uriBuilder.path("/orders/{id}").buildAndExpand(orderDto.id()).toUri();
		return ResponseEntity.created(uri).body(orderDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> findById(@PathVariable Long id){
		var orderDto = service.findById(id);		
		return ResponseEntity.ok(orderDto);
	}
	
	
}
