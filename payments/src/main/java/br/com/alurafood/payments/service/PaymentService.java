package br.com.alurafood.payments.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alurafood.payments.dto.PaymentDto;
import br.com.alurafood.payments.http.OrderClient;
import br.com.alurafood.payments.model.Payment;
import br.com.alurafood.payments.model.Status;
import br.com.alurafood.payments.repository.PaymentRepository;


@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	@Autowired
	private OrderClient order;
	
	public Page<PaymentDto> findAll(Pageable pageable){
		return repository.findAll(pageable).map(PaymentDto::new);
	}
	
	public PaymentDto findById(Long id) {
		return repository.findById(id).map(PaymentDto::new).orElseThrow(
				() -> new RuntimeException());
	}
	
	public PaymentDto createPayment(PaymentDto dto) {
		Payment payment = new Payment(dto);
		payment.setStatus(Status.CRIADO);
		repository.save(payment);
		
		PaymentDto paymentReturn = new PaymentDto(payment);
		return paymentReturn;
	}
	
	  public PaymentDto updatePayment(Long id, PaymentDto dto) {
		  	Payment payment = repository.findById(id).get();	  	
		  	payment.update(payment, dto);	         
	        payment = repository.save(payment);	            
			return new PaymentDto(payment);
	    }
	  
	   public void confirmPayment(Long id){
	        Optional<Payment> payment = repository.findById(id);

	        if (!payment.isPresent()) {
	            throw new RuntimeException();
	        }

	        payment.get().setStatus(Status.CONFIRMADO);
	        repository.save(payment.get());
	        order.updatePayment(payment.get().getPedidoId());
	    }
	
	
}