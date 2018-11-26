package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.code.utils.UploadImageUtils;
import net.fckeditor.response.UploadResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


/**
 * 上传图片控制层
 * 1.单个图片上传
 * 2.fck图片上传
 *
 * 注:需要配置图片服务器,本项目使用应用服务器与图片服务器分离的
 *
 * Created by lionxxw on 2016-07-12 13:21:01
 */
@Controller
public class UploadController extends ApiController {
	
	//上传图片
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(@RequestParam(required = false) MultipartFile pic, HttpServletResponse response){
		String path = UploadImageUtils.uploadImage(pic, DataStatus.IMAGE_URL);

		//返回二个路径
		JSONObject jo = new JSONObject();
		jo.put("url", DataStatus.IMAGE_URL+path);
		jo.put("path",path);
		
		ResponseUtils.renderJson(response, jo.toString());
	}

	@RequestMapping(value = "/upload/uploadFck.do")
	public void uploadFck(HttpServletRequest request, HttpServletResponse response){
		// 强转request 支持多个
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		// 获取值,支持多个
		Map<String, MultipartFile> fileMap = mr.getFileMap();

		Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
		for (Map.Entry<String, MultipartFile> entry : entries){
			MultipartFile pic = entry.getValue();
			String path = UploadImageUtils.uploadImage(pic, DataStatus.IMAGE_URL);
			String url = DataStatus.IMAGE_URL + path;
			// 返回Url给Fck  fck-core.jar  ckeditor
			UploadResponse ok = UploadResponse.getOK(url);

			/**
			 * response 返回对象
			 * response write 和 response print 区别:
			 * 		前者字符流,后者字节流
			 */
			try {
				response.getWriter().print(ok);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}