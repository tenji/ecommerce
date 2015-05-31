package com.tenjishen.common.util;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 字符串工具类
 * 
 * @author TENJI
 * @since
 * @date 2014-8-21
 */
public class StringUtil {

	private static final Logger logger = Logger.getLogger(StringUtil.class);

	private static char ch[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', '0', '1' }; // 最后又重复两个0和1，因为需要凑足数组长度为64
	
	private static Random random = new Random();
	
	private static int maxLength = 1000; // 随机字符串的最大长度为1000

	/**
	 * String数组转Long数组
	 * 
	 * @date 2014-8-21
	 * @param stringArray
	 * @return
	 */
	public static Long[] parseLongArray(String[] stringArray) {
		Long[] longArray = new Long[stringArray.length];
		try {
			for (int i = 0; i < longArray.length; i++) {
				longArray[i] = Long.parseLong(stringArray[i]);
			}
		} catch (Exception e) {
			logger.error("数组转换出错: " + e);
		}

		return longArray;
	}

	/**
	 * 打乱字符串中字符顺序
	 * 
	 * @date 2014-9-25
	 * @param oldStr
	 *            原字符串
	 * @return
	 */
	public static String shuffle(String oldStr) {
		if (oldStr == null) {
			return null;
		} else {
			Character[] temp = new Character[oldStr.length()];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = oldStr.charAt(i);
			}
			temp = ArrayUtil.shuffle(temp);
			
			String newStr = "";
			for (int i = 0; i < temp.length; i++) {
				newStr += temp[i].toString();
			}

			return newStr;
		}
	}

	/**
	 * 获取随机长度的随机字符串
	 * 
	 * @date 2014-9-25
	 * @return
	 */
	public static String getRandomStr() {
		
		return getRandomStr(random.nextInt(maxLength));
	}

	/**
	 * 获取指定长度的随机字符串
	 * 
	 * @date 2014-9-25
	 * @param length
	 *            随机字符串指定长度
	 * @return
	 */
	public static String getRandomStr(int length) {
		if (length > 0) {
			int index = 0;
			char[] temp = new char[length];
			int num = random.nextInt();
			for (int i = 0; i < length % 5; i++) {
				temp[index++] = ch[num & 63]; // 取后面六位，记得对应的二进制是以补码形式存在的
				num >>= 6;// 63的二进制为:111111
				// 为什么要右移6位？因为数组里面一共有64个有效字符。为什么要除5取余？因为一个int型要用4个字节表示，也就是32位
			}
			for (int i = 0; i < length / 5; i++) {
				num = random.nextInt();
				for (int j = 0; j < 5; j++) {
					temp[index++] = ch[num & 63];
					num >>= 6;
				}
			}
			return new String(temp, 0, length);
		} else if (length == 0) {
			return ""; // 返回空字符串
		} else {
			logger.error("---------- 字符串长度不合法 ----------");
			throw new IllegalArgumentException();
		}
	}
}
