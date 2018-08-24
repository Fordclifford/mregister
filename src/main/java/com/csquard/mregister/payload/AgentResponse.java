package com.csquard.mregister.payload;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.csquard.mregister.model.SalesArea;
import com.csquard.mregister.model.SalesRegion;
import com.csquard.mregister.model.Tdr;
import com.csquard.mregister.payload.UserSummary;

public class AgentResponse {
    private UserSummary createdBy;
	private Long agent_no;		
	private String address;		
	private String town;	
	private String signed_name;	
	private Long signed_contact;
	private Long mobile;	
	private String location;	
	private String device_type;	
	private Long imei_no;	
	private Long id_no;
	private String id_attachment;	
	private String signature;
	private Long tdr_id;
	private Long sales_area_id;
	private Long sales_region_id;
   


	public AgentResponse() {
		
	}

	public AgentResponse(UserSummary createdBy, Long agent_no, String address, String town, String signed_name,
			Long signed_contact, Long mobile, String location, String device_type, Long imei_no, Long id_no,
			String id_attachment, String signature, Long tdr, Long sales_area, Long sales_region) {
		super();
		this.createdBy = createdBy;
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
		this.tdr_id = tdr;
		this.sales_area_id = sales_area;
		this.sales_region_id = sales_region;
	}


    public Long getTdr_id() {
		return tdr_id;
	}

	public void setTdr_id(Long tdr_id) {
		this.tdr_id = tdr_id;
	}

	public Long getSales_area_id() {
		return sales_area_id;
	}

	public void setSales_area_id(Long sales_area_id) {
		this.sales_area_id = sales_area_id;
	}

	public Long getSales_region_id() {
		return sales_region_id;
	}

	public void setSales_region_id(Long sales_region_id) {
		this.sales_region_id = sales_region_id;
	}

	public UserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserSummary createdBy) {
        this.createdBy = createdBy;
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