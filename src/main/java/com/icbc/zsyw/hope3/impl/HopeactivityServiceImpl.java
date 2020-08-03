package com.icbc.zsyw.hope3.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopeActivityLog;
import com.icbc.zsyw.hope3.dto.HopeUserFavor;
import com.icbc.zsyw.hope3.enums.*;
import com.icbc.zsyw.hope3.mapper.HopeActicityMapper;
import com.icbc.zsyw.hope3.mapper.HopeActivityLogMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserFavorMapper;
import com.icbc.zsyw.hope3.service.HopeactivityService;
import com.icbc.zsyw.hope3.util.FileToHtmlUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.persistence.SecondaryTable;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ClassName HopeactivityServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 9:10
 * @Version V1.0
 **/
@Service
public class HopeactivityServiceImpl implements HopeactivityService {
    @Resource
    private HopeActicityMapper hopeActicityMapper;
    @Resource
    private HopeActivityLogMapper hopeActivityLogMapper;
    @Resource
    private HopeUserFavorMapper hopeUserFavorMapper;
    @Value("${img.local.path}")
    private String imgLoaclPath;
    @Value("${html.image}")
    private String htmlImage;
    @Value("${html.path}")
    private String htmlPath;
    @Value("${html.worddoc.path}")
    private String htmlWordPth;
/*    #word转html windwos路径
    html.wimage=D:\\icbc\\image\\html\\image
    html.wpath=D:\\icbc\\image\\html\\
    html.wworddoc.path=D:\\icbc\\image\\*/
    /*@Value("${html.wimage}")
    private String htmlImage;
    @Value("${html.wpath}")
    private String htmlPath;
    @Value("${html.wworddoc.path}")
    private String htmlWordPth;*/
    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
    * @Author: qinwankang
    * @Date: 2020/6/2 16:52
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> queryHopeActivity(JSONObject jsonObject) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //入参校验
        BaseResponse checkResoponse =   checkparameter(jsonObject);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(checkResoponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(checkResoponse.getMessage())){
            return checkResoponse;
        }
        List<HopeActicity>activtilist =  hopeActicityMapper.queryWatchActivity(jsonObject);
        //该代码先注释掉
      //  List<Integer>classList= hopeActicityMapper.queryActivityClass();
        /*if(classList==null || classList.size()==0){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }*/
        /*if(activtilist==null || activtilist.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);*/
       // List<ActivityClass> relist = new ArrayList<ActivityClass>();
      //  for(ActivityClassEnum activityClassEnum:ActivityClassEnum.values()){
            //ActivityClass activityClass = new ActivityClass();
            //activityClass.setAclass(activityClassEnum.getValue());
          //  List<HopeActicity>aclist = new ArrayList<HopeActicity>();
        //pdf路径
        //String webUrlq =imgLoaclPath;
         String webUrlq =imgLoaclPath;
        //String webUrlq1 = "./"+imgLoaclPath;
            Date now =new Date();
            List<HopeActicity>activtilist1 =new ArrayList<HopeActicity>();

