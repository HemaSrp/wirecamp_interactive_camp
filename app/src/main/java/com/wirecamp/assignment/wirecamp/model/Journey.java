package com.wirecamp.assignment.wirecamp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Journey{

	@JsonProperty("number")
	private String number;

	@JsonProperty("name")
	private String name;

	@JsonProperty("passList")
	private List<PassListItem> passList;

	@JsonProperty("capacity2nd")
	private Object capacity2nd;

	@JsonProperty("categoryCode")
	private Object categoryCode;

	@JsonProperty("to")
	private String to;

	@JsonProperty("category")
	private String category;

	@JsonProperty("subcategory")
	private Object subcategory;

	@JsonProperty("operator")
	private String operator;

	@JsonProperty("capacity1st")
	private Object capacity1st;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPassList(List<PassListItem> passList){
		this.passList = passList;
	}

	public List<PassListItem> getPassList(){
		return passList;
	}

	public void setCapacity2nd(Object capacity2nd){
		this.capacity2nd = capacity2nd;
	}

	public Object getCapacity2nd(){
		return capacity2nd;
	}

	public void setCategoryCode(Object categoryCode){
		this.categoryCode = categoryCode;
	}

	public Object getCategoryCode(){
		return categoryCode;
	}

	public void setTo(String to){
		this.to = to;
	}

	public String getTo(){
		return to;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setSubcategory(Object subcategory){
		this.subcategory = subcategory;
	}

	public Object getSubcategory(){
		return subcategory;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperator(){
		return operator;
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
			"Journey{" + 
			"number = '" + number + '\'' + 
			",name = '" + name + '\'' + 
			",passList = '" + passList + '\'' + 
			",capacity2nd = '" + capacity2nd + '\'' + 
			",categoryCode = '" + categoryCode + '\'' + 
			",to = '" + to + '\'' + 
			",category = '" + category + '\'' + 
			",subcategory = '" + subcategory + '\'' + 
			",operator = '" + operator + '\'' + 
			",capacity1st = '" + capacity1st + '\'' + 
			"}";
		}
}