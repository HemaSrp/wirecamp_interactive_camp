package com.wirecamp.assignment.wirecamp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("from")
	private From from;

	@JsonProperty("to")
	private To to;

	@JsonProperty("stations")
	private Stations stations;

	@JsonProperty("connections")
	private List<ConnectionsItem> connections;

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

	public void setStations(Stations stations){
		this.stations = stations;
	}

	public Stations getStations(){
		return stations;
	}

	public void setConnections(List<ConnectionsItem> connections){
		this.connections = connections;
	}

	public List<ConnectionsItem> getConnections(){
		return connections;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"from = '" + from + '\'' + 
			",to = '" + to + '\'' + 
			",stations = '" + stations + '\'' + 
			",connections = '" + connections + '\'' + 
			"}";
		}
}