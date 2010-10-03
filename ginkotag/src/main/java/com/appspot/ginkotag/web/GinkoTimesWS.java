package com.appspot.ginkotag.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.ginkotag.BusinessException;
import com.appspot.ginkotag.GinkoWebScrapper;
import com.appspot.ginkotag.model.IRabbitCommand;
import com.appspot.ginkotag.model.Rabbit;
import com.appspot.ginkotag.model.RabbitCommandResponse;
import com.appspot.ginkotag.model.RealRabbitCommand;
import com.appspot.ginkotag.model.TimeInfo;

public class GinkoTimesWS extends HttpServlet {
	/**	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(GinkoTimesWS.class
			.getName());

	private static final String WS_PATH = "ginkoTimesWS.do";
	private static final String STOP_PARAM = "stop";
	private static final String TOKEN_PARAM = "token";
	private static final String SN_PARAM = "sn";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String sn = req.getParameter(SN_PARAM);
		String token = req.getParameter(TOKEN_PARAM);

		String stop = req.getParameter(STOP_PARAM);
		stop = URLEncoder.encode(stop, "UTF-8");

		List<TimeInfo> timeInfos;
		try {
			timeInfos = new GinkoWebScrapper(stop).extractTimes();
			LOGGER.info(timeInfos.toString());
			String txtMsg = TimeInfo.toTTS(stop, timeInfos);
			txtMsg = URLEncoder.encode(txtMsg, "UTF-8");

			Rabbit rabbit = new Rabbit(sn, token);
			IRabbitCommand rabbitCommand = new RealRabbitCommand(rabbit);
			RabbitCommandResponse commandeResponse = rabbitCommand.say(txtMsg)
					.execute();
			resp.setContentType("text/plain");
			resp.getWriter().println(commandeResponse);
		} catch (BusinessException e) {
			LOGGER.warning("Failed to retrieve time infos " + e);
		}
	}

	public static String getURL(String sn, String token, String stop)
			throws UnsupportedEncodingException {
		return WS_PATH + "?" + SN_PARAM + "=" + sn + "&" + TOKEN_PARAM + "="
				+ token + "&" + STOP_PARAM + "=" + stop;
	}
}
