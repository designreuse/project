package com.erye.utils.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VeDate {
	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return���ض�ʱ���ʽ yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateFormat(String dateStr) {
		Date currentTime = strToDateLong(dateStr);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mms");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * ����ʱ���ʽ�ַ�ת��Ϊʱ�� yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

}