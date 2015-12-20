package com.aoe.mail.constants;

import com.aoe.base.constants.ErrorCode;
import com.aoe.base.dto.CommonResult;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by joey on 15-12-20.
 */
public class EMAIL_ERROR_MSG {

    private static AtomicInteger num = new AtomicInteger(1);
    private static Integer base = ErrorCode.Mail_Start;

    public static final CommonResult SEND_MAIL_FAIL = new CommonResult(
            (base + num.incrementAndGet()),"发送邮件失败"
    );

}
