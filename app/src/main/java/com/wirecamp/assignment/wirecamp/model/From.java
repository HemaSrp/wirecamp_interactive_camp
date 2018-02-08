package com.wirecamp.assignment.wirecamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class From{

	@JsonProperty("arrivalTimestamp")
	private String arrivalTimestamp;

	@JsonProperty("delay")
	private int delay;

	@JsonProperty("arrival")
	private Object arrival;

	@JsonProperty("departureTimestamp")
	private String departureTimestamp;

	@JsonProperty("station")
	private Station station;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("departure")
	private String departure;

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

	public void setDelay(int delay){
		this.delay = delay;
	}

	public int getDelay(){
		return delay;
	}

	public void setArrival(Object arrival){
		this.arrival = arrival;
	}

	public Object getArrival(){
		return arrival;
	}

	public void setDepartureTimestamp(String departureTimestamp){
		this.departureTimestamp = departureTimestamp;
	}

	public String getDepartureTimestamp(){
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

	public void setDeparture(String departure){
		this.departure = departure;
	}

	public String getDeparture(){
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
			"From{" + 
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