package com.lionxxw.kqsystem.utils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 压缩文件工具类
 *
 * Created by lionxxw on 2016/7/18.
 */
public class ZipUtils {

    private static final Log log = LogFactory.getLog(ZipUtils.class);

    /**
     * 压缩文件
     *
     * @param srcfile File[] 需要压缩的文件列表
     * @param zipfile File 压缩后的文件
     */
    public static void zipFiles(List<File> srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        try {
            // Create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            // Compress the files
            for (int i = 0; i < srcfile.size(); i++) {
                File file = srcfile.get(i);
                FileInputStream in = new FileInputStream(file);
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(file.getName()));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            // Complete the ZIP file
            out.close();
        } catch (IOException e) {
            log.error("ZipUtil zipFiles exception:"+e);
        }
    }

    /**
     * 解压缩
     *
     * @param zipfile File 需要解压缩的文件
     * @param descDir String 解压后的目标目录
     */
    public static void unZipFiles(File zipfile, String descDir) {
        try {
            // Open the ZIP file
            ZipFile zf = new ZipFile(zipfile);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                // Get the entry name
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String zipEntryName = entry.getName();
                InputStream in = zf.getInputStream(entry);
                // System.out.println(zipEntryName);
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                // Close the file and stream
                in.close();
                out.close();
            }
        } catch (IOException e) {
            log.error("ZipUtil unZipFiles exception:"+e);
        }
    }

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        List<File> srcfile=new ArrayList<File>();
        srcfile.add(new File("e:\\1.xls"));
        srcfile.add(new File("e:\\2.xls"));
        srcfile.add(new File("e:\\3.xls"));
        srcfile.add(new File("e:\\4.xls"));
        srcfile.add(new File("e:\\5.xls"));

        File zipfile = new File("e:\\edm.zip");
        ZipUtils.zipFiles(srcfile, zipfile);
    }
}