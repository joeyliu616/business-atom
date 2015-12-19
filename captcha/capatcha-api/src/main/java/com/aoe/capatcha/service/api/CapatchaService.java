package com.aoe.capatcha.service.api;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.dto.EmptyData;
import com.aoe.capatcha.dto.CapatchaInfo;

/**
 * Created by joey on 15-12-20.
 */
public interface CapatchaService {

    /**
     * 生成验证码
     * @return
     */
    public CommonResponse<CapatchaInfo> createCapatcha();

    /**
     * 校验验证码
     * @param uuid CapatchaInfo.uuid
     * @param code 验证码
     * @return
     */
    public CommonResponse<EmptyData> verifyCode(String uuid, String code);
}
