package com.appspot.ginkotag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.appspot.ginkotag.model.TimeInfo;

public class GinkoWebScrapper {

	private static final Logger LOGGER = Logger
			.getLogger(GinkoWebScrapper.class.getName());

	private URL ginkoURL;
	private final String stop;

	public GinkoWebScrapper(String stop) {
		this.stop = stop;
		try {
			String ginkoURLAsString = "http://www.ginkotempo.com/TempoMobile/tempoMobile.do?choix_station="
					+ stop + "&methode=afficheStation";
			ginkoURL = new URL(ginkoURLAsString);
		} catch (MalformedURLException e) {
			LOGGER.warning("Failed to build ginko url " + e);
		}
	}

	public List<TimeInfo> extractTimes() throws BusinessException {
		LOGGER.info("Searching time infos for stop " + stop);
		BufferedReader fis = null;
		try {
			fis = getGinkoResponse();
			readUntilTimeInfosStart(fis);
			List<TimeInfo> timeInfos = readTimeInfos(fis);
			return timeInfos;
		} catch (IOException e) {
			LOGGER.warning("Failed to get time infos " + e);
			throw new BusinessException(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				LOGGER.info("Failed to close ginko url response " + e);
				throw new BusinessException(e);
			}
		}
	}

	private List<TimeInfo> readTimeInfos(BufferedReader fis) throws IOException {
		List<TimeInfo> timeInfos = new ArrayList<TimeInfo>();
		TimeInfo timeInfo = null;
		String line = fis.readLine();
		while (line != null && !line.contains("</table")) {
			if (line.contains("<td")) {
				String[] stringParts = line.split("</div>");
				String strippedLine = stringParts[0].replaceAll("<.*?>", "")
						.trim();
				String strippedLine2 = null;
				if (stringParts.length > 2) {
					strippedLine2 = stringParts[1].replaceAll("<.*?>", "")
							.trim();
					timeInfo = new TimeInfo(strippedLine, strippedLine2);

					timeInfos.add(timeInfo);
				} else {
					if (!strippedLine.isEmpty()) {
						strippedLine.replaceAll("min", "minutes");
						timeInfo.addTime(strippedLine);
					}
				}
			}
			line = fis.readLine();
		}
		return timeInfos;
	}

	private void readUntilTimeInfosStart(BufferedReader fis) throws IOException {
		String line = fis.readLine();
		while (line != null && !line.contains("<table")) {
			line = fis.readLine();
		}
	}

	private BufferedReader getGinkoResponse()
			throws UnsupportedEncodingException, IOException {
		InputStreamReader urlInput = new InputStreamReader(
				ginkoURL.openStream(), "UTF-8");
		BufferedReader fis = new BufferedReader(urlInput);
		return fis;
	}

}