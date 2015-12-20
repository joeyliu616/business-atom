package com.aoe.base.integration.rest.constants;

/**
 * Created by joey on 15-12-19.
 */
public interface URLConstants {

    static final String productPrefix = "/demo";

    interface SMS{
        String prefix = productPrefix + "/sms";
        String REGISTER_CODE = prefix + "/register_code";
        String VERIFY_CODE = prefix + "/verify_code";
    }

    interface CAPTCHA{
        String prefix = productPrefix + "/captcha";
        String CAPTCHA_CODE = prefix + "/captcha_code";
        String VERIFY_CODE = prefix + "/verify_code";
    }

}
