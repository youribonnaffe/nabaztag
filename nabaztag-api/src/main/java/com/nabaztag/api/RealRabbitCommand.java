package com.nabaztag.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import com.nabaztag.api.model.EarPosition;
import com.nabaztag.api.model.Rabbit;
import com.nabaztag.api.model.VioletAPI;
import com.nabaztag.api.util.TextSplitterUtil;

public class RealRabbitCommand implements IRabbitCommand {

	private static final Logger LOGGER = Logger.getLogger(RealRabbitCommand.class.getName());

	// in ms
	private static final int RESPONSE_TIMEOUT = 10000;
	private static final int MAX_TTS_LENGTH = 100;

	private final Rabbit rabbit;
	private String textToSay;
	private EarPosition leftEarPosition;
	private EarPosition rightEarPosition;

	public RealRabbitCommand(Rabbit rabbit) {
		this.rabbit = rabbit;
		this.textToSay = "";
	}

	public IRabbitCommand say(String text) {
		textToSay += text;
		return this;
	}

	public RabbitCommandResponse execute() {
		try {
			LOGGER.info("Sending command to real rabbit " + this);
			InputStream response = sendCommand();
			return RabbitCommandResponse.readFromInputStream(response);
		} catch (Exception e) {
			LOGGER.warning("Error occurred when executing rabbit command " + e);
		}
		return RabbitCommandResponse.NO_RESPONSE;
	}

	private InputStream sendCommand() throws MalformedURLException, IOException {
		InputStream inputStreamEars = sendEarPosition();
		InputStream inputStreamText = sendCommandTextToSay();
		if (inputStreamText != null)
			return inputStreamText;
		return inputStreamEars;
	}

	private InputStream sendEarPosition() throws MalformedURLException, IOException {
		if (leftEarPosition == null && rightEarPosition == null) {
			return null;
		}
		String violetApiUrl = getVioletApiUrlStart();
		if (leftEarPosition != null) {
			violetApiUrl += "&" + VioletAPI.LEFT_EAR_PARAM + "=" + leftEarPosition.getPosition();
		}
		if (rightEarPosition != null) {
			violetApiUrl += "&" + VioletAPI.RIGHT_EAR_PARAM + "=" + rightEarPosition.getPosition();
		}
		return sendCommandAsApiURL(violetApiUrl);
	}

	private InputStream sendCommandTextToSay() throws MalformedURLException, IOException {
		InputStream inputStream = null;
		String[] splittedTextToSay = TextSplitterUtil.split(textToSay, MAX_TTS_LENGTH);
		for (String toSay : splittedTextToSay) {
			String violetApiUrl = getVioletApiUrlStart() + VioletAPI.TTS_PARAM + "=" + toSay;
			inputStream = sendCommandAsApiURL(violetApiUrl);
		}
		return inputStream;
	}

	public IRabbitCommand moveLeftEar(EarPosition earPosition) {
		leftEarPosition = earPosition;
		return this;
	}

	public IRabbitCommand moveRightEar(EarPosition earPosition) {
		rightEarPosition = earPosition;
		return this;
	}

	public EarPosition getLeftEarPosition() {
		try {
			LOGGER.info("Getting left ear position");
			String violetApiUrl = getVioletApiUrlStart() + VioletAPI.EARS_PARAM + "=ok";
			InputStream response = sendCommandAsApiURL(violetApiUrl);
			return RabbitStatusResponse.readFromInputStream(response).getLeftEarPosition();
		} catch (Exception e) {
			LOGGER.warning("Error occurred when getting left ear postion" + e);
		}
		return null;
	}

	public EarPosition getRightEarPosition() {
		try {
			LOGGER.info("Getting right ear position");
			String violetApiUrl = getVioletApiUrlStart() + VioletAPI.EARS_PARAM + "=ok";
			InputStream response = sendCommandAsApiURL(violetApiUrl);
			return RabbitStatusResponse.readFromInputStream(response).getRightEarPosition();
		} catch (Exception e) {
			LOGGER.warning("Error occurred when getting right ear position" + e);
		}
		return null;
	}

	@Override
	public String toString() {
		return "RealRabbitCommand [rabbit=" + rabbit + ", textToSay=" + textToSay + ", leftEarPosition="
				+ leftEarPosition + ", rightEarPosition=" + rightEarPosition + "]";
	}

	private String getVioletApiUrlStart() {
		return VioletAPI.URL + "?" + VioletAPI.SN_PARAM + "=" + rabbit.getSn() + "&" + VioletAPI.TOKEN_PARAM + "="
				+ rabbit.getToken() + "&";
	}

	private InputStream sendCommandAsApiURL(String violetApiUrl) throws MalformedURLException, IOException {
		InputStream inputStream;
		URL nabaztagURL = new URL(violetApiUrl);
		URLConnection nabaztagConnection = nabaztagURL.openConnection();
		nabaztagConnection.setReadTimeout(RESPONSE_TIMEOUT);
		inputStream = nabaztagConnection.getInputStream();
		return inputStream;
	}

}
