package com.icbc.zsyw.hope3.util;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory_h;
import com.icbc.zsyw.hope3.enums.EmailReqEnum;
import com.icbc.zsyw.hope3.enums.UserMobileReqEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName FiltrateUtil
 * @Description
 * @Author qinwankang
 * @Date 2020/5/15 13:57
 * @Version V1.0
 **/
public class FiltrateUtil {
    public static List<HopeSearchHistory> filtrateRepetition(List<HopeSearchHistory> hList){
        if(hList==null || hList.size()==0)
            return null;
        List<HopeSearchHistory>shortList = new ArrayList<HopeSearchHistory>();
        for (int i = 0; i < hList.size(); i++) {
            for (int j = 0; j < hList.size(); j++) {
                if(hList.get(j).getSearchtext().indexOf(hList.get(i).getSearchtext())>-1){
                    if(!hList.get(j).getSearchtext().equals(hList.get(i).getSearchtext())){
                        shortList.add(hList.get(i));
                        HopeSearchHistory hopeSearchHistory_h = hList.get(j);
                        hopeSearchHistory_h.setHotSearchcount(hopeSearchHistory_h.getHotSearchcount()+hList.get(i).getHotSearchcount());
                        hList.set(j,hopeSearchHistory_h);
                    }

                }
            }
        }
        if(shortList==null || shortList.size()==0){
            return hList;
        }
        List<HopeSearchHistory>finalList = new ArrayList<HopeSearchHistory>();
        Integer teInt = 0;
        for (int i = 0; i < hList.size(); i++) {
            for (int j = 0; j < shortList.size(); j++) {
                if(hList.get(i)==shortList.get(j)){
                    teInt++;
                }
            }
            if(teInt==0){
                finalList.add(hList.get(i));
            }
            teInt=0;
        }
        finalList=sortList(finalList);
        return finalList;
    }
    public static List<HopeSearchHistory> sortList(List<HopeSearchHistory> hList){
        if (hList.size() == 0)
                      return null;
                for (int i = 0; i < hList.size(); i++) {
                    for (int j = 0; j < hList.size() - 1 - i; j++) {
                        if (hList.get(j + 1).getHotSearchcount() > hList.get(j).getHotSearchcount()) {
                            HopeSearchHistory temp = hList.get(j + 1);
                            hList.set(j+1,hList.get(j));
                            hList.set(j,temp);

                        }
                    }
                }
                 return hList;

    }
    public static void main(String[] args) {
        String url = "agagonagds$$assdjdslg$$sadkgdg$$";
        String[]urls = url.split("\\$\\$");
        for(String s:urls){
            System.out.println(s);
        }

    }
    public static String getModuleUrl(String url,String aamid,String deptid){
        //http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx?aamid=$$aamid$$
        //http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx?deptid=$$deptid$$
        //http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx?deptid=$$deptid$$aamid=$$aamid$$
        //
        String[] urlArr = url.split("\\$\\$");
        String urlRes ="";
        if(urlArr.length>4 || urlArr.length==4){
            if(urlArr[1].equals("deptid")){
                urlRes =urlArr[0]+deptid+"&"+urlArr[2]+aamid;
            }else if(urlArr[1].equals("aamid")){
                urlRes =urlArr[0]+aamid+"&"+urlArr[2]+deptid;
            }
        }
        if(urlArr.length<4){
            if(urlArr[1].equals("deptid")){
                urlRes =urlArr[0]+deptid;
            }else if(urlArr[1].equals("aamid")){
                urlRes =urlArr[0]+aamid;
            }
        }
        return urlRes;
    }
    public static String getModuleSmallImage(String imageStr){
        //img_lianxiwm_152@2x.png
        String[] urlArr = imageStr.split("152");
        String strRe ="";
        if(urlArr.length>2 || urlArr.length==2){
           strRe = urlArr[0]+"95"+urlArr[1];
        }
        return strRe;
    }
    //手机号判断
    public static BaseResponse matchMobile(String mobileStr){
      Integer count = mobileStr.length();
      if(count!=11)
          return new BaseResponse<>(UserMobileReqEnum.mobileNumCount.getReturnCode(),UserMobileReqEnum.mobileNumCount.getMsg());
        String pattern = "\\D+";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(mobileStr);
        if(m.find()){
            return new BaseResponse<>(UserMobileReqEnum.mobileNumStyle.getReturnCode(),UserMobileReqEnum.mobileNumStyle.getMsg());
        }else {
          pattern = "1";
            // 创建 Pattern 对象
            Pattern r1 = Pattern.compile(pattern);
            // 现在创建 matcher 对象
            Matcher m1 = r1.matcher(mobileStr.split("")[0]);
            if(!m1.find()){
                return new BaseResponse<>(UserMobileReqEnum.mobileNumType.getReturnCode(),UserMobileReqEnum.mobileNumType.getMsg());
            }else {
                pattern = "[^3-8]";
                // 创建 Pattern 对象
                Pattern r2 = Pattern.compile(pattern);
                // 现在创建 matcher 对象
                Matcher m2 = r2.matcher(mobileStr.split("")[1]);
                if (m2.find()) {
                    return new BaseResponse<>(UserMobileReqEnum.mobileNumType.getReturnCode(), UserMobileReqEnum.mobileNumType.getMsg());
                }
            }
        }
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
//电子邮箱判断
    public static BaseResponse matchmail(String mailStr){
       if(!mailStr.contains("@")){
           return new BaseResponse(EmailReqEnum.mailSymbol.getReturnCode(),EmailReqEnum.mailSymbol.getMsg());
       }
       if(mailStr.split("@").length<=1){
           return new BaseResponse(EmailReqEnum.mailcom.getReturnCode(),EmailReqEnum.mailcom.getMsg());
       }
       if(!mailStr.split("@")[1].contains(".com")&&!mailStr.split("@")[1].contains(".cn")){
           return new BaseResponse(EmailReqEnum.mailcom.getReturnCode(),EmailReqEnum.mailcom.getMsg());
       }
       return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    //办公室电话判断
    public static BaseResponse matchOfficeMobile(String mobileStr){
        Integer count = mobileStr.length();
        if(count!=8&&count!=7)
            return new BaseResponse<>(UserMobileReqEnum.officemobileNumCount.getReturnCode(),UserMobileReqEnum.officemobileNumCount.getMsg());
        String pattern = "\\D+";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(mobileStr);
        if(m.find()){
            return new BaseResponse<>(UserMobileReqEnum.officemobileNumStyle.getReturnCode(),UserMobileReqEnum.officemobileNumStyle.getMsg());
        }
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
