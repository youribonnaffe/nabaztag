package com.nabaztag.api.model;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + position;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EarPosition other = (EarPosition) obj;
		if (position != other.position)
			return false;
		return true;
	}

}
