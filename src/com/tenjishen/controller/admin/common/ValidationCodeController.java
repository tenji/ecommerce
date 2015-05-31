package com.tenjishen.controller.admin.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenjishen.common.util.ValidationCodeUtil;

/**
 * Controller - 验证码
 * 
 * @author tenji
 * 
 */
@Controller
@Scope("prototype")
public class ValidationCodeController {

	// 生成验证码并保存到session中
	@RequestMapping("/admin/validateCode")
	public String generate(String code, HttpServletRequest request,
			HttpServletResponse response) {
		ByteArrayOutputStream output = ValidationCodeUtil.generate(request
				.getSession());
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			output.writeTo(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStreams(outputStream, output); // 关闭资源
		}

		return null;
	}

	/**
	 * 关闭资源
	 * 
	 * @param outputStream
	 * @param output
	 */
	private void closeStreams(OutputStream outputStream,
			ByteArrayOutputStream output) {
		if (null != output) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != outputStream) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}
