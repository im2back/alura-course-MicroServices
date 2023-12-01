package br.com.alurafood.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alurafood.payments.dto.PaymentDto;
import br.com.alurafood.payments.model.Payment;
import br.com.alurafood.payments.model.Status;
import br.com.alurafood.payments.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Page<PaymentDto> findAll(Pageable pageable){
		return paymentRepository.findAll(pageable).map(PaymentDto::new);
	}
	
	public PaymentDto findById(Long id) {
		return paymentRepository.findById(id).map(PaymentDto::new).orElseThrow(
				() -> new EntityNotFoundException());
	}
	
	public PaymentDto createPayment(PaymentDto dto) {
		Payment payment = new Payment(dto);
		payment.setStatus(Status.CRIADO);
		paymentRepository.save(payment);
		
		PaymentDto paymentReturn = new PaymentDto(payment);
		return paymentReturn;
	}
	
	  public PaymentDto updatePayment(Long id, PaymentDto dto) {
		  	Payment payment = paymentRepository.findById(id).get();	  	
		  	payment.update(payment, dto);	         
	        payment = paymentRepository.save(payment);	            
			return new PaymentDto(payment);
	    }
	  
	  public void deletePayment(Long id) {
	        paymentRepository.deleteById(id);
	    }
	
	
}