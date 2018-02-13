package com.wirecamp.assignment.wirecamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prognosis{

	@JsonProperty("arrival")
	private Object arrival;

	@JsonProperty("capacity2nd")
	private Object capacity2nd;

	@JsonProperty("departure")
	private String departure;

	@JsonProperty("platform")
	private Object platform;

	@JsonProperty("capacity1st")
	private Object capacity1st;

	public void setArrival(Object arrival){
		this.arrival = arrival;
	}

	public Object getArrival(){
		return arrival;
	}

	public void setCapacity2nd(Object capacity2nd){
		this.capacity2nd = capacity2nd;
	}

	public Object getCapacity2nd(){
		return capacity2nd;
	}

	public void setDeparture(String departure){
		this.departure = departure;
	}

	public String getDeparture(){
		return departure;
	}

	public void setPlatform(Object platform){
		this.platform = platform;
	}

	public Object getPlatform(){
		return platform;
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
			"Prognosis{" + 
			"arrival = '" + arrival + '\'' + 
			",capacity2nd = '" + capacity2nd + '\'' + 
			",departure = '" + departure + '\'' + 
			",platform = '" + platform + '\'' + 
			",capacity1st = '" + capacity1st + '\'' + 
			"}";
		}
}