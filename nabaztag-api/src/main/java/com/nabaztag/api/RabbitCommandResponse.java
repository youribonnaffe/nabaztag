package com.nabaztag.api;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public enum RabbitCommandResponse {

	MESSAGESENT, NOGOODTOKENORSERIAL, NO_RESPONSE, TTSSENT, EARPOSITIONSENT;

	private static final String MESSAGE_NODE = "message";
	private static final String COMMENT_NODE = "comment";

	private String comment;

	public static RabbitCommandResponse readFromInputStream(InputStream response) throws IOException, SAXException,
			ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(response);
		Node node = document.getElementsByTagName(MESSAGE_NODE).item(0);
		RabbitCommandResponse enumValue = RabbitCommandResponse.valueOf(node.getTextContent());
		enumValue.comment = document.getElementsByTagName(COMMENT_NODE).item(0).getTextContent();
		return enumValue;
	}

	@Override
	public String toString() {
		return super.toString() + (comment == null ? "" : "(" + comment + ")");
	}

}
