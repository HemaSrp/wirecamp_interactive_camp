package com.wirecamp.assignment.wirecamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class To{

	@JsonProperty("arrivalTimestamp")
	private String arrivalTimestamp;

	@JsonProperty("delay")
	private Object delay;

	@JsonProperty("arrival")
	private String arrival;

	@JsonProperty("departureTimestamp")
	private Object departureTimestamp;

	@JsonProperty("station")
	private Station station;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("departure")
	private Object departure;

	@JsonProperty("realtimeAvailability")
	private Object realtimeAvailability;

	@JsonProperty("prognosis")
	private Prognosis prognosis;

	@JsonProperty("platform")
	private String platform;

	public void setArrivalTimestamp(String arrivalTimestamp){
		this.arrivalTimestamp = arrivalTimestamp;
	}

	public String getArrivalTimestamp(){
		return arrivalTimestamp;
	}

	public void setDelay(Object delay){
		this.delay = delay;
	}

	public Object getDelay(){
		return delay;
	}

	public void setArrival(String arrival){
		this.arrival = arrival;
	}

	public String getArrival(){
		return arrival;
	}

	public void setDepartureTimestamp(Object departureTimestamp){
		this.departureTimestamp = departureTimestamp;
	}

	public Object getDepartureTimestamp(){
		return departureTimestamp;
	}

	public void setStation(Station station){
		this.station = station;
	}

	public Station getStation(){
		return station;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setDeparture(Object departure){
		this.departure = departure;
	}

	public Object getDeparture(){
		return departure;
	}

	public void setRealtimeAvailability(Object realtimeAvailability){
		this.realtimeAvailability = realtimeAvailability;
	}

	public Object getRealtimeAvailability(){
		return realtimeAvailability;
	}

	public void setPrognosis(Prognosis prognosis){
		this.prognosis = prognosis;
	}

	public Prognosis getPrognosis(){
		return prognosis;
	}

	public void setPlatform(String platform){
		this.platform = platform;
	}

	public String getPlatform(){
		return platform;
	}

	@Override
 	public String toString(){
		return 
			"To{" + 
			"arrivalTimestamp = '" + arrivalTimestamp + '\'' + 
			",delay = '" + delay + '\'' + 
			",arrival = '" + arrival + '\'' + 
			",departureTimestamp = '" + departureTimestamp + '\'' + 
			",station = '" + station + '\'' + 
			",location = '" + location + '\'' + 
			",departure = '" + departure + '\'' + 
			",realtimeAvailability = '" + realtimeAvailability + '\'' + 
			",prognosis = '" + prognosis + '\'' + 
			",platform = '" + platform + '\'' + 
			"}";
		}
}