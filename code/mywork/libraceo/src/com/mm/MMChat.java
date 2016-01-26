package com.mm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 24个小时，每个时间点对应一个客流数据，求相邻两个小时内客流数据之和最大的时间段和客流总和
 * 
 * @author zhang
 *
 */
public class MMChat {

	public static void main(String[] args) {
		MMChat.sortMap();
	}

	public static void sortMap() {
		int max = 0;// 最大流量总和
		String maxBeginHourStr = "";// 时间段-起
		String maxEndHourStr = "";// 时间段-止
		for (String key : tempMap.keySet()) {
			int beginHour = Integer.parseInt(key.split("\\:")[0]);
			int endHour = beginHour + 1;// 取一小时以后的数据
			String beginHourStr = beginHour + ":00";// 时间段-起
			String endHourStr = endHour + ":00";// 时间段-止
			if (tempMap.containsKey(beginHourStr) && tempMap.containsKey(endHourStr)) {
				int temp = tempMap.get(beginHourStr) + tempMap.get(endHourStr);
				if (temp > max) {// 如果大于存储的最大值，替换总和，起始、终止
					max = temp;
					maxBeginHourStr = beginHourStr;
					maxEndHourStr = endHourStr;
				}
			}
		}
		System.out.println(String.format("[%s]-[%s]流量最大为:[%s]", new Object[] { maxBeginHourStr, maxEndHourStr, max }));
	}

	public static void sortList() {
		int max = 0;// 最大流量总和
		int beginHour = 0;// 时间段-起
		int endHour = 0;// 时间段-止
		for (int i = 0; i < tempList.size() - 1; i++) {
			int temp = tempList.get(i) + tempList.get(i + 1);
			if (temp > max) {
				max = temp;
				beginHour = i + 1;// list索引从0开始，所以时间+1
				endHour = i + 1 + 1;
			}
		}
		System.out.println(String.format("[%s]-[%s]流量最大为:[%s]", new Object[] { beginHour, endHour, max }));
	}

	public static final List<Integer> tempList = new ArrayList<Integer>();
	static {
		tempList.add(100000);
		tempList.add(200000);
		tempList.add(300000);
		tempList.add(400000);
		tempList.add(500000);
		tempList.add(600000);
		tempList.add(700000);
		tempList.add(800000);
		tempList.add(900000);
		tempList.add(1000000);
		tempList.add(1100000);
		tempList.add(1200000);
		tempList.add(1300000);
		tempList.add(1400000);
		tempList.add(1500000);
		tempList.add(1600000);
		tempList.add(1700000);
		tempList.add(1800000);
		tempList.add(1900000);
		tempList.add(2000000);
		tempList.add(210000);
		tempList.add(2200000);
		tempList.add(2300000);
		tempList.add(2400000);
	}

	public static final Map<String, Integer> tempMap = new HashMap<String, Integer>();
	static {
		tempMap.put("0:00", 10000);
		tempMap.put("1:00", 100000);
		tempMap.put("2:00", 200000);
		tempMap.put("3:00", 300000);
		tempMap.put("4:00", 400000);
		tempMap.put("5:00", 500000);
		tempMap.put("6:00", 600000);
		tempMap.put("7:00", 700000);
		tempMap.put("8:00", 800000);
		tempMap.put("9:00", 900000);
		tempMap.put("10:00", 1000000);
		tempMap.put("11:00", 1100000);
		tempMap.put("12:00", 1200000);
		tempMap.put("13:00", 1300000);
		tempMap.put("14:00", 1400000);
		tempMap.put("15:00", 1500000);
		tempMap.put("16:00", 160000000);
		tempMap.put("17:00", 1700000);
		tempMap.put("18:00", 1800000);
		tempMap.put("19:00", 1900000);
		tempMap.put("20:00", 2000000);
		tempMap.put("21:00", 2100000);
		tempMap.put("22:00", 2200000);
		tempMap.put("23:00", 2300000);
		tempMap.put("24:00", 2300000);
	}
}
