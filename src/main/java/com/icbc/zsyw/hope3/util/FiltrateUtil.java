package com.icbc.zsyw.hope3.util;

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
    public static List<HopeSearchHistory_h> filtrateRepetition(List<HopeSearchHistory_h> hList){
        if(hList==null || hList.size()==0)
            return null;
        List<HopeSearchHistory_h>shortList = new ArrayList<HopeSearchHistory_h>();
        for (int i = 0; i < hList.size(); i++) {
            for (int j = 0; j < hList.size(); j++) {
                if(hList.get(j).getSearchtext().indexOf(hList.get(i).getSearchtext())>-1){
                    if(!hList.get(j).getSearchtext().equals(hList.get(i).getSearchtext())){
                        shortList.add(hList.get(i));
                        HopeSearchHistory_h hopeSearchHistory_h = hList.get(j);
                        hopeSearchHistory_h.setHotSearchcount(hopeSearchHistory_h.getHotSearchcount()+hList.get(i).getHotSearchcount());
                        hList.set(j,hopeSearchHistory_h);
                    }

                }
            }
        }
        if(shortList==null || shortList.size()==0){
            return hList;
        }
        List<HopeSearchHistory_h>finalList = new ArrayList<HopeSearchHistory_h>();
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
        return finalList;
    }
    public static List<HopeSearchHistory_h> sortList(List<HopeSearchHistory_h> hList){
        if (hList.size() == 0)
                      return null;
                for (int i = 0; i < hList.size(); i++) {
                    for (int j = 0; j < hList.size() - 1 - i; j++) {
                        if (hList.get(j + 1).getHotSearchcount() > hList.get(j).getHotSearchcount()) {
                            HopeSearchHistory_h temp = hList.get(j + 1);
                            hList.set(j+1,hList.get(j));
                            hList.set(j,temp);

                        }
                    }
                }
                 return hList;

    }
    public static void main(String[] args) {
        List<Integer>list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        list.set(3,99);
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public static String getModuleUrl(String url,String aamid,String deptid){
        String[] urlArr = url.split("\\$\\$");
        String urlRes = urlArr[0]+deptid+"&"+urlArr[2]+aamid;
        return urlRes;
    }
}
