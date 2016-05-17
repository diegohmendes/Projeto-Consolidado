package br.com.fiescfrotas.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConversionUtil {

	public static Time convertDateToTime(Date date, boolean am) {
		if (date == null) {
			return null;
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);

		if (am) {
			// Check to make sure that the hours are indeed am hours
			if (hourOfDay > 11) {
				cal.set(Calendar.HOUR_OF_DAY, hourOfDay - 12);
				date.setTime(cal.getTimeInMillis());
			}
		} else {
			// Check to make sure that the hours are indeed pm hours
			if (cal.get(Calendar.HOUR_OF_DAY) < 11) {
				cal.set(Calendar.HOUR_OF_DAY, hourOfDay + 12);
				date.setTime(cal.getTimeInMillis());
			}
		}
		return new Time(date.getTime());
	}

	public String md5(String input) {
		String md5 = null;

		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			String salt = "Obr1G4do*Pr0f3SsoR#S1lvi0";
			String md5WithSalt = input + salt;
			// Update input string in message digest
			digest.update(md5WithSalt.getBytes(), 0, md5WithSalt.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
	
	public static String convertDateToString(Date data) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		return df.format(data);
	}
}
