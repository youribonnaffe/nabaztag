package com.appspot.ginkotag.model;

public class EarPosition {

	private final int position;

	public EarPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "EarPosition [position=" + position + "]";
	}

}
