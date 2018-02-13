package com.wirecamp.assignment.wirecamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate{

	@JsonProperty("x")
	private double X;

	@JsonProperty("y")
	private double Y;

	@JsonProperty("type")
	private String type;

	public void setX(double X){
		this.X = X;
	}

	public double getX(){
		return X;
	}

	public void setY(double Y){
		this.Y = Y;
	}

	public double getY(){
		return Y;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"Coordinate{" + 
			"x = '" + X + '\'' + 
			",y = '" + Y + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}