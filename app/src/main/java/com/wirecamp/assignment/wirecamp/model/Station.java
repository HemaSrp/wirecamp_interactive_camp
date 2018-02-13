package com.wirecamp.assignment.wirecamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Station{

	@JsonProperty("score")
	private Object score;

	@JsonProperty("coordinate")
	private Coordinate coordinate;

	@JsonProperty("distance")
	private Object distance;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	public void setScore(Object score){
		this.score = score;
	}

	public Object getScore(){
		return score;
	}

	public void setCoordinate(Coordinate coordinate){
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate(){
		return coordinate;
	}

	public void setDistance(Object distance){
		this.distance = distance;
	}

	public Object getDistance(){
		return distance;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Station{" + 
			"score = '" + score + '\'' + 
			",coordinate = '" + coordinate + '\'' + 
			",distance = '" + distance + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}