package com.wirecamp.assignment.wirecamp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stations{

	@JsonProperty("from")
	private List<FromItem> from;

	@JsonProperty("to")
	private List<ToItem> to;

	public void setFrom(List<FromItem> from){
		this.from = from;
	}

	public List<FromItem> getFrom(){
		return from;
	}

	public void setTo(List<ToItem> to){
		this.to = to;
	}

	public List<ToItem> getTo(){
		return to;
	}

	@Override
 	public String toString(){
		return 
			"Stations{" + 
			"from = '" + from + '\'' + 
			",to = '" + to + '\'' + 
			"}";
		}
}