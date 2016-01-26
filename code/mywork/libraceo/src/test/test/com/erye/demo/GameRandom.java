package test.com.erye.demo;

import java.util.Random;
import java.util.TreeMap;

public class GameRandom {

	public static void main(String[] args) {
		TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		int index = 100;
		int right = 99;
		Random random = new Random();
		for (int i = 0; i < index; i++) {
			int result = Math.abs(random.nextInt()) % index;
			if (treeMap.containsKey(result)) {
				treeMap.put(result, treeMap.get(result) + 1);
			} else {
				treeMap.put(result, 1);
			}
		}
		for (Integer result : treeMap.keySet()) {
			if (result == right)
				System.out.println("结果:" + result + "-" + treeMap.get(result));
			else
				System.out.println("结果:" + result + "-" + treeMap.get(result));
		}
	}
}
