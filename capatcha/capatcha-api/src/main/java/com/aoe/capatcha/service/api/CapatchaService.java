package com.aoe.capatcha.service.api;

import com.aoe.base.dto.CommonResponse;
import com.aoe.capatcha.dto.CapatchaInfo;
import com.aoe.capatcha.dto.MatchResult;

/**
 * Created by joey on 15-12-20.
 */
public interface CapatchaService {

    /**
     * 生成验证码. 图片格式png, 以二进制流传输
     * @param bizNo 甲方id
     * @param firstPartName 甲方名称
     * @return
     */
    public CommonResponse<CapatchaInfo> createCapatcha(String bizNo, String firstPartName);

    /**
     * 校验验证码
     * @param uuid CapatchaInfo.uuid
     * @param code 验证码
     * @return
     */
    public CommonResponse<MatchResult> verifyCode(String uuid, String code);
}
