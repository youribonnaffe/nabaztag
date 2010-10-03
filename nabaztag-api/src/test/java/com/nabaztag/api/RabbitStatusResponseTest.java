package com.nabaztag.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.nabaztag.api.model.EarPosition;

import static org.junit.Assert.assertEquals;

public class RabbitStatusResponseTest {

	@Test
	public void testReadFromInputStream() throws IOException, SAXException, ParserConfigurationException {
		InputStream response = new ByteArrayInputStream(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><rsp><message>POSITIONEAR</message><leftposition>8</leftposition><rightposition>10</rightposition></rsp>"
						.getBytes("UTF-8"));
		RabbitStatusResponse result = RabbitStatusResponse.readFromInputStream(response);
		assertEquals(new EarPosition(8), result.getLeftEarPosition());
		assertEquals(new EarPosition(10), result.getRightEarPosition());
	}

}
