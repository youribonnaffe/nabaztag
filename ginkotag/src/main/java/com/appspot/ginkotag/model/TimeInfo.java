package com.appspot.ginkotag.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeInfo {
	private final String lineNumber;
	private final String lineName;
	private final List<String> times;

	public TimeInfo(String lineNumber, String lineName) {
		this.lineNumber = lineNumber;
		this.lineName = lineName;
		times = new ArrayList<String>();
	}

	public void addTime(String strippedLine) {
		times.add(strippedLine);
	}

	public String getLineName() {
		return lineName;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public List<String> getTimes() {
		return Collections.unmodifiableList(times);
	}

	@Override
	public String toString() {
		return "TimeInfo{" + "lineNumber='" + lineNumber + '\''
				+ ", lineName='" + lineName + '\'' + ", times=" + times + '}';
	}

	public static String toTTS(String stop, List<TimeInfo> timeInfos) {
		String txtMsg = "Arrêt" + blank(1) + stop;
		for (TimeInfo timeInfo : timeInfos) {
			if (!timeInfo.getTimes().isEmpty()) {
				txtMsg += blank(10) + timeInfo.getLineNumber() + blank(1)
						+ timeInfo.getLineName() + blank(3);
				for (String time : timeInfo.getTimes()) {
					txtMsg += blank(1) + " " + time;
				}
			}
		}
		return txtMsg;
	}

	private static String blank(int i) {
		String ret = "";
		for (int j = 0; j < i; j++) {
			ret += "\n";
		}
		return ret;
	}

}
