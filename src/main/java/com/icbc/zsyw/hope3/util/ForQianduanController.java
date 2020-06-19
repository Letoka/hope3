package com.icbc.zsyw.hope3.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ForQianduanController
 * @Description
 * @Author qinwankang
 * @Date 2020/6/15 15:30
 * @Version V1.0
 **/
@Controller
@RequestMapping("/forQianduanController")
@Slf4j
public class ForQianduanController {
    @RequestMapping(path = {"/qianduan"},method = RequestMethod.GET)
    public String  fileDownLoad1(HttpServletRequest request, HttpServletResponse response , ModelMap model) throws SQLException, IOException, ServletException {
        Map<String,String> fileNameMap = new HashMap<String,String>();
        fileNameMap.put("aaaaa","qwk");
        model.addAttribute("fileNameMap", fileNameMap);
        return "listfile";
    }

}