            if(activtilist!=null&&activtilist.size()!=0){
                for(HopeActicity hopeActicity:activtilist){
                    //  if(activityClassEnum.getKey()==hopeActicity.getTextclass()){
                    if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                        //是否点赞
                        //   HopeActivityLog hopeActivityLog = hopeActivityLogMapper.checkDianzan(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                        //  if(hopeActivityLog!=null){
                        //       hopeActicity.setDianzan(true);
                        //   }
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(jsonObject.getString("aamid"),hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //访问量
                        //  Integer acount =  hopeActivityLogMapper.queryActiviCount(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                        //   hopeActicity.setFangwenCount(acount);
                        //图片路径
                        String imagename = hopeActicity.getImagename();
                        try {
                            imagename = java.net.URLDecoder.decode(hopeActicity.getImagename(),"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+imagename;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String[] textpArr = hopeActicity.getTextpath().split("/");
                        String textname = textpArr[textpArr.length-1];
                        try {
                            textname = java.net.URLDecoder.decode(textname,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        //String textpath=webUrlq+textname;
                        String textpath=webUrlq+textname.split("\\.")[0]+".html";
                        try {
                            File htmlFile = new File(htmlPath + textname.split("\\.")[0]+".html");
                            if (!htmlFile.exists()) {
                                //FileToHtmlUtil.PoiWord03ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                                FileToHtmlUtil.PoiWord03ToHtmlS(htmlImage,htmlPath,htmlWordPth,textname);
                                //FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                            }
                               //FileToHtmlUtil.docxToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                            //FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                            //FileToHtmlUtil.convert2Html(htmlWordPth,htmlPath,textname);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                       /* try {
                            FileToHtmlUtil.fun(htmlWordPth,textname);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        hopeActicity.setTextpath(textpath);
                        //    aclist.add(hopeActicity);
                        activtilist1.add(hopeActicity);
                        continue;
                    }
                    if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                        if(hopeActicity.getEndtime().after(now)){
                            //是否点赞
                            //   HopeActivityLog hopeActivityLog = hopeActivityLogMapper.checkDianzan(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                            //  if(hopeActivityLog!=null){
                            //       hopeActicity.setDianzan(true);
                            //   }
                            //点赞量
                            Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                            hopeActicity.setDianzanliang(dianzangliang);
                            //是否收藏
                            Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(jsonObject.getString("aamid"),hopeActicity.getActivityid());
                            if(hopeUserFavors!=0){
                                hopeActicity.setShoucang(true);
                            }
                            //收藏量
                            Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                            hopeActicity.setShoucangliang(shoucangliang);
                            //访问量
                            //  Integer acount =  hopeActivityLogMapper.queryActiviCount(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                            //   hopeActicity.setFangwenCount(acount);
                            //图片路径
                            String imagename = hopeActicity.getImagename();
                            try {
                                imagename = java.net.URLDecoder.decode(hopeActicity.getImagename(),"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            String webUrlend = webUrlq+imagename;
                            hopeActicity.setImagename(webUrlend);
                            //文章路径
                            String[] textpArr = hopeActicity.getTextpath().split("/");
                            String textname = textpArr[textpArr.length-1];
                            try {
                                textname = java.net.URLDecoder.decode(textname,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            //String textpath=webUrlq+textname;
                            String textpath=webUrlq+textname.split("\\.")[0]+".html";
                            try {
                                File htmlFile = new File(htmlPath + textname.split("\\.")[0]+".html");
                                if (!htmlFile.exists()) {
                                    //FileToHtmlUtil.PoiWord03ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                                    FileToHtmlUtil.PoiWord03ToHtmlS(htmlImage,htmlPath,htmlWordPth,textname);
                                    // FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);

                                }
                                  // FileToHtmlUtil.docxToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                               //   FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                                //FileToHtmlUtil.convert2Html(htmlWordPth,htmlPath,textname);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                           /* try {
                                FileToHtmlUtil.fun(htmlWordPth,textname);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/
                            hopeActicity.setTextpath(textpath);
                            //    aclist.add(hopeActicity);
                            activtilist1.add(hopeActicity);
                            continue;
                        }
                    }
                    if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                        if(hopeActicity.getStarttime().before(now)){
                            //是否点赞
                            //   HopeActivityLog hopeActivityLog = hopeActivityLogMapper.checkDianzan(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                            //  if(hopeActivityLog!=null){
                            //       hopeActicity.setDianzan(true);
                            //   }
                            //点赞量
                            Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                            hopeActicity.setDianzanliang(dianzangliang);
                            //是否收藏
                            Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(jsonObject.getString("aamid"),hopeActicity.getActivityid());
                            if(hopeUserFavors!=0){
                                hopeActicity.setShoucang(true);
                            }
                            //收藏量
                            Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                            hopeActicity.setShoucangliang(shoucangliang);
                            //访问量
                            //  Integer acount =  hopeActivityLogMapper.queryActiviCount(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                            //   hopeActicity.setFangwenCount(acount);
                            //图片路径
                            String imagename = hopeActicity.getImagename();
                            try {
                                imagename = java.net.URLDecoder.decode(hopeActicity.getImagename(),"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            String webUrlend = webUrlq+imagename;
                            hopeActicity.setImagename(webUrlend);
                            //文章路径
                            String[] textpArr = hopeActicity.getTextpath().split("/");
                            String textname = textpArr[textpArr.length-1];
                            try {
                                textname = java.net.URLDecoder.decode(textname,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            //String textpath=webUrlq+textname;
                            String textpath=webUrlq+textname.split("\\.")[0]+".html";
                            try {
                                File htmlFile = new File(htmlPath + textname.split("\\.")[0]+".html");
                                if (!htmlFile.exists()) {
                                    //FileToHtmlUtil.PoiWord03ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                                    FileToHtmlUtil.PoiWord03ToHtmlS(htmlImage,htmlPath,htmlWordPth,textname);
                                    //FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);

                                }
                                //FileToHtmlUtil.docxToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                              //    FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                              //  FileToHtmlUtil.convert2Html(htmlWordPth,htmlPath,textname);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                           /* try {
                                FileToHtmlUtil.fun(htmlWordPth,textname);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/
                            hopeActicity.setTextpath(textpath);
                            //    aclist.add(hopeActicity);
                            activtilist1.add(hopeActicity);
                            continue;
                        }
                    }
                    if(hopeActicity.getStarttime().before(now)&& hopeActicity.getEndtime().after(now)){
                        //是否点赞
                        //   HopeActivityLog hopeActivityLog = hopeActivityLogMapper.checkDianzan(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                        //  if(hopeActivityLog!=null){
                        //       hopeActicity.setDianzan(true);
                        //   }
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(jsonObject.getString("aamid"),hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //访问量
                        //  Integer acount =  hopeActivityLogMapper.queryActiviCount(hopeActicity.getActivityid(),jsonObject.getString("aamid"));
                        //   hopeActicity.setFangwenCount(acount);
                        //图片路径
                        String imagename = hopeActicity.getImagename();
                        try {
                            imagename = java.net.URLDecoder.decode(hopeActicity.getImagename(),"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+imagename;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String[] textpArr = hopeActicity.getTextpath().split("/");
                        String textname = textpArr[textpArr.length-1];
                        try {
                            textname = java.net.URLDecoder.decode(textname,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        //String textpath=webUrlq+textname;
                        String textpath=webUrlq+textname.split("\\.")[0]+".html";
                        try {
                            File htmlFile = new File(htmlPath + textname.split("\\.")[0]+".html");
                            if (!htmlFile.exists()) {
                                //FileToHtmlUtil.PoiWord03ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                                FileToHtmlUtil.PoiWord03ToHtmlS(htmlImage,htmlPath,htmlWordPth,textname);
                                //FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);

                            }

                              // FileToHtmlUtil.docxToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                           // FileToHtmlUtil.Word2003ToHtml(htmlImage,htmlPath,htmlWordPth,textname);
                            // FileToHtmlUtil.convert2Html(htmlWordPth,htmlPath,textname);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        /*try {
                            FileToHtmlUtil.fun(htmlWordPth,textname);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        hopeActicity.setTextpath(textpath);
                        //    aclist.add(hopeActicity);
                        activtilist1.add(hopeActicity);
                        continue;
                    }
                    //}
                }
            }

            //activityClass.setList(aclist);
          //  relist.add(activityClass);
      //  }
      //  if(relist==null || relist.size()==0)
         //   return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        List<ActivityClass> relist = new ArrayList<ActivityClass>();
          for(ActivityClassEnum activityClassEnum:ActivityClassEnum.values()) {
              ActivityClass activityClass = new ActivityClass();
              activityClass.setAclass(activityClassEnum.getValue());
              if(activityClassEnum.getKey()==jsonObject.getInteger(HopeActivityReqEnum.textclass.name())){
                  activityClass.setList(activtilist1);
              }
              relist.add(activityClass);
          }
        return new BaseResponse<List<ActivityClass>>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:发现页文章点赞功能
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 9:43
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> commendhopeActivity(HopeActivityLog hopeActivityLog) {
        //参数校验
        BaseResponse aclogresponse = checkactivilog(hopeActivityLog);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(aclogresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(aclogresponse.getMessage()))
            return aclogresponse;
      //  HopeActivityLog hopeActivityLog1 = hopeActivityLogMapper.checkDianzan(hopeActivityLog.getActivityid(),hopeActivityLog.getAamid());
        /*if(hopeActivityLog1!=null)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_EXIST,BaseResponse.DATA_STATUS_EXISTER);*/
        hopeActivityLogMapper.insert(hopeActivityLog);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }


/**
* 功能描述:发现页收藏功能
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 10:23
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> colletcthopeActivity(HopeUserFavor hopeUserFavor) {
        //参数校验
        BaseResponse actfavorresponse = checkacfavor(hopeUserFavor);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(actfavorresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(actfavorresponse.getMessage()))
            return actfavorresponse;
        Integer hopeUserFavor1= hopeUserFavorMapper.checkShoucang(hopeUserFavor.getAamid(),hopeUserFavor.getActivityid());
        if(hopeUserFavor1!=0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_EXIST,BaseResponse.DATA_STATUS_EXISTER);
        hopeUserFavorMapper.insert(hopeUserFavor);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:发现页点击文章增加访问量
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 11:06
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> clickhopeActivity(HopeActivityLog hopeActivityLog) {
        //参数校验
        BaseResponse aclogresponse = checkactivilog(hopeActivityLog);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(aclogresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(aclogresponse.getMessage()))
            return aclogresponse;
        hopeActivityLogMapper.insert(hopeActivityLog);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:首页三大块点击第三块文章更新广告跳转到文章列表（该文章列表不用分类，按照发表时间降序排列）
 * @param
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeActicity>>
* @Author: qinwankang
* @Date: 2020/6/8 17:11
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeActicity>> getHopeActivitys() {
        List<HopeActicity>hlist = hopeActicityMapper.getHopeActivitys();
        if (hlist==null || hlist.size()==0)
            return new BaseResponse<List<HopeActicity>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        return new BaseResponse<List<HopeActicity>>(BaseResponse.STATUS_HANDLE_SUCCESS,hlist,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:发现页取消收藏功能
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
* @Author: qinwankang
* @Date: 2020/7/1 19:36
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Object> deleteCollActivi(HopeUserFavor hopeUserFavor) {
        //入参校验
        BaseResponse cresponse = checkdeleParam(hopeUserFavor);
        if(!cresponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!cresponse.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return cresponse;
        }
        //是否收藏
        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(hopeUserFavor.getAamid(),hopeUserFavor.getActivityid());
        if(hopeUserFavors==0){
           return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        hopeUserFavorMapper.deleteByAamidAndAcid(hopeUserFavor);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private BaseResponse checkdeleParam(HopeUserFavor hopeUserFavor) {
        if(StringUtils.isEmpty(hopeUserFavor.getAamid())){
            return new BaseResponse(HopeUserFavorRequestEnum.aamid.getReturnCode(),HopeUserFavorRequestEnum.aamid.getMsg());
        }

        if(StringUtils.isEmpty(hopeUserFavor.getActivityid())){
            return new BaseResponse(HopeUserFavorRequestEnum.activityid.getReturnCode(),HopeUserFavorRequestEnum.activityid.getMsg());
        }
       return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /**
* 功能描述:发现页收藏功能入参校验
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse
* @Author: qinwankang
* @Date: 2020/6/4 10:32
*/
    private BaseResponse checkacfavor(HopeUserFavor hopeUserFavor) {
        //body请求体校验
        JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopeUserFavor));
        if (input.size() == 0) {
            return new BaseResponse(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopeActiviLogResEnum applyCreditQuotaRequestEnum : HopeActiviLogResEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /**
* 功能描述:发现页点赞功能参数校验
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse
* @Author: qinwankang
* @Date: 2020/6/3 9:45
*/
    private BaseResponse checkactivilog(HopeActivityLog hopeActivityLog) {
        //body请求体校验
        JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopeActivityLog));
        if (input.size() == 0) {
            return new BaseResponse(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopeActiviLogResEnum applyCreditQuotaRequestEnum : HopeActiviLogResEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）辅助类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/6/2 16:52
    */
    public class ActivityClass<List>{
        private String aclass;
        private List list;
        public String getAclass() {
            return aclass;
        }
        public List getList() {
            return list;
        }
        public void setAclass(String aclass) {
            this.aclass = aclass;
        }

        public void setList(List list) {
            this.list = list;
        }
    }
    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）入参校验
     * @param hopePrivGroup
    * @return: com.icbc.zsyw.hope3.common.BaseResponse
    * @Author: qinwankang
    * @Date: 2020/6/2 16:53
    */
    private BaseResponse checkparameter(JSONObject hopePrivGroup) {
        //body请求体校验
      //  JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopePrivGroup));
        if (hopePrivGroup.size() == 0) {
            return new BaseResponse(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopePrivRequestEnum applyCreditQuotaRequestEnum : HopePrivRequestEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(hopePrivGroup.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=hopePrivGroup.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        if(null==hopePrivGroup.getInteger(HopeActivityReqEnum.textclass.name())){
          return new BaseResponse(HopeActivityReqEnum.textclass.getReturnCode(),HopeActivityReqEnum.textclass.getMsg());
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
