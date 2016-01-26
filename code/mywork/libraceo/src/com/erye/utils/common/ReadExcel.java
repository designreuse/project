package com.erye.utils.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	

	public static List<String[]> readExcel(String url) throws BiffException, IOException {
		// ����һ��list �����洢��ȡ������
		List<String[]> list = new ArrayList<String[]>();
		Workbook rwb = null;
		Cell cell = null;

		// ����������
		InputStream stream = new FileInputStream(url);

		// ��ȡExcel�ļ�����
		rwb = Workbook.getWorkbook(stream);

		// ��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
		Sheet sheet = rwb.getSheet(0);

		// ����(��ͷ��Ŀ¼����Ҫ����1��ʼ)
		for (int i = 1; i < sheet.getRows(); i++) {

			// ����һ������ �����洢ÿһ�е�ֵ
			String[] str = new String[sheet.getColumns()];

			// ����
			for (int j = 0; j < sheet.getColumns(); j++) {

				// ��ȡ��i�У���j�е�ֵ
				cell = sheet.getCell(j, i);
				str[j] = cell.getContents();

			}
			// �Ѹջ�ȡ���д���list
			list.add(str);
		}

//		for (int i = 0; i < list.size(); i++) {
//			String[] str = (String[]) list.get(i);
//			for (int j = 0; j < str.length; j++) {
//				System.out.println(str[j]);
//			}
//			System.out.println(i);
//		}
		return list;
	}
}
