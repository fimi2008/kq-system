package com.lionxxw.kqsystem.code.encode;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: 自定义Md5加密规则接口实现 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/6/11 下午10:20
 */
public class EncodeMd5Impl implements EncodeMd5{

    //加密
    public String encode(String password){
        String algorithm = "MD5";
        //加盐所谓加盐就是在用户密码的基础上按照某一规则添加不规律的字符
        password = "aw"+password+"53";
        MessageDigest instance = null;
        try {
            instance = MessageDigest.getInstance(algorithm);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //加密
        byte[] digest = instance.digest(password.getBytes());
        //十六进制加密
        char[] encodeHex = Hex.encodeHex(digest);

        return new String(encodeHex);
    }

    public static void main(String[] args) {
        EncodeMd5 md5 = new EncodeMd5Impl();
        System.out.println(md5.encode("111111"));
    }
}
