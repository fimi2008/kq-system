package com.lionxxw.kqsystem.code.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>Description: 基于jersey图片服务器框架上传工具类 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/25 上午9:32
 */
public class UploadImageUtils {

    /**
     * <p>Description: 图片上传 </p>
     *
     * @param pic 图片
     * @param imageUrl 图片服务器路径
     * @return path 图片保存路径
     * @author lionxxw
     * @date 16/5/25 上午9:54
     * @version 1.0
     */
    public static String uploadImage(MultipartFile pic, String imageUrl){
        //扩展名
        String ext = FilenameUtils.getExtension(pic.getOriginalFilename());

        String format = getCreateFormat();

        //实例化一个Jersey
        Client client = new Client();
        //保存数据库
        String path = "upload/" + format + "." + ext;

        //另一台服务器的请求路径是?
        String url = imageUrl  + path;
        //设置请求路径
        WebResource resource = client.resource(url);

        //发送开始  POST  GET   PUT
        try {
            resource.put(String.class, pic.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    /**		
     * <p>Description: 图片名称生成策略 </p>
     * 
     * @return f
     * @author lionxxw
     * @date 16/5/25 上午10:15
     * @version 1.0
     */
    public static String getCreateFormat(){
        //图片名称生成策略
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //图片名称一部分
        StringBuilder format = new StringBuilder(df.format(new Date()));

        //随机三位数
        Random r = new Random();
        // n 1000   0-999   99
        for(int i=0 ; i<3 ;i++){
            format.append(r.nextInt(10));
        }

        return format.toString();
    }
}
