package com.aoe.capatcha.service.config;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * Created by joey on 15-12-20.
 */
@Configuration
public class CaptchaConfig {

    /**
     * 配置验证码尾数， 颜色.
     * @return
     */
    @Bean
    public ConfigurableCaptchaService configurableCaptchaService(){
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setMaxLength(4);
        wordFactory.setMinLength(4);
        cs.setWordFactory(wordFactory);
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        return cs;
    }




}
