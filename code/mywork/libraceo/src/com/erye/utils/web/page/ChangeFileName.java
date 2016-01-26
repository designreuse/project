package com.erye.utils.web.page;

import java.io.File;

public class ChangeFileName {
	//static String fileUrl = "E:\\�ٶ���ͬ����\\�������\\��˾��Ŀ\\658����\\��������\\2015-09-24��Ͷ�ʵ����?\\�滻��ַ\\�°�Э��\\";
	static String fileUrl = "E:\\�ٶ���ͬ����\\�������\\��˾��Ŀ\\658����\\��������\\2015-11-12��ϵͳȫ����ͬ��\\PC-ϵͳȫ����Ʊ���Э��\\";
	

	public static void main(String[] args) {
		File filePath = new File(fileUrl); // ָ���ļ���·��
		File[] tempList = filePath.listFiles();
		int i = 0;
		for (File file2 : tempList) {
			String fileName = file2.getName();
			//fileName = fileName.replace("_0.", ".").replace("_1.", ".").replace("_2.", ".").replace("_3.", ".").replace("_4.", ".").replace("_5.", ".").replace("_6.", ".").replace("_new.", ".");
			fileName = fileName.replace(" ", "_");
			String newName = fileUrl + fileName;
			System.out.println(i+ "--"+ newName);
			file2.renameTo(new File(newName)); // ����
		}
	}
}
