package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;


public class SaleMinDTO {
		private Long id;
	    private LocalDate date;
	    private Double amount;
	    private String sellerName;  // só no DTO

	    public SaleMinDTO(Sale entity) {
	        this.id = entity.getId();
	        this.date = entity.getDate();
	        this.amount = entity.getAmount();
	        this.sellerName = entity.getSeller().getName(); // pega o nome do seller
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public String getSellerName() {
			return sellerName;
		}

		public void setSellerName(String sellerName) {
			this.sellerName = sellerName;
		}   
	}
