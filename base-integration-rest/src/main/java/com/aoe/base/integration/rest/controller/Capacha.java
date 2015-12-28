package com.aoe.base.integration.rest.controller;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.integration.rest.constants.ServerConstants;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.capatcha.dto.CapatchaInfo;
import com.aoe.capatcha.service.api.CapatchaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joey on 15-12-20.
 */
@Controller
public class Capacha {

    @Resource
    CapatchaService capatchaService;

    @RequestMapping(value= "/demo/captcha/captcha_code",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<CapatchaInfo> getCaptcha(){
        CommonResponse<CapatchaInfo> response = new CommonResponse<>();

        return capatchaService.createCapatcha(OrderNoUtil.uuid(), ServerConstants.Name);
    }

    @RequestMapping(value= "/demo/captcha/captcha_image",method = RequestMethod.POST)
    public void getCaptchaImage(HttpServletResponse response) throws IOException {
        CommonResponse<CapatchaInfo> capatcha = capatchaService.createCapatcha(OrderNoUtil.uuid(), ServerConstants.Name);
        if(null != capatcha && null != capatcha.getData()){
            CapatchaInfo info = capatcha.getData();
            response.setContentType("image/png");
            response.getOutputStream().write(info.getImage());
            return;
        }

    }
}
