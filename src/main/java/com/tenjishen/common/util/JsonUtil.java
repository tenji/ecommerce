package com.tenjishen.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * JSON处理工具类
 * 
 * @author TENJI
 *
 */
public class JsonUtil {
	private static final Logger logger = Logger.getLogger(JsonUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Object转换成JSON字符串
	 * 
	 * @author TENJI
	 * @param list	需要获取的List集合
	 */
	@SuppressWarnings("deprecation")
	public static String writeListToJson(Object list) {
		String jsonString = "";
		
		/*
		 * 对时间的格式化处理，Jackson默认是将时间格式转成timestamps形式的，
		 * 我们可以自定义转化格式
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 年-月-日 时:分:秒 格式
		SerializationConfig serConfig = objectMapper.getSerializationConfig();
		serConfig.setDateFormat(dateFormat);
		DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();
		deserializationConfig.setDateFormat(dateFormat);
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		
		try {
			jsonString = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	/** 
     * jackjson把json字符串转换为Java对象的实现方法 
     *  
     * @param <T> 
     *            转换为的java对象 
     * @param json 
     *            json字符串 
     * @param typeReference 
     *            jackjson自定义的类型 
     * @return 返回Java对象 
     */  
    public static <T> T jsonToObj(String json, TypeReference<T> typeReference) {  
        ObjectMapper mapper = new ObjectMapper();  
        try {  
            return mapper.readValue(json, typeReference);  
        } catch (JsonParseException e) {  
            logger.error("JsonParseException: ", e);  
        } catch (JsonMappingException e) {  
            logger.error("JsonMappingException: ", e);  
        } catch (IOException e) {  
            logger.error("IOException: ", e);  
        }  
        return null;  
    }  

	/**
	 * json转换为java对象
	 * 
	 * @param <T>
	 *            要转换的对象
	 * @param json
	 *            字符串
	 * @param valueType
	 *            对象的class
	 * @return 返回对象
	 */
	public static <T> T jsonToObj(String json, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, valueType);
		} catch (JsonParseException e) {
			logger.error("JsonParseException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * java对象转换为json字符串
	 * 
	 * @param object
	 *            Java对象
	 * @return 返回字符串
	 */
	public static String objToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

	/**
	 * java对象转换为json字符串，带Filter
	 * 
	 * @param object
	 *            要转换的对象
	 * @param filterName
	 *            过滤器的名称
	 * @param properties
	 *            要过滤哪些字段
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String objToJson(Object object, String filterName,
			Set<String> properties) {
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider().addFilter(
				filterName, SimpleBeanPropertyFilter.serializeAllExcept(properties));
		try {
			return mapper.filteredWriter(filters).writeValueAsString(object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException: ", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}
		return null;
	}

}
