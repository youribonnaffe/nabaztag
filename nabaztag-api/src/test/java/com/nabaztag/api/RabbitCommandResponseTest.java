package com.nabaztag.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertEquals;

public class RabbitCommandResponseTest {

	@Test
	public void testReadFromInputStream_NOGOODTOKENORSERIAL() throws IOException, SAXException,
			ParserConfigurationException {
		InputStream response = new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<rsp><message>NOGOODTOKENORSERIAL</message>"
				+ "<comment>Your token or serial number are not correct !</comment>" + "</rsp>").getBytes("UTF-8"));
		RabbitCommandResponse result = RabbitCommandResponse.readFromInputStream(response);
		assertEquals(RabbitCommandResponse.NOGOODTOKENORSERIAL, result);
	}

	@Test
	public void testReadFromInputStream_MESSAGESENT() throws IOException, SAXException, ParserConfigurationException {
		InputStream response = new ByteArrayInputStream(
				("<?xml version=\"1.0\" encoding=\"UTF-8\"?><rsp><message>MESSAGESENT</message><comment>Your message has been sent</comment></rsp>")
						.getBytes("UTF-8"));
		RabbitCommandResponse result = RabbitCommandResponse.readFromInputStream(response);
		assertEquals(RabbitCommandResponse.MESSAGESENT, result);
	}
}
