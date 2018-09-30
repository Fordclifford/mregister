package com.csquard.mregister.payload;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SalesAreaRequest {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
    
    private Long sales_region_id;
    
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSales_region_id() {
		return sales_region_id;
	}

	public void setSales_region_id(Long sales_region_id) {
		this.sales_region_id = sales_region_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
