package com.nabaztag.api;

import com.nabaztag.api.model.EarPosition;

public interface IRabbitCommand {

	public IRabbitCommand say(String text);

	public IRabbitCommand moveLeftEar(EarPosition earPosition);

	public IRabbitCommand moveRightEar(EarPosition earPosition);

	public RabbitCommandResponse execute();

	public EarPosition getLeftEarPosition();

	public EarPosition getRightEarPosition();

}
