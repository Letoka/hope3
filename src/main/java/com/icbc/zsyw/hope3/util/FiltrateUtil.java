package com.icbc.zsyw.hope3.util;

import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory_h;

import java.util.ArrayList;
import java.util.List;

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
        //img_lianxiwm_152@2x.png
       String ss =  "img_lianxiwm_152";
       Integer h = ss.indexOf("152");

       System.out.println(h);

    }
    public static String getModuleUrl(String url,String aamid,String deptid){
        String[] urlArr = url.split("\\$\\$");
        String urlRes ="";
        if(urlArr.length>4 || urlArr.length==4){
            urlRes =urlArr[0]+deptid+"&"+urlArr[2]+aamid;
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

}
