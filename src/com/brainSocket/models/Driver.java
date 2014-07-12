package com.brainSocket.models;

import com.google.android.gms.maps.model.LatLng;

public class Driver  extends User{

	public Driver(int id, String name, LatLng loc, int destId) {
		super(id, name, loc, destId);
		// TODO Auto-generated constructor stub
	}

	private double cost;

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
