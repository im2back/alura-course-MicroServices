package br.com.alurafood.payments.model;



import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "payments")
@Entity(name = "Payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private BigDecimal amount;
	
	@NotBlank @Size(max = 100) @Column(name = "name_column")	
	private String name;
	
	@NotBlank @Size(max = 19) @Column(name = "number_column")
	private String number;
	
	@NotBlank @Size(max = 7)
	private String expiration;
	
	@NotBlank @Size(min =3,max = 3) @Column(name = "code_column")
	private String code;

	@Enumerated(EnumType.STRING) @NotBlank @Column(name = "status_column")
	private Status status;
	
	@NotNull @Column(name = "pedido_id")
	private Long pedidoId;
	
	@NotNull @Column(name = "forma_de_pagamento_id")
	private Long formaDePagamentoId; 
	

	
	
	
	
}