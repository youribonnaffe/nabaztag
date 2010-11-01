package com.appspot.nabaztag.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nabaztag.api.IRabbitCommand;
import com.nabaztag.api.RealRabbitCommand;
import com.nabaztag.api.model.EarPosition;
import com.nabaztag.api.model.Rabbit;

public class EarsControlServlet extends HttpServlet {

	/**	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Rabbit rabbit = (Rabbit) req.getAttribute("rabbit");
		System.out.println(rabbit);
		IRabbitCommand command = new RealRabbitCommand(rabbit);
		int leftPosition = Integer.parseInt(req.getParameter("left_position"));
		command.moveLeftEar(new EarPosition(leftPosition));
		int rightPosition = Integer.parseInt(req.getParameter("right_position"));
		command.moveRightEar(new EarPosition(rightPosition));
		command.execute();
		// TODO async
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ears_control.jsp");
		dispatcher.forward(req, resp);
	}

}
