package com.icbc.zsyw.hope3.config;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeApiToken;
import com.icbc.zsyw.hope3.enums.HopeHeaderRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeApiTokenMapper;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
    public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    public static String generate(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }
    // 加密后解密
    public   static  String JM(String inStr) {
        char [] a = inStr.toCharArray();
        for  ( int  i =  0 ; i < a.length; i++) {
            a[i] = (char ) (a[i] ^  't' );
        }
        String k = new  String(a);
        return  k;
    }
    public static void main(String[] args) {
      String s = JM("9a286406c252a3d14218228974e1f567");
      System.out.println(s);
    }
}
