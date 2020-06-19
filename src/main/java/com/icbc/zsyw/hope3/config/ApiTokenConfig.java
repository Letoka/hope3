package com.icbc.zsyw.hope3.config;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeApiToken;
import com.icbc.zsyw.hope3.enums.HopeHeaderRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeApiTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ApiTokenConfig
 * @Description
 * @Author qinwankang
 * @Date 2020/5/26 8:38
 * @Version V1.0
 **/
@Configuration
public class ApiTokenConfig {
    @Autowired
    private static HopeApiTokenMapper hopeApiTokenMapper;
    @Value("server.version.name")
    private static  String serverVersion;

    public static BaseResponse checkApiToken(String apiCode,String apiToken,String version){
        if(HopeHeaderRequestEnum.apiCode.isNotEmpty()&& StringUtils.isEmpty(apiCode))
            return new BaseResponse<Object>(HopeHeaderRequestEnum.apiCode.getReturnCode(),HopeHeaderRequestEnum.apiCode.getMsg());
       if(HopeHeaderRequestEnum.apiToken.isNotEmpty() && StringUtils.isEmpty(apiToken))
            return new BaseResponse(HopeHeaderRequestEnum.apiToken.getReturnCode(),HopeHeaderRequestEnum.apiToken.getMsg());
       if(HopeHeaderRequestEnum.version.isNotEmpty() && StringUtils.isEmpty(version))
            return new BaseResponse(HopeHeaderRequestEnum.version.getReturnCode(),HopeHeaderRequestEnum.version.getMsg());
        if(!serverVersion.equals(version))
            return new BaseResponse(HopeHeaderRequestEnum.versionFalse.getReturnCode(),HopeHeaderRequestEnum.versionFalse.getMsg());
        HopeApiToken hopeApiToken = hopeApiTokenMapper.selectByPrimaryKey(apiCode);
        if(hopeApiToken==null){
            return new BaseResponse(HopeHeaderRequestEnum.apiNoToken.getReturnCode(),HopeHeaderRequestEnum.apiNoToken.getMsg());
        }
        //$api_token = md5('模块名' + '控制器名' + '方法名' + '2018-1-18' + '加密密钥')
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String api_token = apiCode+hopeApiToken.getApitoken();
        String md5Str  = DigestUtils.md5DigestAsHex(api_token.getBytes());
        if(md5Str.equals(apiToken))
            return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
        return new BaseResponse(HopeHeaderRequestEnum.apiTokenFalse.getReturnCode(),HopeHeaderRequestEnum.apiTokenFalse.getMsg());
    }
}
