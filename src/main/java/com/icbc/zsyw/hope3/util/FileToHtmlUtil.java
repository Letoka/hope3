package com.icbc.zsyw.hope3.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;

import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @ClassName FileToHtmlUtil
 * @Description
 * @Author qinwankang
 * @Date 2020/7/29 15:14
 * @Version V1.0
 **/
@Slf4j
public class FileToHtmlUtil {
    //doc格式转换为html
    public static String docToHtml() throws Exception {
        // .addResourceLocations("file:D:\\icbc\\image\\moduleimage\\")
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
      /*  String imagePathStr = path.getAbsolutePath() + "\\static\\image\\";
        String sourceFileName = path.getAbsolutePath() + "\\static\\test.doc";
        String targetFileName = path.getAbsolutePath() + "\\static\\test2.html";*/
        String imagePathStr = "D:\\icbc\\image\\html\\image\\";
        String sourceFileName = "D:\\icbc\\image\\aa.doc";
        String targetFileName = "D:\\icbc\\image\\html\\test2.html";
        File file = new File(imagePathStr);
        if (!file.exists()) {
            file.mkdirs();
        }
        //Document wordDocument = new Document(new FileInputStream(sourceFileName));
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        //保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "image/" + name;
        });
        wordToHtmlConverter.processDocument(wordDocument);
        org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        return targetFileName;
    }


    /**
     * word03版本(.doc)转html
     * poi:word03在线预览
     */
    public static void PoiWord03ToHtml1(String imagePath, String htmlPath, String htmlWordPth, String docName) throws IOException, ParserConfigurationException, TransformerException {
      /*  final String path = "C:\\Users\\Administrator\\Desktop\\";
        final String file = "C:\\Users\\Administrator\\Desktop\\word03.doc";*/
       String path = "D:\\qwki\\qwkword\\html\\image\\";
        String file = "D:\\qwki\\qwkword\\shiyanshi_liulianfx.doc";
        // 判断html文件是否存在

        //String path = imagePath;
       // String file = htmlWordPth + docName;
        // String targetFileName ="D:\\icbc\\image\\html\\test2.html";
        InputStream input = new FileInputStream(file);
        //XWPFDocument wordDocument = new XWPFDocument(input);
        HWPFDocument wordDocument = new HWPFDocument(input);
        //XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
        //  xhtmlConverter.convert(document, outputStreamWriter, options);
   /*     String sourceFileName = htmlWordPth+docName;
        String targetFileName =htmlPath+docName.split("\\.")[0]+".html";
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        return targetFileName;*/
   File fileImage = new File("D:\\qwki\\qwkword\\html\\image\\");
   if(!fileImage.exists()){
       fileImage.mkdirs();
   }
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {     //图片在html页面加载路径
                //  return "image\\"+suggestedName;

                return "image/" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        //获取文档中所有图片File.separator
        //  List pics = wordDocument.getPicturesTable().getAllPictures();
        List pics = wordDocument.getPicturesTable().getAllPictures();
        File htmlimgFile = new File(path);
        if (!htmlimgFile.exists()) {
            htmlimgFile.mkdirs();
        }
        String imgName ="";
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {//图片保存在文件夹的路径
                    //  File htmlimgFile = new File(htmlPath +"image\\");


                  /*  pic.writeImageContent(new FileOutputStream(htmlPath+"image\\"
                            + pic.suggestFullFileName()));*/
                 imgName = pic.suggestFullFileName();
                    List<String> fileList=new ArrayList<String>();
                    fileList=   listfile(htmlimgFile,fileList);
                    if(fileList!=null&&fileList.size()!=0){
                        for(String filename:fileList){
                            if(filename.equals(imgName)){
                                Random random = new Random();
                                int filerandom = random.nextInt(100);
                                imgName=imgName.split("\\.")[0]+filerandom+"\\."+imgName.split("\\.")[1];
                            }
                        }
                    }

                    pic.writeImageContent(new FileOutputStream("D:\\qwki\\qwkword\\html\\image\\"
                            + imgName));
                    //FileOutputStream(File file)
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /*String filePath = htmlPath+"image/" + pic.suggestFullFileName();
                try {
                    getFixedIcon(filePath,200,200);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

            }
        }
        //创建html页面并将文档中内容写入页面
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        // String content = new String(outStream.toString("UTF-8"));
        String content = new String(outStream.toString("utf-8"));
        FileUtils.writeStringToFile(new File(htmlPath, docName.split("\\.")[0] + ".html"), content, "utf-8");


    }
    /**
     * word03版本(.doc)转html
     * poi:word03在线预览
     */
    public static void PoiWord03ToHtml(String imagePath, String htmlPath, String htmlWordPth, String docName) throws IOException, ParserConfigurationException, TransformerException {
      /*  final String path = "C:\\Users\\Administrator\\Desktop\\";
        final String file = "C:\\Users\\Administrator\\Desktop\\word03.doc";*/
       /* String path = "D:\\icbc\\image\\html\\image\\";
        String file = "D:\\icbc\\image\\aa.doc";*/
        // 判断html文件是否存在

        //String path = imagePath;
        String file = htmlWordPth + docName;
        // String targetFileName ="D:\\icbc\\image\\html\\test2.html";
        InputStream input = new FileInputStream(file);
        //XWPFDocument wordDocument = new XWPFDocument(input);
        HWPFDocument wordDocument = new HWPFDocument(input);
        //XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
        //  xhtmlConverter.convert(document, outputStreamWriter, options);
   /*     String sourceFileName = htmlWordPth+docName;
        String targetFileName =htmlPath+docName.split("\\.")[0]+".html";
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        return targetFileName;*/
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {     //图片在html页面加载路径
                //  return "image\\"+suggestedName;
            /* List<String> fileList=   listfile(new File(imagePath));
             if(fileList!=null&&fileList.size()!=0){
                 for(String filename:fileList){
                     if(filename.equals(suggestedName)){
                         Random random = new Random();
                         int filerandom = random.nextInt(100);
                         suggestedName=suggestedName.split("\\.")[0]+filerandom+"\\."+suggestedName.split("\\.")[1];
                     }
                 }
             }
*/
                return "image/" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        //获取文档中所有图片File.separator
        //  List pics = wordDocument.getPicturesTable().getAllPictures();
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {//图片保存在文件夹的路径
                    //  File htmlimgFile = new File(htmlPath +"image\\");
                    File htmlimgFile = new File(htmlPath + "image/");
                    if (!htmlimgFile.exists()) {
                        htmlimgFile.mkdir();
                    }
                    log.info(pic.suggestFullFileName());
                    log.info(pic.getHeight() + "");
                    log.info(pic.getWidth() + "");
                    log.info(pic.getSize() + "");
                  /*  pic.writeImageContent(new FileOutputStream(htmlPath+"image\\"
                            + pic.suggestFullFileName()));*/
                    pic.writeImageContent(new FileOutputStream(htmlPath + "image/"
                            + pic.suggestFullFileName()));
                    //FileOutputStream(File file)
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /*String filePath = htmlPath+"image/" + pic.suggestFullFileName();
                try {
                    getFixedIcon(filePath,200,200);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        }
        //创建html页面并将文档中内容写入页面
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        // String content = new String(outStream.toString("UTF-8"));
        String content = new String(outStream.toString("utf-8"));
        FileUtils.writeStringToFile(new File(htmlPath, docName.split("\\.")[0] + ".html"), content, "utf-8");


    }

    public static void main(String[] args) {
        try {
            PoiWord03ToHtmlS("D:\\qwki\\qwkword\\html\\image\\","D:\\qwki\\qwkword\\html\\","D:\\qwki\\qwkword\\","shiyanshi_liulianfx.doc");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    /**
     * word03版本(.doc)转html
     * poi:word03在线预览
     */
    public static void PoiWord03ToHtmlS(String imagePath, String htmlPath, String htmlWordPth, String docName) throws IOException, ParserConfigurationException, TransformerException {
      /*  final String path = "C:\\Users\\Administrator\\Desktop\\";
        final String file = "C:\\Users\\Administrator\\Desktop\\word03.doc";*/
       /* String path = "D:\\icbc\\image\\html\\image\\";
        String file = "D:\\icbc\\image\\aa.doc";*/
        // 判断html文件是否存在

        //String path = imagePath;
        String file = htmlWordPth + docName;
        // String targetFileName ="D:\\icbc\\image\\html\\test2.html";
        InputStream input = new FileInputStream(file);
        //XWPFDocument wordDocument = new XWPFDocument(input);
        HWPFDocument wordDocument = new HWPFDocument(input);
        //XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
        //  xhtmlConverter.convert(document, outputStreamWriter, options);
   /*     String sourceFileName = htmlWordPth+docName;
        String targetFileName =htmlPath+docName.split("\\.")[0]+".html";
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        return targetFileName;*/
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {     //图片在html页面加载路径
                //  return "image\\"+suggestedName;
            /* List<String> fileList=   listfile(new File(imagePath));
             if(fileList!=null&&fileList.size()!=0){
                 for(String filename:fileList){
                     if(filename.equals(suggestedName)){
                         Random random = new Random();
                         int filerandom = random.nextInt(100);
                         suggestedName=suggestedName.split("\\.")[0]+filerandom+"\\."+suggestedName.split("\\.")[1];
                     }
                 }
             }
*/
                return "image/" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        //获取文档中所有图片File.separator
        //  List pics = wordDocument.getPicturesTable().getAllPictures();
        List pics = wordDocument.getPicturesTable().getAllPictures();
        String imgName ="";
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {//图片保存在文件夹的路径
                     File htmlimgFile = new File(htmlPath +"image\\");
                    //  File htmlimgFile = new File(htmlPath + "image/");
                    if (!htmlimgFile.exists()) {
                        htmlimgFile.mkdirs();
                    }
                    log.info(pic.suggestFullFileName());
                    log.info(pic.getHeight() + "");
                    log.info(pic.getWidth() + "");
                    log.info(pic.getSize() + "");
                  /*  pic.writeImageContent(new FileOutputStream(htmlPath+"image\\"
                            + pic.suggestFullFileName()));*/
                    imgName = pic.suggestFullFileName();
                    List<String> fileList=new ArrayList<String>();
                    fileList=   listfile(htmlimgFile,fileList);
                    if(fileList!=null&&fileList.size()!=0){
                        for(String filename:fileList){
                            if(filename.equals(imgName)){
                                Random random = new Random();
                                int filerandom = random.nextInt(100);
                                imgName=imgName.split("\\.")[0]+filerandom+"."+imgName.split("\\.")[1];
                            }
                        }
                    }
                    pic.writeImageContent(new FileOutputStream(htmlPath + "image\\"
                            + imgName));
                    //FileOutputStream(File file)
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /*String filePath = htmlPath+"image/" + pic.suggestFullFileName();
                try {
                    getFixedIcon(filePath,200,200);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        }
        //创建html页面并将文档中内容写入页面
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        // String content = new String(outStream.toString("UTF-8"));
        String content = new String(outStream.toString("utf-8"));
        FileUtils.writeStringToFile(new File(htmlPath, docName.split("\\.")[0] + ".html"), content, "utf-8");


    }

    /**
     * 35     * @Method: listfile
     * 36     * @Description: 递归遍历指定目录下的所有文件
     * 37     * @Anthor:孤傲苍狼
     * 38     * @param file 即代表一个文件，也代表一个文件目录
     * 39     * @param map 存储文件名的Map集合
     * 40
     */
    public static  List<String> listfile(File file,List<String>fileNameList) {
        //如果file代表的不是一个文件，而是一个目录

        if (!file.isFile()) {
            //列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            //遍历files[]数组
            for (File f : files) {
                //递归
                listfile(f,fileNameList);
            }
        } else {
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
             那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
           // String realName = file.getName().substring(file.getName().indexOf("_") + 1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
          //  map.put(file.getName(), realName);
            fileNameList.add(file.getName());
        }
        return fileNameList;
    }

    public static void PoiWord03ToHtml() throws IOException, ParserConfigurationException, TransformerException {
        final String path = "D:\\qwki\\qwkword\\";
        final String file = "D:\\qwki\\qwkword\\shiyanshi_liulianfx.doc";
        InputStream input = new FileInputStream(file);
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {     //图片在html页面加载路径
                return "image\\" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        //获取文档中所有图片
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {//图片保存在文件夹的路径
                    File htmlimgFile = new File(path + "image\\");
                    if (!htmlimgFile.exists()) {
                        htmlimgFile.mkdir();
                    }
                    System.out.println("名称1" + pic.suggestFullFileName());
                    System.out.println("高度1" + pic.getHeight());
                    System.out.println("宽度1" + pic.getWidth());
                    System.out.println("大小1" + pic.getSize());
                    System.out.println("=============================");

                    pic.writeImageContent(new FileOutputStream(path + "image\\"
                            + pic.suggestFullFileName()));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String filePath = path + "image\\" + pic.suggestFullFileName();
                try {
                    getFixedIcon(filePath, 200, 200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("名称" + pic.suggestFullFileName());
                System.out.println("高度" + pic.getHeight());
                System.out.println("宽度" + pic.getWidth());
                System.out.println("大小" + pic.getSize());
                System.out.println("=============================");
            }
        }
        //创建html页面并将文档中内容写入页面
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toString("UTF-8"));
        FileUtils.writeStringToFile(new File(path, "word03.html"), content, "utf-8");

    }

    /**
     * 按输入的任意宽高改变图片的大小
     *
     * @param filePath
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static Icon getFixedIcon(String filePath, int width, int height) throws Exception {
        File f = new File(filePath);

        BufferedImage bi = ImageIO.read(f);

        double wRatio = (new Integer(width)).doubleValue() / bi.getWidth(); //宽度的比例

        double hRatio = (new Integer(height)).doubleValue() / bi.getHeight(); //高度的比例

        Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH); //设置图像的缩放大小

        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(wRatio, hRatio), null);   //设置图像的缩放比例

        image = op.filter(bi, null);

        int lastLength = filePath.lastIndexOf(".");
        String subFilePath = filePath.substring(0, lastLength);  //得到图片输出路径
        String fileType = filePath.substring(lastLength);  //图片类型
        File zoomFile1 = new File(subFilePath + fileType);
       /* if(zoomFile1.exists()){
            zoomFile1.delete();
        }*/
        //File zoomFile = new File(subFilePath +"_"+ width +"_" + height + fileType);
        File zoomFile = new File(subFilePath + fileType);
//return zoomFile;
        Icon ret = null;
        try {

            ImageIO.write((BufferedImage) image, "jpg", zoomFile);
            ret = new ImageIcon(zoomFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 将word2003转换为html文件 2018-4-9
     *
     * @param //wordPath word文件路径
     * @param //wordName word文件名称无后缀
     * @param //suffix   word文件后缀
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */


    public static String Word2003ToHtml1(String imagePath1, String htmlPath1, String htmlWordPth1, String docName) throws IOException, TransformerException,
            ParserConfigurationException {
        String qwk = "";
       /* String path = "D:\\icbc\\image\\html\\image\\";
        String wordPath = "D:\\icbc\\image\\";*/
        //  String path = imagePath1;
        // String wordPath = htmlWordPth;
        //   String wordName=docName.split("\\.")[0];
        String wordName = docName;
        String suffix = ".doc";
        /*String htmlPath = wordPath + File.separator + wordName + "_show"
                + File.separator;*/
       /* String htmlPath = wordPath + File.separator + "doctohtml"
                + File.separator;*/
        String htmlName = wordName.split("\\.")[0] + ".html";
        // final String imagePath = htmlPath + "image" + File.separator;
        final String htmlPath = "D:\\qwki\\qwkword\\html\\";
        final String imagePath = "D:\\qwki\\qwkword\\html\\image\\";
        final String file = "D:\\qwki\\qwkword\\shiyanshi_liulianfx.doc";
        //  final String file = htmlWordPth+docName;
// 判断html文件是否存在
        File htmlFile = new File(htmlPath + htmlName);
        if (htmlFile.exists()) {
            return htmlFile.getAbsolutePath();
        }


// 原word文档
        //   final String file = wordPath + File.separator + wordName + suffix;
        InputStream input = new FileInputStream(new File(file));


        HWPFDocument wordDocument = new HWPFDocument(input);

        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
// 设置图片存放的位置以及大小
        //  float widthInches=653f;
        // float heightInches=550f;
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            /* public String savePicture(byte[] content, PictureType pictureType,
                                       String suggestedName, float widthInches, float heightInches)*/
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {
                File imgPath = new File(imagePath);
                if (!imgPath.exists()) {// 图片目录不存在则创建
                    imgPath.mkdirs();
                }
                File file = new File(imagePath + suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);

                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //修改图片大小
               /*String filePath = imagePath+"image\\" + suggestedName;
               try {
                   getFixedIcon(filePath,200,200);
               } catch (Exception e) {
                   e.printStackTrace();
               }*/
// 图片在html文件上的路径 相对路径
                return "image/" + suggestedName;
            }


        });


// 解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();


// 生成html文件上级文件夹
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }


// 生成html文件地址
// 也可以使用字符数组流获取解析的内容
// ByteArrayOutputStream baos = new ByteArrayOutputStream();
// OutputStream outStream = new BufferedOutputStream(baos);


        OutputStream outStream = new FileOutputStream(htmlFile);


        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);


        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");


        serializer.transform(domSource, streamResult);


// 也可以使用字符数组流获取解析的内容
// String content = baos.toString();
// System.out.println(content);
// baos.close();
// System.out.println("2003-doc"+content);
        return htmlFile.getAbsolutePath();
    }


    public static String Word2003ToHtml(String imagePath, String htmlPath, String htmlWordPth, String docName) throws IOException, TransformerException,
            ParserConfigurationException {
       /* html.image=/data/mobile/article/html/image/
                html.path=/data/mobile/article/html/
                html.worddoc.path=/data/mobile/article/*/
        // String textname = textpArr[textpArr.length-1];
        // FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
       /*
        @Value("${html.image}")
        private String htmlImage;
        @Value("${html.path}")
        private String htmlPath;
        @Value("${html.worddoc.path}")
        private String htmlWordPth;*/
        String qwk = "";
       /* String path = "D:\\icbc\\image\\html\\image\\";
        String wordPath = "D:\\icbc\\image\\";*/
        //  String path = imagePath1;
        String wordPath = htmlWordPth;
        //   String wordName=docName.split("\\.")[0];
        String wordName = docName;
        String suffix = ".doc";
        /*String htmlPath = wordPath + File.separator + wordName + "_show"
                + File.separator;*/
       /* String htmlPath = wordPath + File.separator + "doctohtml"
                + File.separator;*/
        String htmlName = wordName.split("\\.")[0] + ".html";
        // final String imagePath = htmlPath + "image" + File.separator;
      /*  final String htmlPath = "D:\\qwki\\qwkword\\html\\";
        final String imagePath = "D:\\qwki\\qwkword\\html\\image\\";
        final String file = "D:\\qwki\\qwkword\\shiyanshi_liulianfx.doc";*/
        final String file = htmlWordPth + docName;
// 判断html文件是否存在
        File htmlFile = new File(htmlPath + htmlName);
        if (htmlFile.exists()) {
            return htmlFile.getAbsolutePath();
        }


// 原word文档
        //   final String file = wordPath + File.separator + wordName + suffix;
        InputStream input = new FileInputStream(new File(file));


        HWPFDocument wordDocument = new HWPFDocument(input);

        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
// 设置图片存放的位置以及大小
        //  float widthInches=653f;
        // float heightInches=550f;
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            /* public String savePicture(byte[] content, PictureType pictureType,
                                       String suggestedName, float widthInches, float heightInches)*/
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {
                File imgPath = new File(imagePath);
                if (!imgPath.exists()) {// 图片目录不存在则创建
                    imgPath.mkdirs();
                }
                File file = new File(imagePath + suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);

                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //修改图片大小
               /*String filePath = imagePath+"image\\" + suggestedName;
               try {
                   getFixedIcon(filePath,200,200);
               } catch (Exception e) {
                   e.printStackTrace();
               }*/
// 图片在html文件上的路径 相对路径
                return "image/" + suggestedName;
            }


        });


// 解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();


// 生成html文件上级文件夹
        File folder = new File(htmlPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }


// 生成html文件地址
// 也可以使用字符数组流获取解析的内容
// ByteArrayOutputStream baos = new ByteArrayOutputStream();
// OutputStream outStream = new BufferedOutputStream(baos);


        OutputStream outStream = new FileOutputStream(htmlFile);


        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);


        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");


        serializer.transform(domSource, streamResult);


// 也可以使用字符数组流获取解析的内容
// String content = baos.toString();
// System.out.println(content);
// baos.close();
// System.out.println("2003-doc"+content);
        return htmlFile.getAbsolutePath();
    }


    //docx格式转换为html
 /*   @Value("${html.image}")
    private String htmlImage;
    @Value("${html.path}")
    private String htmlPath;
    @Value("${html.worddoc.path}")
    private String htmlWordPth;*/
    public static String docxToHtml(String imagePath, String htmlPath, String htmlWordPth, String docName) throws Exception {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
     /* String imagePath = path.getAbsolutePath() + "\\static\\image";
        String sourceFileName = path.getAbsolutePath() + "\\static\\test.docx";
        String targetFileName = path.getAbsolutePath() + "\\static\\test.html";*/
        // String imagePath = "D:\\icbc\\image\\html\\image";
        //String sourceFileName = "D:\\icbc\\image\\aa.doc";
        // String targetFileName ="D:\\icbc\\image\\html\\test2.html";
        String sourceFileName = htmlWordPth + docName;
        String targetFileName = htmlPath + docName.split("\\.")[0] + ".html";
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        return targetFileName;
    }

    ////读取html文件,读取之后内嵌式css转内联式css
    public static void fun(String htmlWordPth, String textname) throws IOException {
        /*String htmlPath = wordPath + File.separator + "doctohtml"
                + File.separator;
        String htmlName = wordName + ".html";*/
        File file = new File(htmlWordPth + File.separator + "doctohtml" + File.separator + textname.split("\\.")[0] + ".html");
        InputStream in = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        StringBuffer buff = new StringBuffer();
        while ((s = br.readLine()) != null) {
            buff.append(s);
        }
        br.close();
        reader.close();
        in.close();
        System.out.println(buff.toString());
        changeStyle(buff);
    }

    public static void changeStyle(StringBuffer buff) {
        StringBuffer buffStyle = new StringBuffer();
        //截取样式代码
        buffStyle.append(buff.substring(buff.indexOf("<style type=\"text/css\">") + 23, buff.indexOf("style", buff.indexOf("<style type=\"text/css\">") + 23) - 2));
        System.out.println(buffStyle);
        //截取body代码
        String body = buff.substring(buff.indexOf("<body"), buff.indexOf("</body") + 7);
        body = body.replaceAll("body", "div");
        StringBuffer bodyBuffer = new StringBuffer(body);
        System.out.println(bodyBuffer);
        String[] split = buffStyle.toString().split("}");
        Map<String, String> styleMap = new HashMap<>();
        for (String s1 : split) {
            System.out.println(s1);
            String[] split1 = s1.split("\\{");
            styleMap.put(split1[0].substring(1), split1[1]);
        }
        Set<String> strings = styleMap.keySet();
        for (String key : strings) {
            System.out.print("key : " + key);
            System.out.println("   value : " + styleMap.get(key));
            //将嵌入样式转换为行内样式
            if (bodyBuffer.toString().contains(key)) {
                int length = bodyBuffer.toString().split(key).length - 1;
                int temp = 0;
                for (int i = 0; i < length; i++) {
                    //首先判断是否完全匹配这个样式的class标识
                    //由于word转换为html的时候他会自动生成class的标识  比如 p1,p2,p3,p4,p10,p11这样的话使用contains方法
                    //p1就会被p11匹配到，这样样式就会乱掉，所以在添加行内样式之前必须要进行完全匹配
                    temp = bodyBuffer.indexOf(key, temp);
                    String isComplete = bodyBuffer.substring(temp, temp + key.length() + 1);
                    //这个地方key+" "意思是代表可能一个标签里面有多个class标识 比如 class = "p2 p3 p4"
                    if (!isComplete.equals(key + "\"") && !isComplete.equals(key + " ")) {
                        //这种就代表不是完全匹配
                        continue;
                    }
                    //这个是每次查询到的位置，判断此标签中是否添加了style标签
                    String isContaionStyle = bodyBuffer.substring(temp, bodyBuffer.indexOf(">", temp));
                    if (isContaionStyle.contains("style")) {
                        //代表已经存在此style，那么直接加进去就好了
                        //首先找到style的位置
                        int styleTemp = bodyBuffer.indexOf("style", temp);
                        bodyBuffer.insert(styleTemp + 7, styleMap.get(key));
                    } else {
                        //代表没有style，那么直接插入style
                        int styleIndex = bodyBuffer.indexOf("\"", temp);
                        bodyBuffer.insert(styleIndex + 1, " style=\"" + styleMap.get(key) + "\"");
                    }
                    temp += key.length() + 1;
                }
            }
        }
        System.out.println(bodyBuffer.toString());
        changePicture(bodyBuffer);
    }

    //更换图片的路径
    public static void changePicture(StringBuffer buffer) {
        //查询一个有多少个图片
        int length = buffer.toString().split("<img src=\"").length - 1;
        int temp = 0;
        for (int i = 0; i < length; i++) {
            temp = buffer.indexOf("<img src=\"", temp);
            String srcContent = buffer.substring(temp + 10, buffer.indexOf("style", temp + 10));
            //获取第三方文件服务器的路径,比如如下realSrc
            String realSrc = "";
            //将路径进行替换
            buffer.replace(temp + 10, buffer.indexOf("style", temp + 10), realSrc + "\"");
            temp++;
        }
    }

    //word转html保留样式
    //filename代表word路径，outputfile代表html文件路径
    public static void convert2Html(String fileName, String outPutFile, String textname)
            throws TransformerException, IOException,
            ParserConfigurationException {
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName + textname));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content,
                                      PictureType pictureType, String suggestedName,
                                      float widthInches, float heightInches) {
                return "image/" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        //save pictures
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                System.out.println();
                try {
                    pic.writeImageContent(new FileOutputStream("image/" + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        //serializer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        writeFile(new String(out.toByteArray()), outPutFile + textname.split("\\.")[0] + ".html");
    }

    public static void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            fos = new FileOutputStream(file);

            //    bw = new BufferedWriter(new OutputStreamWriter(fos,"GB2312"));
            bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }
}
