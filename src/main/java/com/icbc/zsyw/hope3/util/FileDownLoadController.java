package com.icbc.zsyw.hope3.util;

import com.icbc.zsyw.hope3.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName FileDownLoadController
 * @Description
 * @Author qinwankang
 * @Date 2020/6/7 9:00
 * @Version V1.0
 **/
@Controller
@RequestMapping("/fileDownLoadController")
@Slf4j
public class FileDownLoadController {
    /**
    * 功能描述:下载第一步，跳转到下载页面
     * @param request
     * @param response
     * @param model
    * @return: java.lang.String
    * @Author: qinwankang
    * @Date: 2020/6/7 9:46
    */
    @RequestMapping(path = {"/fileDownLoad1"},method = RequestMethod.GET)
    public String  fileDownLoad1(HttpServletRequest request, HttpServletResponse response , ModelMap model) throws SQLException, IOException, ServletException {
        //获取要下载文件的目录
        String localpath = request.getServletContext().getRealPath("static/uplaod/");
        //存储要下载的文件名
                Map<String,String> fileNameMap = new HashMap<String,String>();
                //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
                listfile(new File(localpath),fileNameMap);//File既可以代表一个文件也可以代表一个目录
                 //将Map集合发送到listfile.jsp页面进行显示
        model.addAttribute("fileNameMap", fileNameMap);
        return "listfile";
    }
    public void listfile(File file,Map<String,String> map){
                 //如果file代表的不是一个文件，而是一个目录
                 if(!file.isFile()){
                         //列出该目录下的所有文件和目录
                         File files[] = file.listFiles();
                         //遍历files[]数组
                         for(File f : files){
                                 //递归
                                 listfile(f,map);
                             }
                     }else{
                         /**
                           * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
                              file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
                              那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
                           */
                         String realName = file.getName().substring(file.getName().indexOf("_")+1);
                         //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
                         map.put(file.getName(), realName);
                     }
             }
             /**
             * 功能描述:下载第二步，进行下载
              * @param request
              * @param response
              * @param
             * @return: void
             * @Author: qinwankang
             * @Date: 2020/6/7 9:47
             */
    @RequestMapping(path = {"/fileDownLoad2"},method = RequestMethod.GET)
    public void  fileDownLoad2(HttpServletRequest request, HttpServletResponse response ) throws SQLException, IOException, ServletException {
        //得到要下载的文件名
           String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //获取要下载文件的目录
        String localpath = request.getServletContext().getRealPath("static/uplaod/");
//通过文件名找出文件的所在目录
               String path = findFileSavePathByFileName(fileName,localpath);
                //得到要下载的文件
                 File file = new File(path + "\\" + fileName);
                 //如果文件不存在
                 if(!file.exists()){
                        request.setAttribute("message", "您要下载的资源已被删除！！");
                     //   request.getRequestDispatcher("/message.jsp").forward(request, response);
                        return;
                     }
                 //处理文件名
                 String realname = fileName.substring(fileName.indexOf("_")+1);
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        //设置响应头，控制浏览器下载该文件
                 response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
              //读取要下载的文件，保存到文件输入流
                FileInputStream in = new FileInputStream(path + "\\" + fileName);
                 //创建输出流
                OutputStream out = response.getOutputStream();
                 //创建缓冲区
                 byte buffer[] = new byte[1024];
                 int len = 0;
                 //循环将输入流中的内容读取到缓冲区当中
                 while((len=in.read(buffer))>0){
                         //输出缓冲区的内容到浏览器，实现文件下载
                         out.write(buffer, 0, len);
                     }
                 //关闭文件输入流
                 in.close();
                 //关闭输出流
                 out.close();
    }
    /**
     * @Method: findFileSavePathByFileName
     * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
     * @Anthor:孤傲苍狼
     * @param filename 要下载的文件名
     * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
     * @return 要下载的文件的存储目录
     */
    public String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
