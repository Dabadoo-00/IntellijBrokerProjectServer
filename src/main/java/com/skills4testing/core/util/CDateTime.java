package com.skills4testing.core.util;

import java.sql.*;
import java.util.*;

public class CDateTime {

	/**
	 * Returns the current date time in appropriate database format
	 */
	public static Timestamp currentDateTime() throws Exception {
		/* Modified by Saadat 29-08-2001 */
		/*
		 * java.util.Date date = new java.util.Date();
		 * java.text.SimpleDateFormat sdf = new
		 * java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); String
		 * timeStampFormat = sdf.format(date); Timestamp timestamp = null; try{
		 * timestamp = Timestamp.valueOf(timeStampFormat); }catch (Exception e){
		 * CError.throwError(900); } return timestamp;
		 */
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * getStartingDateOfMonthWithYear This function returns the equavalent long
	 * value of year, month, first date of month and 00 hh, 00 mm, 00 ss.
	 * 
	 */

	public static long getStartingDateOfMonthWithYear() {
		Timestamp startAtDateOfMonth = null;
		Calendar calandar = Calendar.getInstance();
		String startDateOfMonth = calandar.get(Calendar.YEAR) + "-"
				+ calandar.get(Calendar.MONTH) + "-" + "1 " + "00:00:00";
		startAtDateOfMonth = Timestamp.valueOf(startDateOfMonth);
		return startAtDateOfMonth.getTime();
	}

	/**
	 * toSystemOutDate
	 * 
	 * This method is used to print out date time to the console or log files.
	 * This method uses the format Day-Month-Year hour:minutes:second AM/PM.
	 */
	public static String toSystemOutDate() {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd-MM-yyyy hh:mm:ss a");
		return sdf.format(date);
	}

	public static int getMonth() {
		Calendar calandar = Calendar.getInstance();
		return calandar.get(Calendar.MONTH) + 1; // 1 is just to avoid the 0 to
													// 11 month of calender
	}

	public static String getTimeFormatInTimestamp(String time) {
		String timeFormat = "";
		String year = "";
		String month = "";
		String date = "";
		StringTokenizer st = new StringTokenizer(time, "/");
		while (st.hasMoreTokens()) {
			date = st.nextToken();
			month = st.nextToken();

			year = st.nextToken();
			break;
		}
		timeFormat = year + "-" + month + "-" + date + " " + "00:00:00.000";
		// String formatMonth = "00";
		// //if(Integer.parseInt(month) < 9)
		// // formatMonth = "0" + month;
		// // else
		// formatMonth = month;
		//
		// String formatDate = "00";
		// if(Integer.parseInt(date) < 9)
		// formatDate = "0" + date;
		// else
		// formatDate = date;
		//
		// // yyyy-mm-dd hh:mm:ss.fffffffff
		//
		// timeFormat = year +"-" + formatMonth +"-" +formatDate + " " +
		// "00:00:00.000";

		return timeFormat;

	}

	public static String getShortDateFormatInTimestamp(String time) {
		String timeFormat = "";
		String year = "";
		String month = "";
		String date = "";
		StringTokenizer st = new StringTokenizer(time, "/");
		while (st.hasMoreTokens()) {
			date = st.nextToken();
			month = st.nextToken();

			year = st.nextToken();
			break;
		}
		timeFormat = year + "-" + month + "-" + date;

		return timeFormat;

	}

}
