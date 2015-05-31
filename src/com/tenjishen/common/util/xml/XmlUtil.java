package com.tenjishen.common.util.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * XML工具类
 * 
 * @author TENJI
 * @since
 * @date 2014-8-11
 */
public class XmlUtil {

    /******************************* 解析 ******************************/

    /**
     * 解析XML格式的字符串，并将结果放到Map中，只解析到第二层的标签，如有更多层则第三层和以上不解析
     * 
     * @param xml
     *            XML文档字符串
     * @param excludeTags
     *            不解析的XML标签
     * @return
     */
    public static Map<String, Object> parse2Map(String xml,
            String... excludeTags) {
        return parse2Map(getRoot(xml), excludeTags);
    }

    /**
     * 解析节点，并将结果放到Map中，只解析节点的子节点，不解析子节点往下的子节点
     * 
     * @param ele
     *            节点
     * @param excludeNodes
     *            不解析的子节点
     * @return
     */
    public static Map<String, Object> parse2Map(Element ele,
            String... excludeNodes) {
        return toMap(ele, excludeNodes);
    }

    /**
     * 解析XML格式的字符串，并将结果放到List中，只解析到第三层的标签，如有更多层则第四层和以上不解析
     * 
     * @param xml
     * @param excludeTags
     * @return
     */
    public static List<Map<String, Object>> parse2List(String xml,
            String... excludeTags) {
        return parse2List(getRoot(xml), excludeTags);
    }

    /**
     * 解析节点，并将结果放到Map中，只解析节点的子节点和孙节点，不解析孙节点往下的节点
     * 
     * @param ele
     * @param excludeNodes
     * @return
     */
    public static List<Map<String, Object>> parse2List(Element ele,
            String... excludeNodes) {
        return toList(ele, excludeNodes);
    }

    /******************************* 解析并修改名称 ******************************/

    /**
     * 解析XML字符串，只解析到第二层的标签
     * 
     * @param xml
     *            XML字符串
     * @param changeNodes
     *            需要修改名称的子标签
     * @param excludeTags
     *            不解析的XML标签
     * @return
     */
    public static Map<String, Object> parse2Map(String xml,
            Map<String, String> changeTags, String... excludeTags) {
        return parse2Map(getRoot(xml), changeTags, excludeTags);
    }

    /**
     * 只解析节点的子节点，不解析子节点往下的子节点
     * 
     * @param ele
     *            节点
     * @param changeNodes
     *            需要修改名称的子节点
     * @param excludeNodes
     * @return
     */
    public static Map<String, Object> parse2Map(Element ele,
            Map<String, String> changeNodes, String... excludeNodes) {
        return toMap(ele, changeNodes, excludeNodes);
    }

    /**
     * 解析XML格式的字符串，并将结果放到List中，只解析到第三层的标签
     * 
     * @param xml
     *            XML格式的字符串
     * @param changeTags
     *            需要修改名称的标签
     * @param excludeTags
     *            不需要解析的标签
     * @return
     */
    public static List<Map<String, Object>> parse2List(String xml,
            Map<String, String> changeTags, String... excludeTags) {
        return parse2List(getRoot(xml), changeTags, excludeTags);
    }

    /**
     * 只解析节点的子节点和孙节点，不解析孙节点往下的节点
     * 
     * @param ele
     *            节点
     * @param changeNodes
     *            需要修改名称的节点
     * @param excludeNodes
     *            不解析的节点
     * @return
     */
    public static List<Map<String, Object>> parse2List(Element ele,
            Map<String, String> changeNodes, String... excludeNodes) {
        return toList(ele, changeNodes, excludeNodes);
    }

    /******************************* 其它方法 ******************************/

    /**
     * 获取XML文档片段的根
     * 
     * @param xml
     * @return
     */
    public static Element getRoot(String xml) {
        try {
            return DocumentHelper.parseText(xml).getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /******************************* 私有方法 ******************************/

    @SuppressWarnings("unchecked")
    private static Map<String, Object> toMap(Element ele,
            String... excludeNodes) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>(ele
                .elements().size());

        for (Iterator<Element> it = ele.elementIterator(); it.hasNext();) {
            Element e = it.next();
            resultMap.put(e.getName(), e.getTextTrim());
        }
        if (excludeNodes != null && excludeNodes.length > 0) {
            for (String node : excludeNodes) {
                resultMap.remove(node);
            }
        }
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> toMap(Element ele,
            Map<String, String> changeNodes, String... excludeNodes) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>(ele
                .elements().size());

        for (Iterator<Element> it = ele.elementIterator(); it.hasNext();) {
            Element e = it.next();

            if (changeNodes.containsKey(e.getName())) {
                resultMap.put(changeNodes.get(e.getName()), e.getTextTrim());
                continue;
            }
            resultMap.put(e.getName(), e.getTextTrim());
        }
        if (excludeNodes != null && excludeNodes.length > 0) {
            for (String node : excludeNodes) {
                resultMap.remove(node);
            }
        }
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> toList(Element ele,
            String... excludeNodes) {

        int size = ele.elements().size();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(
                size);

        for (Iterator<Element> it = ele.elementIterator(); it.hasNext();) {
            list.add(toMap(it.next(), excludeNodes));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> toList(Element ele,
            Map<String, String> changeNodes, String... excludeNodes) {

        int size = ele.elements().size();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(
                size);

        for (Iterator<Element> it = ele.elementIterator(); it.hasNext();) {
            list.add(toMap(it.next(), changeNodes, excludeNodes));
        }
        return list;
    }

}
