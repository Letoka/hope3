package com.icbc.zsyw.hope3.util;

import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @ClassName TxtReader
 * @Description
 * @Author qinwankang
 * @Date 2020/8/4 15:31
 * @Version V1.0
 **/
public class TxtReader {
    public static String readTxt(File file) throws IOException {
        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        while ((s=br.readLine())!=null){
          //System.out.println(s);
            if(s.contains("jpg")||s.contains("png")||s.contains("gif")||s.contains("tif")||s.contains("pcx")||s.contains("bmp")
            ||s.contains("tga")||s.contains("exif")||s.contains("fpx")||s.contains("svg")||s.contains("psd")||s.contains("cdr")
                    ||s.contains("pcd")||s.contains("dxf")||s.contains("ufo")||s.contains("eps")||s.contains("ai")||s.contains("raw")
                    ||s.contains("WMF")||s.contains("webp")||s.contains("jpeg")||s.contains("svg")||s.contains("psd")||s.contains("cdr")){

               s=s.split("\\$\\$")[0]+"&$$"+s.split("\\$\\$")[1];
            }

            content = content.append(s).append("&");
        }
        return content.toString();
    }
    /**
     * <p>将文件转成base64 字符串</p>
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }
    public static void main(String[] args) {
        try {
//通过绝对路径获取文件
            String s1 = readTxt(new File("D:\\qwki\\qwkword\\zsyw20.txt"));
//spring boot中文件直接放在resources目录下
            //String s2 = readTxt(ResourceUtils.getFile("classpath:du.txt"));
            System.out.println(s1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
