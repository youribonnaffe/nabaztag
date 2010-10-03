package com.nabaztag.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nabaztag.api.model.EarPosition;
import com.nabaztag.api.model.Rabbit;

import static org.junit.Assert.assertEquals;

public class RealRabbitCommandTest {

	private static final int DEFAULT_SLEEP_TIME = 2000;

	private Rabbit rabbit;

	@Before
	public void configureRabbit() throws IOException {
		Properties properties = new Properties();
		properties.load(new InputStreamReader(getClass().getResourceAsStream("/rabbit.properties"), "UTF8"));
		rabbit = new Rabbit(properties.getProperty("sn"), properties.getProperty("token"));
	}

	@After
	public void sleepBetweenTests() throws InterruptedException {
		Thread.sleep(DEFAULT_SLEEP_TIME);
	}

	@Test
	public void testSay() throws IOException {
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		command.say("test");
		RabbitCommandResponse response = command.execute();
		assertEquals(RabbitCommandResponse.TTSSENT, response);
	}

	@Test
	public void testMoveLeftEar() throws IOException {
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		command.moveLeftEar(new EarPosition(12));
		RabbitCommandResponse response = command.execute();
		assertEquals(RabbitCommandResponse.EARPOSITIONSENT, response);
	}

	@Test
	public void testMoveRightEar() throws IOException {
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		command.moveRightEar(new EarPosition(8));
		RabbitCommandResponse response = command.execute();
		assertEquals(RabbitCommandResponse.EARPOSITIONSENT, response);
	}

	@Test
	public void testResetEars() throws IOException {
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		command.moveRightEar(new EarPosition(0));
		command.moveLeftEar(new EarPosition(0));
		RabbitCommandResponse response = command.execute();
		assertEquals(RabbitCommandResponse.EARPOSITIONSENT, response);
	}

}
