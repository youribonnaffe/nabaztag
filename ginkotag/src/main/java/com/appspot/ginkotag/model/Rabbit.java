package com.appspot.ginkotag.model;

public class Rabbit {

	private final String sn;
	private final String token;

	public Rabbit(String sn, String token) {
		this.sn = sn;
		this.token = token;
	}

	public String getSn() {
		return sn;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "Rabbit [sn=" + sn + ", token=" + token + "]";
	}

}
