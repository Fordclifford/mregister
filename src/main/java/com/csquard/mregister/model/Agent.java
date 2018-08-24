package com.csquard.mregister.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.csquard.mregister.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "agents")
@JsonIgnoreProperties("agents")
//, uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
//            "agent_no"
//        }),
//        @UniqueConstraint(columnNames = {
//            "imei_no"
//        })
//})

public class Agent extends UserDateAudit   {
	public Long getSalesRegionId() {
		return salesRegionId;
	}

	public void setSalesRegionId(Long salesRegionId) {
		this.salesRegionId = salesRegionId;
	}

	public Long getSalesAreaId() {
		return salesAreaId;
	}

	public void setSalesAreaId(Long salesAreaId) {
		this.salesAreaId = salesAreaId;
	}

	public Long getTdrId() {
		return tdrId;
	}

	public void setTdrId(Long tdrId) {
		this.tdrId = tdrId;
	}

	/**
	 * 
	 */	
	
	
	@Column(name = "sales_region_id",nullable=false)
	 private Long salesRegionId;
	

	@Column(name = "sales_area_id",nullable=false)
	 private Long salesAreaId;
	
	@Column(name = "tdr_id",nullable=false)
	 private Long tdrId;	

	
	@Id
	@Column(nullable = false)
	private Long agent_no;
	
	@Column(name = "address")
	private String address;
	
	@Column(nullable = false,name = "town")
	private String town;
		
	@Column(nullable = false,name = "signed_name")
	private String signed_name;
		
	@Column(nullable = false,name = "signed_contact")
	private Long signed_contact;
		
	@Column(nullable = true,name = "mobile")
	private Long mobile;
	
	@Column(nullable = false,name = "location")
	private String location;
		
	@Column(nullable = false,name = "device_type")
	private String device_type;
		
	@Column(nullable = false,name = "imei_no")
	private Long imei_no;
	
	@Column(nullable = false,name = "id_no")
	private Long id_no;
		
	@Column(name = "id_attachment")
	private String id_attachment;
	
	@Column(name = "signature")
	private String signature;

	
	public Agent() {
		
	}

	public Agent(Long salesRegionId, Long salesAreaId, Long tdrId, Long agent_no, String address, String town,
			String signed_name, Long signed_contact, Long mobile, String location, String device_type, Long imei_no,
			Long id_no, String id_attachment, String signature) {
		super();
		this.salesRegionId = salesRegionId;
		this.salesAreaId = salesAreaId;
		this.tdrId = tdrId;
		this.agent_no = agent_no;
		this.address = address;
		this.town = town;
		this.signed_name = signed_name;
		this.signed_contact = signed_contact;
		this.mobile = mobile;
		this.location = location;
		this.device_type = device_type;
		this.imei_no = imei_no;
		this.id_no = id_no;
		this.id_attachment = id_attachment;
		this.signature = signature;
	}

	public Long getAgent_no() {
		return agent_no;
	}

	public void setAgent_no(Long agent_no) {
		this.agent_no = agent_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getSigned_name() {
		return signed_name;
	}

	public void setSigned_name(String signed_name) {
		this.signed_name = signed_name;
	}

	public Long getSigned_contact() {
		return signed_contact;
	}

	public void setSigned_contact(Long signed_contact) {
		this.signed_contact = signed_contact;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public Long getImei_no() {
		return imei_no;
	}

	public void setImei_no(Long imei_no) {
		this.imei_no = imei_no;
	}

	public Long getId_no() {
		return id_no;
	}

	public void setId_no(Long id_no) {
		this.id_no = id_no;
	}

	public String getId_attachment() {
		return id_attachment;
	}

	public void setId_attachment(String id_attachment) {
		this.id_attachment = id_attachment;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
}
