package com.csquard.mregister.payload;

import com.csquard.mregister.model.SalesRegion;

public class SalesAreaResponse {
private String name;
private SalesRegion salesRegion;
private Long id;
public SalesAreaResponse() {
	
}

public SalesAreaResponse(String name, SalesRegion salesRegion, Long id) {
	super();
	this.name = name;
	this.salesRegion = salesRegion;
	this.id = id;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public SalesRegion getSalesRegion() {
	return salesRegion;
}
public void setSalesRegion(SalesRegion salesRegion) {
	this.salesRegion = salesRegion;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}


}
