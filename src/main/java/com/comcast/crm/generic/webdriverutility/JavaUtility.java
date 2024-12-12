package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {

		Random ranDom = new Random();
		int ranDomNumber = ranDom.nextInt(5000);

		return ranDomNumber;
	}

	public String getSystemDateYYYYMMDD() {

		Date dateObj = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);

		return date;

	}

	public String getRequiredDateYYYYMMDD(int days) {

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

		// Use Calendar.getInstance() to fetch the system date and time
		Calendar cal = Calendar.getInstance();
		// Add the specified number of days to the current date
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String reqDate = sim.format(cal.getTime());

		return reqDate;
	}

}
