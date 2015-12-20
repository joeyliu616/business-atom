package com.aoe.mail.service.api;

import com.aoe.base.dto.CommonResponse;
import com.aoe.mail.dto.MailInfo;

/**
 * Created by joey on 15-12-20.
 */
public interface MailService {


    /**
     * 发送邮件
     * @param address 邮件地址
     * @param content 邮件内容
     * @param bizNo   甲方订单ID
     * @param firstPartName 甲方名称
     * @return
     */
    CommonResponse<MailInfo> sendMail(String address, String title, String content, String bizNo, String firstPartName);
}
