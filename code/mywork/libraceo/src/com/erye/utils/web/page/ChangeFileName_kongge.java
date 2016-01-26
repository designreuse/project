package com.erye.utils.web.page;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ChangeFileName_kongge {
	private static ArrayList<String> filelist = new ArrayList<String>();

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		refreshFileList("E:\\�ٶ���ͬ����\\�������\\��˾��Ŀ\\658����\\��������\2015-11-12��ϵͳȫ����ͬ��\\-PC-ϵͳȫ����Ʊ���Э��\\");
		System.out.println(System.currentTimeMillis() - a);
	}

	public static void refreshFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				refreshFileList(files[i].getAbsolutePath());
			} else {
				String fileName = files[i].getName();
				String filePath = "E:\\�ٶ���ͬ����\\�������\\��˾��Ŀ\\658����\\��������\2015-11-12��ϵͳȫ����ͬ��\\-PC-ϵͳȫ����Ʊ���Э��\\pc-xitongquanliang-gupiao-kogge\\";

				System.out.println("fileName-------" + fileName);
				System.out.println("filePath-------" + filePath);

				filelist.add(files[i].getAbsolutePath());

				try {
					copyFile(files[i], new File(filePath.replaceAll(" ", "_")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// �����ļ�
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// �½��ļ���������������л���
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// �½��ļ��������������л���
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			// ��������
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// ˢ�´˻���������
			outBuff.flush();
		} finally {
			// �ر���
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}
}
