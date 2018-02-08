package com.wirecamp.assignment.wirecamp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectionsItem{

	@JsonProperty("duration")
	private String duration;

	@JsonProperty("transfers")
	private int transfers;

	@JsonProperty("service")
	private Object service;

	@JsonProperty("capacity2nd")
	private Object capacity2nd;

	@JsonProperty("from")
	private From from;

	@JsonProperty("to")
	private To to;

	@JsonProperty("sections")
	private List<SectionsItem> sections;

	@JsonProperty("products")
	private List<String> products;

	@JsonProperty("capacity1st")
	private Object capacity1st;

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setTransfers(int transfers){
		this.transfers = transfers;
	}

	public int getTransfers(){
		return transfers;
	}

	public void setService(Object service){
		this.service = service;
	}

	public Object getService(){
		return service;
	}

	public void setCapacity2nd(Object capacity2nd){
		this.capacity2nd = capacity2nd;
	}

	public Object getCapacity2nd(){
		return capacity2nd;
	}

	public void setFrom(From from){
		this.from = from;
	}

	public From getFrom(){
		return from;
	}

	public void setTo(To to){
		this.to = to;
	}

	public To getTo(){
		return to;
	}

	public void setSections(List<SectionsItem> sections){
		this.sections = sections;
	}

	public List<SectionsItem> getSections(){
		return sections;
	}

	public void setProducts(List<String> products){
		this.products = products;
	}

	public List<String> getProducts(){
		return products;
	}

	public void setCapacity1st(Object capacity1st){
		this.capacity1st = capacity1st;
	}

	public Object getCapacity1st(){
		return capacity1st;
	}

	@Override
 	public String toString(){
		return 
			"ConnectionsItem{" + 
			"duration = '" + duration + '\'' + 
			",transfers = '" + transfers + '\'' + 
			",service = '" + service + '\'' + 
			",capacity2nd = '" + capacity2nd + '\'' + 
			",from = '" + from + '\'' + 
			",to = '" + to + '\'' + 
			",sections = '" + sections + '\'' + 
			",products = '" + products + '\'' + 
			",capacity1st = '" + capacity1st + '\'' + 
			"}";
		}
}