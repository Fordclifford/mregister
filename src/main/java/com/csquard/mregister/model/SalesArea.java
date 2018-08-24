package com.csquard.mregister.model;

import javax.persistence.*;
import com.csquard.mregister.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sales_areas")
public class SalesArea extends DateAudit{
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @Column(name = "name",nullable=false)
	   private String name;
	 
//	  @OneToOne(cascade = CascadeType.ALL,
//	            fetch = FetchType.LAZY,
//	            mappedBy = "salesArea")
//	  @JsonIgnore
//	    private Agent agent;
	
//	 
//	 @OneToOne(fetch = FetchType.LAZY,
//    	     cascade =  CascadeType.ALL,
//    	     mappedBy = "salesArea")
//	 @JsonIgnore
//    	private Tdr tdr;
	 
//		@ManyToOne(fetch = FetchType.LAZY)
//		@JoinColumn (name="", nullable = false)
//		 @JsonIgnore
//		private SalesRegion salesRegion; 
	 
		 @Column(name = "sales_region_id",nullable=false)
		 private Long salesRegionId;
		 
		 
//		
//	 public Agent getAgent() {
//			return agent;
//		}
//
//
//		public void setAgent(Agent agent) {
//			this.agent = agent;
//		}


		public Long getSales_region_id() {
			return salesRegionId;
		}


		public void setSales_region_id(Long sales_region_id) {
			this.salesRegionId = sales_region_id;
		}

		
	public SalesArea(String name, Long salesRegionId) {
			super();
			this.name = name;
			this.salesRegionId = salesRegionId;
		}


	public SalesArea() {
			
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
