package com.nabaztag.api.model;

import com.nabaztag.api.IRabbitCommand;
import com.nabaztag.api.RealRabbitCommand;

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

	public EarPosition getLeftEarPosition() {
		IRabbitCommand rabbitCommand = new RealRabbitCommand(this);
		return rabbitCommand.getLeftEarPosition();
	}

	public EarPosition getRightEarPosition() {
		IRabbitCommand rabbitCommand = new RealRabbitCommand(this);
		return rabbitCommand.getRightEarPosition();
	}

	@Override
	public String toString() {
		return "Rabbit [sn=" + sn + ", token=" + token + "]";
	}

}
