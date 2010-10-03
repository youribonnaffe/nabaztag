package com.appspot.ginkotag.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public enum RabbitCommandResponse {

	MESSAGESENT, NOGOODTOKENORSERIAL, NO_RESPONSE;

	private static final String MESSAGE_NODE = "message";

	public static RabbitCommandResponse readFromInputStream(InputStream response)
			throws IOException, SAXException, ParserConfigurationException {
		BufferedReader br = new BufferedReader(new InputStreamReader(response));

		String line = br.readLine();
		String responseContent = "";
		while (line != null) {
			responseContent += line;
			line = br.readLine();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructeur;
		constructeur = factory.newDocumentBuilder();
		Document document = constructeur.parse(new InputSource(
				new StringReader(responseContent)));

		Element messageElement = document.getElementById(MESSAGE_NODE);
		return RabbitCommandResponse.valueOf(messageElement.getNodeValue());
	}

}
