package com.csquard.mregister.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.csquard.mregister.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "sales_regions")
public class SalesRegion extends DateAudit {
//	
//	 @OneToOne(fetch = FetchType.LAZY,
//     cascade =  CascadeType.ALL,
//     mappedBy = "salesRegion")
//	  @JsonIgnore
//	 private Asm asm;
	 
//	  
//	 public Agent getAgent() {
//		return agent;
//	}
//
//	public void setAgent(Agent agent) {
//		this.agent = agent;
//	}
//
//	@OneToOne(cascade = CascadeType.ALL,
//	            fetch = FetchType.LAZY,
//	            mappedBy = "salesRegion")
//	  @JsonIgnore
//	    private Agent agent;

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @NotBlank
	 private String name;
	 
	 
	 
	 public SalesRegion( String name) {
		super();
		this.name = name;
	}
	 
		public SalesRegion() {
			super();
		}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}