package com.ss.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {
	@RequestMapping("/test")
	public String test() {
		System.out.println("-----------------------");
		return "index";
	}

	@RequestMapping("/down")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String realPath = "D:\\1120.jpg";
		File file = new File(realPath);
		String filename = file.getName();
		filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
		response.setContentType("multipart/form-data");
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		response.addHeader("Content-Length", "" + file.length());
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		int res;
		while ((res = in.read()) != -1) {
			out.write(res);
		}
		in.close();
		out.close();
		out.flush();
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpSession session, @RequestParam("file") MultipartFile File)
			throws IllegalStateException, IOException {
		System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPP");
		System.out.println(File);

		String fileName = File.getOriginalFilename();
		long currentTimeMillis = System.currentTimeMillis();
		// 保存后的文件名
		fileName = currentTimeMillis + fileName;
		String leftPath = session.getServletContext().getRealPath("/");
		File file = new File(leftPath + "/", fileName);
		File.transferTo(file);
		Map<String, Object> map = new HashMap<>();
		map.put("statusCode", 200);

		map.put("filename", file.getName());

		return map;
	}
}
