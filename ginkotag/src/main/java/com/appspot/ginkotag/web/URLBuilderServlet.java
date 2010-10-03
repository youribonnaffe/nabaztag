package com.appspot.ginkotag.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class URLBuilderServlet extends HttpServlet {

	/**	 */
	private static final long serialVersionUID = 1L;

	private static final String NABAZTAG_SN_PARAM = "nabaztag_sn";
	private static final String NABAZTAG_TOKEN_PARAM = "nabaztag_token";
	private static final String NABAZTAG_STOP_PARAM = "nabaztag_stop";
	private static final String GINKO_URL_PARAM = "GINKO_URL";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGetOrPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGetOrPost(req, resp);
	}

	private void doGetOrPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sn = req.getParameter(NABAZTAG_SN_PARAM);
		String token = req.getParameter(NABAZTAG_TOKEN_PARAM);
		String stop = req.getParameter(NABAZTAG_STOP_PARAM);

		if (sn != null && token != null && stop != null) {
			req.setAttribute(GINKO_URL_PARAM,
					GinkoTimesWS.getURL(sn, token, stop));
		}

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}

}
