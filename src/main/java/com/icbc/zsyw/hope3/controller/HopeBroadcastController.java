package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.*;
import com.icbc.zsyw.hope3.impl.HopeBroadcastServiceImpl;
import com.icbc.zsyw.hope3.service.HopeBroadcastService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HopeBroadcastController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/14 16:29
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeBroadcast")
@Slf4j
public class HopeBroadcastController {
    @Resource
    private HopeBroadcastService hopeBroadcastService;

    private static final Logger logger = LoggerFactory.getLogger(HopeBroadcastController.class);
/**
* 功能描述:项目测试，与业务需求没有关系（可留可不留）
 * @param request
 * @param
* @return: com.icbc.zsyw.hope3.dto.HopeBroadcast
* @Author: qinwankang
* @Date: 2020/5/21 15:24
*/

    @RequestMapping(path = {"/hello"},method = RequestMethod.POST)
    public HopeBroadcast checkUserSex(HttpServletRequest request){
      float s = 1f/3f;
         float ss = 0.33333334f;
        float ss1 = 0.3333337f;


return null;
    }


    /**
     * 功能描述:项目测试，与业务需求没有关系（可留可不留）
     * @param request
     * @return: com.icbc.zsyw.hope3.dto.HopeBroadcast
     * @Author: qinwankang
     * @Date: 2020/5/21 15:24
     */
    @RequestMapping(path = {"/helloword"},method = RequestMethod.POST)
    public Map<String,Object> helloword(HttpServletRequest request){
       /* String name = request.getParameter("name");
        String classtype = request.getParameter("classtype");
        System.out.println(name);
        System.out.println(classtype);
        ExecutorService executor = Executors.newCachedThreadPool();
      HopeBroadcast hopeBroadcast = new HopeBroadcast();
      HopeComments hopeComments = new HopeComments();
      HopeImagebar hopeImagebar = new HopeImagebar();
      hopeImagebar.setModuleurl("我代表一个公共的");
      hopeBroadcast.setBroadcastid(99);
      hopeImagebar.setImagebarid(88);
        BaseTask baseTask1 = new HopeBroadcastTask();
        baseTask1.setHopeBroadcast(hopeBroadcast);
        baseTask1.setHopeImagebar(hopeImagebar);
     Future<Map<String,Object>> future =  executor.submit(baseTask1);
        Map<String,Object>map = null;
        try {
         map = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        logger.debug("日志测试20200513qwkakag，具体时间为"+new Date()+",方法参数：");
        log.info("我是谁");*/
     //  UserTest userTest= userTestMapper.selectByPrimaryKey("1");
      //  UserTest userTest1= userTestMapper.selectByPrimaryKey("2");
      //  float u = userTest.getWeight();
      //  float u1 = userTest1.getWeight();
        /*if(u<u1){
            System.out.println("验证正确");
        }else if(u==u1){
            System.out.println("怎么相等");
        }else if(u>u1){
            System.out.println("三分之一大于0.333333335f");
        }
        System.out.println(u);
        System.out.println(u1);
        System.out.println(userTest.getWeight());
        System.out.println(userTest1.getWeight());
        */

        return null;
    }

    /**
    * 功能描述:查询“掌上播报”
     * @param request
     * @param hopeviewBroadcastPriv
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeImagebar>>
    * @Author: qinwankang
    * @Date: 2020/5/20 16:02
    */

    @RequestMapping(path = {"/queryHopeBroadcast"},method = RequestMethod.POST)
    public BaseResponse<List<HopeBroadcast>> queryHopeBroadcast(HttpServletRequest request, @RequestBody HopeviewBroadcastPriv hopeviewBroadcastPriv){
     //   log.info("queryHopeBroadcastStart hopeviewBroadcastPriv:"+JSON.toJSONString(hopeviewBroadcastPriv));
        BaseResponse<List<HopeBroadcast>> response = hopeBroadcastService.queryHopeBroadcast(hopeviewBroadcastPriv.getAamid(),hopeviewBroadcastPriv.getDeptid(),hopeviewBroadcastPriv.getOdeptid());
      //  log.info("queryHopeBroadcastEnd Result:"+ JSON.toJSONString(response));
        return response;

    }
/**
* 功能描述:查询“掌上播报”详情
 * @param request
 * @param hopeviewBroadcastPriv
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
* @Author: qinwankang
* @Date: 2020/5/21 10:11
*/
    @RequestMapping(path = {"/queryHopeBroadcastDetail"},method = RequestMethod.POST)
    public BaseResponse<List<HopeBroadcastServiceImpl.BroadcastNameResult>> queryHopeBroadcastDetail(HttpServletRequest request, @RequestBody HopeviewBroadcastPriv hopeviewBroadcastPriv){
      //  log.info("queryHopeBroadcastDetailStart hopeviewBroadcastPriv:"+JSON.toJSONString(hopeviewBroadcastPriv));
        BaseResponse<List<HopeBroadcastServiceImpl.BroadcastNameResult>> response = hopeBroadcastService.queryHopeBroadcastDetail(hopeviewBroadcastPriv.getAamid(),hopeviewBroadcastPriv.getDeptid(),hopeviewBroadcastPriv.getOdeptid());
      //  log.info("queryHopeBroadcastDetailEnd Result:"+JSON.toJSONString(response));
        return response;

    }

}
