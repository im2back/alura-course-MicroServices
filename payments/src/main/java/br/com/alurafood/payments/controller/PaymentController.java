package br.com.alurafood.payments.controller;



import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alurafood.payments.dto.PaymentDto;
import br.com.alurafood.payments.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	@GetMapping
	public ResponseEntity<Page<PaymentDto>> findAll(@Valid @PageableDefault(size = 10) Pageable pageable){	
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<PaymentDto> findById(@PathVariable @NotNull Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto dto, UriComponentsBuilder uriBuilder){
		PaymentDto paymentDtoReturn = service.createPayment(dto);
			URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(paymentDtoReturn.id()).toUri();
				return ResponseEntity.created(uri).body(paymentDtoReturn);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id, @Valid @RequestBody   PaymentDto dto, UriComponentsBuilder uriBuilder){
		PaymentDto paymentUpdated = service.updatePayment(id,dto);
				return ResponseEntity.ok(paymentUpdated);
	}
	
	
	@PatchMapping("/{id}/confirmar")
	@CircuitBreaker(name = "atualizaPedido", fallbackMethod = "pagamentoAutorizadoComIntegracaoPendente")
    public void confirmPagamento(@PathVariable @NotNull Long id){
        service.confirmPayment(id);
    }
	
	   public void pagamentoAutorizadoComIntegracaoPendente(Long id, Exception e){
	        service.alteraStatus(id);
	    }
	
}



















