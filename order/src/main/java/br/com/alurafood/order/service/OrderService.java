package br.com.alurafood.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alurafood.order.model.Order;
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
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
