package com.tenjishen.common.util.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/*******************************************************************************
 * Dom4j Xml解析
 * 
 * @author zengxiangtao
 * @version 2014-03-13
 ******************************************************************************/
public class PaserXmlStrUtils {

	/** 读取xml =>doc */
	public static Document getDocument(String xmlStr) {
		try {
			return DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Root 头节点 */
	public static Element getRootElement(String xmlStr) {
		if (StringUtils.isBlank(xmlStr)) {
			return null;
		}
		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			if (null != doc) {
				return doc.getRootElement();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 状态返回 */
	public static String getResultStr(String resultStr) {
		return "<?xml version='1.0' encoding='utf-8'?><result><status>"
				+ resultStr + "</status></result>";
	}

	/** 获得XML 某一个节点 */
	public static String paserStr(String xmlStr, String key) {
		Element rootElemnt = PaserXmlStrUtils.getRootElement(xmlStr);
		if (null != rootElemnt) {
			return rootElemnt.elementTextTrim(key);
		}
		return null;
	}

	/** 把一个xml转换成对应的bean */
	public static <T> T paserXmlToBean(String xmlStr, Class<T> beanClass) {
		Element re = getRootElement(xmlStr);
		Iterator<Element> it = null == re ? null : re.elementIterator();
		if (null == it) {
			return null;
		}
		T obj = null;
		try {
			obj = beanClass.newInstance();
			while (it.hasNext()) {
				Element e = it.next();
				// TODO 此版本的commons包没有这个Utils，待改进
				// BeanUtils.setProperty(obj, e.getName(), e.getTextTrim());
			}
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 把一个xml转换成对应的map */
	public static Map<String, String> paserXmlToMap(String xmlStr) {
		Element re = getRootElement(xmlStr);
		Iterator<Element> it = null == re ? null : re.elementIterator();
		if (null == it) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		while (it.hasNext()) {
			Element e = it.next();
			map.put(e.getName(), e.getTextTrim());
		}
		return map;
	}
}
