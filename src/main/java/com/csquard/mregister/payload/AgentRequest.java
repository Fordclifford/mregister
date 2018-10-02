<<<<<<< HEAD
package com.csquard.mregister.payload;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.*;
import javax.validation.constraints.Size;




public class AgentRequest {
	
	
	private Long agent_no;
	
	
    @Size(max = 100)
	private String address;
	
	
    @Size(max = 100)
	private String town;
		
	
    @Size(max = 100)
	private String signed_name;
		
	
	private Long signed_contact;
		
	
	private Long mobile;
	
	
    @Size(max = 100)
	private String location;
		

    @Size(max = 100)
	private String device_type;
		
	
	private Long imei_no;
	
	
	private Long id_no;
		

    @Size(max = 100)
	private String id_attachment;
	
	
    @Size(max = 100)
	private String signature;
	
	
	private Long tdr_id;
	

	private Long sales_area_id;
	
	
	private Long sales_region_id;
	
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

	
	
}
=======
package com.csquard.mregister.payload;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.*;
import javax.validation.constraints.Size;




public class AgentRequest {
	
	
	private Long agent_no;
	
	
    @Size(max = 100)
	private String address;
	
	
    @Size(max = 100)
	private String town;
		
	
    @Size(max = 100)
	private String signed_name;
		
	
	private Long signed_contact;
		
	
	private Long mobile;
	
	
    @Size(max = 100)
	private String location;
		

    @Size(max = 100)
	private String device_type;
		
	
	private Long imei_no;
	
	
	private Long id_no;
		

    @Size(max = 100)
	private String id_attachment;
	
	
    @Size(max = 100)
	private String signature;
	
	
	private Long tdr_id;
	

	private Long sales_area_id;
	
	
	private Long sales_region_id;
	
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

	
	
}
>>>>>>> 97f7824d90a5ad7679914b4efccb4da1151ec8b0
