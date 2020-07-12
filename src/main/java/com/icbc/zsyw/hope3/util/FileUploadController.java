package com.icbc.zsyw.hope3.util;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.icbc.zsyw.hope3.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName FileUploadController
 * @Description
 * @Author qinwankang
 * @Date 2020/6/3 13:57
 * @Version V1.0
 **/
@RestController
@RequestMapping("/fileUploadController")
@Slf4j
public class FileUploadController {
    @Autowired
    private ApplicationContext applicationContext;
   // @Value("${img.local.path}")
    private String imgLoaclPath;
    /**
    * 功能描述:将图片上传到本地指定路径
     * @param request
     * @param file
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
    * @Author: qinwankang
    * @Date: 2020/6/3 15:19
    */
    @RequestMapping(path = {"/fileUpload1"},method = RequestMethod.POST)
    public BaseResponse<String> fileUpload1(HttpServletRequest request, @RequestParam("file") MultipartFile file)throws SQLException, IOException {

        byte[] data = file.getBytes();
      //  ResourceHandlerRegistry registry = new ResourceHandlerRegistry(applicationContext,request.getServletContext());
        String extName = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));
        String fileName = file.getOriginalFilename().split("\\.")[0]+"-"+UUID.randomUUID() + extName;
        StringBuffer sb = new StringBuffer(ResourceUtils.getURL("classpath:").getPath());
        File targetFileS = new File(imgLoaclPath);
        if(!targetFileS.exists()){
            targetFileS.mkdirs();
        }

        FileOutputStream out1 = new FileOutputStream(imgLoaclPath+fileName);
        out1.write(data);
        out1.flush();
        out1.close();
    //    registry.addResourceHandler("/static/upload/**").addResourceLocations("file:///"+imgLoaclPath);
        String webUrlq = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/mobile/static/upload/";
        String webUrlend = webUrlq+fileName;
        return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,webUrlend,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
     * 功能描述:将图片上传到本地指定路径(写死存储路径)
     * @param request
     * @param file
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
     * @Author: qinwankang
     * @Date: 2020/6/3 15:19
     */
    @RequestMapping(path = {"/fileUpload2"},method = RequestMethod.POST)
    public BaseResponse<String> fileUpload2(HttpServletRequest request, @RequestParam("file") MultipartFile file)throws SQLException, IOException {
        byte[] data = file.getBytes();
        ResourceHandlerRegistry registry = new ResourceHandlerRegistry(applicationContext,request.getServletContext());
        String extName = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));
        String fileName = "aa" + extName;
        StringBuffer sb = new StringBuffer(ResourceUtils.getURL("classpath:").getPath());
        File targetFileS = new File(imgLoaclPath);
        if(!targetFileS.exists()){
            targetFileS.mkdirs();
        }
        FileOutputStream out1 = new FileOutputStream(imgLoaclPath+fileName);
        out1.write(data);
        out1.flush();
        out1.close();
     //   registry.addResourceHandler("/static/upload/**").addResourceLocations("file:///"+imgLoaclPath);
        String webUrlq = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/mobile/static/upload/";
        String webUrlend = webUrlq+fileName;
        return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,webUrlend,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
     * 功能描述:将图片上传到项目路径
     * @param request
     * @param file
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
     * @Author: qinwankang
     * @Date: 2020/6/3 15:19
     */
    @RequestMapping(path = {"/fileUpload3"},method = RequestMethod.POST)
    public BaseResponse<String> fileUpload3(HttpServletRequest request, @RequestParam("file") MultipartFile file)throws SQLException, IOException {
        byte[] data = file.getBytes();
      //  ResourceHandlerRegistry registry = new ResourceHandlerRegistry(applicationContext,request.getServletContext());
        String extName = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));
        String fileName = "aa" + extName;
        String localpath = request.getServletContext().getRealPath("static/uplaod/");
        File targetFileS = new File(localpath);
        if(!targetFileS.exists()){
            targetFileS.mkdirs();
        }
        FileOutputStream out1 = new FileOutputStream(localpath+fileName);
        out1.write(data);
        out1.flush();
        out1.close();
       //registry.addResourceHandler("/static/upload/**").addResourceLocations("file:///"+imgLoaclPath);
        String webUrlq = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/mobile/static/uplaod/";
        String webUrlend = webUrlq+fileName;
        return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,webUrlend,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
     * 功能描述:将word转pdf并并上传
     * @param request
     * @param file
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
     * @Author: qinwankang
     * @Date: 2020/6/3 15:19
     */
    @RequestMapping(path = {"/fileUploadDocToPdf"},method = RequestMethod.POST)
    public BaseResponse<String> fileUploadDocToPdf(HttpServletRequest request, @RequestParam("file") MultipartFile file)throws SQLException, IOException {
        byte[] data = file.getBytes();
        //  ResourceHandlerRegistry registry = new ResourceHandlerRegistry(applicationContext,request.getServletContext());
        String extName = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));
        String fileName = "aa" + ".pdf";
        String localpath = request.getServletContext().getRealPath("static/uplaod/");
        File targetFileS = new File(localpath);
        if(!targetFileS.exists()){
            targetFileS.mkdirs();
        }
        InputStream docxInputStream = file.getInputStream();
        FileOutputStream out1 = new FileOutputStream(localpath+fileName);
        IConverter converter = LocalConverter.builder().build();
        converter.convert(docxInputStream).as(DocumentType.DOCX).to(out1).as(DocumentType.PDF).execute();
        out1.close();
        //out1.write(data);
        //out1.flush();
        //out1.close();
        // registry.addResourceHandler("/static/upload/**").addResourceLocations("file:///"+imgLoaclPath);
        String webUrlq = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/mobile/static/uplaod/";
        String webUrlend = webUrlq+fileName;
        return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,webUrlend,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
    * 功能描述:实现多文件上传
     * @param request
     * @param file
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
    * @Author: qinwankang
    * @Date: 2020/6/7 18:54
    */
    @RequestMapping(path = {"/fileUploads"},method = RequestMethod.POST)
    public BaseResponse<List<String>> fileUploads(HttpServletRequest request,  MultipartFile[] file)throws SQLException, IOException {
        //遍历文件数组执行上传
        List<String>list = new ArrayList<String>();
        for (int i =0;i<file.length;i++) {
            if(file[i] != null) {
                //调用上传方法
                BaseResponse<String> response =     fileUpload1(request, file[i]);
                list.add(response.getData());
            }
        }
        return new BaseResponse<List<String>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
       }

}
