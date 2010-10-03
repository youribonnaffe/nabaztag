package com.nabaztag.api.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.nabaztag.api.IRabbitCommand;
import com.nabaztag.api.RabbitCommandResponse;
import com.nabaztag.api.RealRabbitCommand;

import static org.junit.Assert.assertEquals;

public class RabbitTest {

	private Rabbit rabbit;

	@Before
	public void configureRabbit() throws IOException {
		Properties properties = new Properties();
		properties.load(new InputStreamReader(getClass().getResourceAsStream("/rabbit.properties"), "UTF8"));
		rabbit = new Rabbit(properties.getProperty("sn"), properties.getProperty("token"));
	}

	@Test
	public void testGetLeftEarPosition() throws InterruptedException {
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		EarPosition earPos = new EarPosition(0);
		command.moveLeftEar(earPos);
		RabbitCommandResponse response = command.execute();
		assertEquals(RabbitCommandResponse.EARPOSITIONSENT, response);
		Thread.sleep(2000);
		assertEquals(earPos, rabbit.getLeftEarPosition());
	}

	@Test
	public void testGetRightEarPosition() throws InterruptedException {
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		EarPosition earPos = new EarPosition(0);
		command.moveRightEar(earPos);
		RabbitCommandResponse response = command.execute();
		assertEquals(RabbitCommandResponse.EARPOSITIONSENT, response);
		Thread.sleep(2000);
		assertEquals(earPos, rabbit.getRightEarPosition());
	}

}
