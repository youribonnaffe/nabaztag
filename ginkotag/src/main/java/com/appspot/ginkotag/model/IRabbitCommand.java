package com.appspot.ginkotag.model;

public interface IRabbitCommand {

	public IRabbitCommand say(String text);

	public IRabbitCommand moveLeftEar(EarPosition earMovement);

	public IRabbitCommand moveRightEar(EarPosition earPosition);

	public RabbitCommandResponse execute();

}
