package br.com.alurafood.order.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alurafood.order.dto.OrderDto;
import br.com.alurafood.order.dto.OrderItemDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dateHour;
	
	@Column(name = "column_status")   @Enumerated(EnumType.STRING)
	private Status status; 
	
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
	private List<OrderItem> itens = new ArrayList<>();

	public Order(LocalDateTime dateHour,Status status ,OrderDto dto) throws JsonMappingException, JsonProcessingException {
		super();
		this.dateHour = dateHour;
		this.status = status;
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(dto.itens());

		 List<OrderItemDto> dtolist = objectMapper.readValue(jsonString, new TypeReference<List<OrderItemDto>>() {});

		 dtolist.forEach(x->{
			this.itens.add(new OrderItem(x));
		});
		 


	}

	

	
}
