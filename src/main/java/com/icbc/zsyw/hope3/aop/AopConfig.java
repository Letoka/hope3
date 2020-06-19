package com.icbc.zsyw.hope3.aop;


import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.config.ApiTokenConfig;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.websocket.AuthenticationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.servlet.support.RequestContext.*;

/**
 * @ClassName AopConfig
 * @Description
 * @Author qinwankang
 * @Date 2020/5/25 20:25
 * @Version V1.0
 **/
@Slf4j //声明日志
@Aspect //声明为一个 AspectJ切面
@Component  //声明为组件，这样系统启动会进行该类的初始化
public class AopConfig {
    //声明切点，可以使用*来代表任意字符，用..来表示任意个参数
   private final String operateLogPoint="execution(* com.icbc.zsyw.hope3.controller.*.*(..))";
   // @Pointcut(operateLogPoint)//切点
    public void webLog(){

    }
    //在方法
   // @Before(value="webLog()")//这个方法横向的插入到切点方法执行前
    public void beforeControll(JoinPoint joinPoint) throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       // HttpSession session = request.getSession();
     String apiToken =  request.getHeader("apiToken");
     String apiCode =  request.getHeader("apiCode");
     String version =  request.getHeader("version");
     BaseResponse baseResponse = ApiTokenConfig.checkApiToken(apiCode,apiToken,version);
     log.info("baseResponse {}",baseResponse.getMessage());
     if(!baseResponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS))
          throw new AuthenticationException("接口token存在异常！");
    }

}
