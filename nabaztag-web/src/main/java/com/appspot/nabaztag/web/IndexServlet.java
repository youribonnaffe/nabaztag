package com.appspot.nabaztag.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nabaztag.api.model.Rabbit;

public class IndexServlet extends HttpServlet {

	/**	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sn = req.getParameter("sn");
		String token = req.getParameter("token");

		if (sn != null && token != null) {
			Cookie snCookie = new Cookie("sn", sn);
			Cookie tokenCookie = new Cookie("token", token);
			resp.addCookie(snCookie);
			resp.addCookie(tokenCookie);
			Rabbit rabbit = new Rabbit(sn, token);
			req.setAttribute("rabbit", rabbit);
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}

}
