package com.nabaztag.api;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.nabaztag.api.model.EarPosition;

public class RabbitStatusResponse {

	private final EarPosition leftEarPosition;
	private final EarPosition rightEarPosition;

	public RabbitStatusResponse(EarPosition leftEarPosition, EarPosition rightEarPosition) {
		this.leftEarPosition = leftEarPosition;
		this.rightEarPosition = rightEarPosition;
	}

	public EarPosition getLeftEarPosition() {
		return leftEarPosition;
	}

	public EarPosition getRightEarPosition() {
		return rightEarPosition;
	}

	private static final String LEFT_EAR_POSITION_NODE = "leftposition";
	private static final String RIGHT_EAR_POSITION_NODE = "rightposition";

	public static RabbitStatusResponse readFromInputStream(InputStream response) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(response);
		EarPosition leftEarPosition = new EarPosition(Integer.parseInt(document
				.getElementsByTagName(LEFT_EAR_POSITION_NODE).item(0).getTextContent()));
		EarPosition rightEarPosition = new EarPosition(Integer.parseInt(document
				.getElementsByTagName(RIGHT_EAR_POSITION_NODE).item(0).getTextContent()));
		return new RabbitStatusResponse(leftEarPosition, rightEarPosition);
	}

}
