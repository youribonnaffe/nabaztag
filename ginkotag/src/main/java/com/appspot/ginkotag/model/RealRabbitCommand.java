package com.appspot.ginkotag.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import com.appspot.ginkotag.TextSplitterUtil;

public class RealRabbitCommand implements IRabbitCommand {

	private static final Logger LOGGER = Logger
			.getLogger(RealRabbitCommand.class.getName());

	// in secs
	private static final int RESPONSE_TIMEOUT = 10;
	private static final int MAX_TTS_LENGTH = 100;

	private final Rabbit rabbit;
	private String textToSay;
	private EarPosition leftEarPosition;
	private EarPosition rightEarPosition;

	public RealRabbitCommand(Rabbit rabbit) {
		this.rabbit = rabbit;
		this.textToSay = "";
	}

	@Override
	public IRabbitCommand say(String text) {
		textToSay += text;
		return this;
	}

	@Override
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

	private InputStream sendEarPosition() throws MalformedURLException,
			IOException {
		if (leftEarPosition == null && rightEarPosition == null) {
			return null;
		}
		String violetApiUrl = VioletAPI.URL + "?" + VioletAPI.SN_PARAM + "="
				+ rabbit.getSn() + "&" + VioletAPI.TOKEN_PARAM + "="
				+ rabbit.getToken() + "&" + VioletAPI.EARS_PARAM + "=ok";
		if (leftEarPosition != null) {
			violetApiUrl += "&" + VioletAPI.LEFT_EAR_PARAM + "="
					+ leftEarPosition.getPosition();
		}
		if (rightEarPosition != null) {
			violetApiUrl += "&" + VioletAPI.RIGHT_EAR_PARAM + "="
					+ rightEarPosition.getPosition();
		}
		return sendCommandAsApiURL(violetApiUrl);
	}

	private InputStream sendCommandTextToSay() throws MalformedURLException,
			IOException {
		InputStream inputStream = null;
		String[] splittedTextToSay = TextSplitterUtil.split(textToSay,
				MAX_TTS_LENGTH);
		for (String toSay : splittedTextToSay) {
			String violetApiUrl = VioletAPI.URL + "?" + VioletAPI.SN_PARAM
					+ "=" + rabbit.getSn() + "&" + VioletAPI.TOKEN_PARAM + "="
					+ rabbit.getToken() + "&" + VioletAPI.TTS_PARAM + "="
					+ toSay;
			inputStream = sendCommandAsApiURL(violetApiUrl);
		}
		return inputStream;
	}

	private InputStream sendCommandAsApiURL(String violetApiUrl)
			throws MalformedURLException, IOException {
		InputStream inputStream;
		URL nabaztagURL = new URL(violetApiUrl);
		URLConnection nabaztagConnection = nabaztagURL.openConnection();
		nabaztagConnection.setReadTimeout(RESPONSE_TIMEOUT);
		inputStream = nabaztagConnection.getInputStream();
		return inputStream;
	}

	@Override
	public String toString() {
		return "RealRabbitCommand [rabbit=" + rabbit + ", textToSay="
				+ textToSay + ", leftEarPosition=" + leftEarPosition
				+ ", rightEarPosition=" + rightEarPosition + "]";
	}

	@Override
	public IRabbitCommand moveLeftEar(EarPosition earPosition) {
		leftEarPosition = earPosition;
		return this;
	}

	@Override
	public IRabbitCommand moveRightEar(EarPosition earPosition) {
		rightEarPosition = earPosition;
		return this;
	}

	private static class VioletAPI {
		private static final String URL = "http://api.nabaztag.com/vl/FR/api.jsp";
		private static final String SN_PARAM = "sn";
		private static final String TOKEN_PARAM = "token";
		private static final String TTS_PARAM = "tts";
		private static final String EARS_PARAM = "ears";
		private static final String LEFT_EAR_PARAM = "posleft";
		private static final String RIGHT_EAR_PARAM = "posright";
	}

}
