package com.tmgg.xbaselibrary.utils;

public class HtmlUtils {
	private final static String regxpForHtml = "<([^>]*)>"; // ����������<��ͷ��>��β�ı�ǩ

	private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // �ҳ�IMG��ǩ

	private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // �ҳ�IMG��ǩ��SRC����

	 

	/**
	 * �������ܣ��滻�����������ʾ
	 * 
	 * @param input
	 * @return String
	 */
	public static String replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			default:
				filtered.append(c);
			}

		}
		return (filtered.toString());
	}

	public static String replaceTag3(String input) {
		String temp = input.replaceAll("&lt;", "<");
		temp = temp.replaceAll("&gt;", ">");
		temp = temp.replaceAll("&amp;", "\"");
		temp = temp.replaceAll("&quot;", "\"");
		temp = temp.replaceAll("&nbsp;", "");
		return temp;
	}

	public static String replaceTag2(String input, String p, String newValue) {

		String temp = input.replaceAll(p, newValue);

		return temp;
	}
	/**
	 * �������ܣ��жϱ���Ƿ����
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
}
