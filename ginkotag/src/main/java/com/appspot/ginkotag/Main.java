package com.appspot.ginkotag;

import com.appspot.ginkotag.model.EarPosition;
import com.appspot.ginkotag.model.IRabbitCommand;
import com.appspot.ginkotag.model.Rabbit;
import com.appspot.ginkotag.model.RealRabbitCommand;

public class Main {

	public static void main(String[] args) {
		Rabbit rabbit = new Rabbit("002185BA6A34", "1228730228");
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		command.moveLeftEar(new EarPosition(4));
		command.moveRightEar(new EarPosition(12));
		command.execute();
	}
}
