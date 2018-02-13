package com.wirecamp.assignment.wirecamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SectionsItem{

	@JsonProperty("journey")
	private Journey journey;

	@JsonProperty("arrival")
	private Arrival arrival;

	@JsonProperty("departure")
	private Departure departure;

	@JsonProperty("walk")
	private Object walk;

	public void setJourney(Journey journey){
		this.journey = journey;
	}

	public Journey getJourney(){
		return journey;
	}

	public void setArrival(Arrival arrival){
		this.arrival = arrival;
	}

	public Arrival getArrival(){
		return arrival;
	}

	public void setDeparture(Departure departure){
		this.departure = departure;
	}

	public Departure getDeparture(){
		return departure;
	}

	public void setWalk(Object walk){
		this.walk = walk;
	}

	public Object getWalk(){
		return walk;
	}

	@Override
 	public String toString(){
		return 
			"SectionsItem{" + 
			"journey = '" + journey + '\'' + 
			",arrival = '" + arrival + '\'' + 
			",departure = '" + departure + '\'' + 
			",walk = '" + walk + '\'' + 
			"}";
		}
}