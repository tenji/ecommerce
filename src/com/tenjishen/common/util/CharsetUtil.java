package com.tenjishen.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

/**
 * 编码转换工具类
 * 
 * @author TENJI
 * @since
 * @date 2014-8-15
 */
public class CharsetUtil {

	private static Logger logger = Logger.getLogger(CharsetUtil.class);

	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	public static final String US_ASCII = "US-ASCII";

	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 8 位 UCS 转换格式 */
	public static final String UTF_8 = "UTF-8";

	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	public static final String UTF_16LE = "UTF-16LE";

	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	public static final String UTF_16 = "UTF-16";

	/** 中文超大字符集 */
	public static final String GBK = "GBK";

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	public static String toASCII(String str)
			throws UnsupportedEncodingException {
		return changeCharset(str, US_ASCII);
	}

	/**
	 * 将字符编码转换成ISO-8859-1码
	 */
	public static String toISO_8859_1(String str)
			throws UnsupportedEncodingException {
		return changeCharset(str, ISO_8859_1);
	}

	/**
	 * 将字符编码转换成UTF-8码
	 */
	public static String toUTF_8(String str)
			throws UnsupportedEncodingException {
		return changeCharset(str, UTF_8);
	}

	/**
	 * 将字符编码转换成UTF-16BE码
	 */
	public static String toUTF_16BE(String str)
			throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16BE);
	}

	/**
	 * 将字符编码转换成UTF-16LE码
	 */
	public static String toUTF_16LE(String str)
			throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16LE);
	}

	/**
	 * 将字符编码转换成UTF-16码
	 */
	public static String toUTF_16(String str)
			throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16);
	}

	/**
	 * 将字符编码转换成GBK码
	 */
	public static String toGBK(String str) throws UnsupportedEncodingException {
		return changeCharset(str, GBK);
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "This is a 中文的 String!";
		System.out.println("str: " + str);
		String gbk = CharsetUtil.toGBK(str);
		System.out.println("转换成GBK码: " + gbk);
		System.out.println();
		String ascii = CharsetUtil.toASCII(str);
		System.out.println("转换成US-ASCII码: " + ascii);
		gbk = CharsetUtil.changeCharset(ascii, CharsetUtil.US_ASCII, CharsetUtil.GBK);
		System.out.println("再把ASCII码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String iso88591 = CharsetUtil.toISO_8859_1(str);
		System.out.println("转换成ISO-8859-1码: " + iso88591);
		gbk = CharsetUtil.changeCharset(iso88591, CharsetUtil.ISO_8859_1,
				CharsetUtil.GBK);
		System.out.println("再把ISO-8859-1码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf8 = CharsetUtil.toUTF_8(str);
		System.out.println("转换成UTF-8码: " + utf8);
		gbk = CharsetUtil.changeCharset(utf8, CharsetUtil.UTF_8, CharsetUtil.GBK);
		System.out.println("再把UTF-8码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16be = CharsetUtil.toUTF_16BE(str);
		System.out.println("转换成UTF-16BE码:" + utf16be);
		gbk = CharsetUtil
				.changeCharset(utf16be, CharsetUtil.UTF_16BE, CharsetUtil.GBK);
		System.out.println("再把UTF-16BE码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16le = CharsetUtil.toUTF_16LE(str);
		System.out.println("转换成UTF-16LE码:" + utf16le);
		gbk = CharsetUtil
				.changeCharset(utf16le, CharsetUtil.UTF_16LE, CharsetUtil.GBK);
		System.out.println("再把UTF-16LE码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16 = CharsetUtil.toUTF_16(str);
		System.out.println("转换成UTF-16码:" + utf16);
		gbk = CharsetUtil.changeCharset(utf16, CharsetUtil.UTF_16LE, CharsetUtil.GBK);
		System.out.println("再把UTF-16码的字符串转换成GBK码: " + gbk);
		String s = new String("中文".getBytes("UTF-8"), "UTF-8");
		logger.info(s);
	}
}
