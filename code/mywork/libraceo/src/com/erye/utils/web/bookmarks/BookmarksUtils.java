package com.erye.utils.web.bookmarks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

public class BookmarksUtils {
	private static String ENCODE = "UTF-8";
	private static String PATH = "E:/bookmarks_16_1_4.html";
	private static String SAVE_PATH = "E:/bookmarks/";

	public static String openFile(String szFileName) {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream(new File(szFileName)), ENCODE));
			String szContent = "";
			String szTemp;

			while ((szTemp = bis.readLine()) != null) {
				szContent += szTemp + "\n";
			}
			bis.close();
			return szContent;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static Parser readHtml(String szContent) {
		try {
			return Parser.createParser(szContent, ENCODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean checkChildren(Node tag) {
		if (tag != null && tag.getChildren() != null && tag.getChildren().size() > 0) {
			return true;
		}
		return false;
	}

	public static NodeList readNodeList(Node node) {
		try {
			NodeList list = node.getChildren();
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				BookmarksUtils.getBookMarks(tag);
				if (BookmarksUtils.checkChildren(tag)) {
					BookmarksUtils.readNodeList(tag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void getBookMarks(Node tag) {
		if (tag instanceof LinkTag) {
			LinkTag linkTag = (LinkTag) tag;
			try {
				message(String.format("保存文件[%s]到:[%s]", new Object[] { linkTag.extractLink(), SAVE_PATH + linkTag.getLinkText() }));
				downloadPageByGetMethod(linkTag.extractLink(), SAVE_PATH, linkTag.getLinkText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void downloadPageByGetMethod(String url, String saveUrl, String fileName) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = httpClient.execute(httpGet);

		InputStream is = null;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			try {
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				fileName = getFileName(fileName);
				createSaveFile(saveUrl);
				saveToFile(saveUrl, fileName, is);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					is.close();
				}
				if (response != null) {
					response.close();
				}
			}
		}
	}

	// 将url中的特殊字符用下划线代替
	public static String getFileName(String url) {
		String fileName = url.replaceAll("[\\?:*|<>\"/]", "_") + ".html";
		return fileName;
	}

	public static void createSaveFile(String saveUrl) {
		File flie = new File(saveUrl);
		if (!flie.exists()) {
			flie.mkdir();
		}
	}

	// 将输入流中的内容输出到path指定的路径，fileName指定的文件名
	public static void saveToFile(String path, String fileName, InputStream is) {
		Scanner sc = new Scanner(is);
		Writer os = null;
		try {
			os = new PrintWriter(path + fileName);
			while (sc.hasNext()) {
				os.write(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("输出流关闭失败！");
				}
			}
		}
	}

	public static void message(String szMsg) {
		try {
			System.out.println(new String(szMsg.getBytes(ENCODE), System.getProperty("file.encoding")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String szContent = openFile(PATH);
		try {
			Parser parser = BookmarksUtils.readHtml(szContent);
			for (NodeIterator i = parser.elements(); i.hasMoreNodes();) {
				Node tag = i.nextNode();
				BookmarksUtils.getBookMarks(tag);
				if (BookmarksUtils.checkChildren(tag)) {
					BookmarksUtils.readNodeList(tag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
