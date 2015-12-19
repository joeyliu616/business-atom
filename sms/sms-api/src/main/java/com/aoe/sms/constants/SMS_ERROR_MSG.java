package com.aoe.sms.constants;

import com.aoe.base.constants.ErrorCode;
import com.aoe.base.dto.CommonResult;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by joey on 15-12-20.
 */
public class SMS_ERROR_MSG {


    private static AtomicInteger num = new AtomicInteger(1);
    private static Integer base = ErrorCode.SMS_Start;

    public static final CommonResult INVALID_MOBILE_NO = new CommonResult(
            (base + num.incrementAndGet()),"错误的手机号"
    );

    public static final CommonResult SEND_SMS_FAIL = new CommonResult(
            base + num.incrementAndGet(), "发送失败"
    );
    public static final CommonResult INVALID_CONTENT_TEMPLATE =  new CommonResult(
            base + num.incrementAndGet(), "非法的短信模板, 需要包含 ${code}"
    );
}
