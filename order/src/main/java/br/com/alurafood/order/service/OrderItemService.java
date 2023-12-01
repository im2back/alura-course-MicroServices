package br.com.alurafood.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alurafood.order.model.OrderItem;
import br.com.alurafood.order.repository.OrderItemRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	public Page<OrderItem> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public OrderItem findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
}
