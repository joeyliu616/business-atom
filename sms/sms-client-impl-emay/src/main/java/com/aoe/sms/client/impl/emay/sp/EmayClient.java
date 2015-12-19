package com.aoe.sms.client.impl.emay.sp;

import cn.emay.sdk.client.api.Client;
import cn.emay.sdk.client.api.MO;
import cn.emay.sdk.client.api.StatusReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 亿美软通 客户端
 */
@Component
public class EmayClient {

    private static final Logger logger = LoggerFactory.getLogger(EmayClient.class);

    private Client client;

    @Value("${sms_emay_sp_serialno}")
    private String softwareSerialNo;
    @Value("${sms_emay_sp_key}")
    private String key;

    public Client getClient() {
        if (client == null) {
            try {
                client = new Client(softwareSerialNo, key);
            } catch (Exception e) {
                logger.error("", e);
                client = null;
            }
        }
        return client;
    }

    /**
     * 软件序列号注册、或者说是激活、软件序列号首次使用必须激活
     * 
     * @param password 软件序列号密码、密码长度为6位的数字字符串、软件序列号和密码请通过亿美销售人员获取
     * @return resultInfo(code,0) 表示成功，其余表示失败
     */
    public ResultInfo registEx(String password) {
        ResultInfo resultInfo = new ResultInfo();
        if (getClient() == null) {
            resultInfo.setCode("1");
            resultInfo.setMessage("client=null");
            logger.info("spId:{} registEx resultInfo:{}",
                    new Object[] { softwareSerialNo, resultInfo });
            return resultInfo;
        }
        int result = getClient().registEx(password);
        logger.info("spId:{} registEx result:{}", new Object[] { softwareSerialNo, result });
        if (result == 0) {
            resultInfo.setCode("0");
            resultInfo.setMessage("注册成功");
        } else if (result == 10) {
            resultInfo.setCode("10");
            resultInfo.setMessage("客户端注册失败");
        } else if (result == 303) {
            resultInfo.setCode("303");
            resultInfo.setMessage("客户端网络故障");
        } else if (result == 305) {
            resultInfo.setCode("305");
            resultInfo.setMessage("返回值不是数字字符串");
        } else if (result == 999) {
            resultInfo.setCode("999");
            resultInfo.setMessage("操作频繁");
        } else {
            resultInfo.setCode("99");
            resultInfo.setMessage("未知错误");
        }
        return resultInfo;
    }

    /**
     * 注册企业信息
     * 
     * @param infos 包含企业注册的各项信息，数组长度为8
     * @return resultInfo(code,0) 表示成功，其余表示失败
     */
    public ResultInfo registDetailInfo(List<String> infos) {
        ResultInfo resultInfo = new ResultInfo();
        if (getClient() == null) {
            resultInfo.setCode("1");
            resultInfo.setMessage("client=null");
            logger.info("spId:{} registDetailInfo resultInfo:{}",
                    new Object[] { softwareSerialNo, resultInfo });
            return resultInfo;
        }
        if (infos.size() != 8) {
            resultInfo.setCode("1");
            resultInfo.setMessage("信息数组长度必须为8");
            logger.info("spId:{} registDetailInfo resultInfo:{}",
                    new Object[]{softwareSerialNo, resultInfo});
            return resultInfo;
        }
        int result = getClient().registDetailInfo(infos.get(0), infos.get(1), infos.get(2),
                infos.get(3), infos.get(4), infos.get(5), infos.get(6), infos.get(7));
        logger.info("spId:{} registDetailInfo result:{}",
                new Object[] { softwareSerialNo, result });
        if (result == 0) {
            resultInfo.setCode("0");
            resultInfo.setMessage("注册企业信息成功");
        } else if (result == -1) {
            resultInfo.setCode("-1");
            resultInfo.setMessage("注册企业信息不符合要求");
        } else if (result == 11) {
            resultInfo.setCode("11");
            resultInfo.setMessage("企业信息注册失败");
        } else if (result == 303) {
            resultInfo.setCode("303");
            resultInfo.setMessage("客户端网络故障");
        } else if (result == 305) {
            resultInfo.setCode("305");
            resultInfo.setMessage("返回值不是数字字符串");
        } else if (result == 307) {
            resultInfo.setCode("307");
            resultInfo.setMessage("目标电话号码不符合规则，电话号码必须是以0、1开头");
        } else if (result == 999) {
            resultInfo.setCode("999");
            resultInfo.setMessage("操作频繁");
        } else {
            resultInfo.setCode("99");
            resultInfo.setMessage("未知错误");
        }
        return resultInfo;
    }

    /**
     * 软件注销 1、软件注销后像发送短信、接受上行短信接口都无法使用 2、软件可以重新注册、注册完成后软件序列号的金额保持注销前的状态
     * 
     * @return resultInfo(code,0) 表示成功，其余表示失败
     */
    public ResultInfo logout() {
        ResultInfo resultInfo = new ResultInfo();
        if (getClient() == null) {
            resultInfo.setCode("1");
            resultInfo.setMessage("client=null");
            logger.info("spId:{} logout resultInfo:{}",
                    new Object[] { softwareSerialNo, resultInfo });
            return resultInfo;
        }
        int result = getClient().logout();
        logger.info("spId:{} logout result:{}", new Object[] { softwareSerialNo, result });
        if (result == 0) {
            resultInfo.setCode("0");
            resultInfo.setMessage("注销成功");
        } else if (result == 22) {
            resultInfo.setCode("22");
            resultInfo.setMessage("注销失败");
        } else if (result == 303) {
            resultInfo.setCode("303");
            resultInfo.setMessage("客户端网络故障");
        } else if (result == 305) {
            resultInfo.setCode("305");
            resultInfo.setMessage("返回值不是数字字符串");
        } else if (result == 999) {
            resultInfo.setCode("999");
            resultInfo.setMessage("操作频繁");
        } else {
            resultInfo.setCode("99");
            resultInfo.setMessage("未知异常");
        }
        logger.info("spId:{} logout resultInfo:{}", new Object[] { softwareSerialNo, resultInfo });
        return resultInfo;
    }

    /**
     * 返回每条短信的费用
     * 
     * @return 费用值,如果失败，返回-1.0
     */
    public double getEachFee() {
        if (getClient() == null) {
            logger.info("spId:{} getEachFee failed,because client=null", softwareSerialNo);
            return -1.0;
        }
        double fee;
        fee = getClient().getEachFee();
        logger.info("spId:{} getEachFee result:{}", new Object[] { softwareSerialNo, fee });
        return fee;
    }

    /**
     * 获取账号的余额,如果失败，返回-1.0
     */
    public double getBalance() {
        double balance;
        if (getClient() == null) {
            logger.info("spId:{} getBalance failed,because client=null", softwareSerialNo);
            return -1.0;
        }
        try {
            balance = getClient().getBalance();
        } catch (Exception e) {
            logger.error("", e);
            return -1.0;
        }
        logger.info("spId:{} getBalance result:{}", new Object[] { softwareSerialNo, balance });
        return balance;
    }

    /**
     * 序列号充值
     * 
     * @param cardNo 充值卡号
     * @param cardPass 充值卡密码
     * @return ResultInfo
     */
    public ResultInfo chargeUp(String cardNo, String cardPass) {
        ResultInfo resultInfo = new ResultInfo();
        if (getClient() == null) {
            resultInfo.setCode("1");
            resultInfo.setMessage("client=null");
            logger.info("spId:{} chargeUp with cardNo:{},cardPass:{} failed,resultInfo:{}",
                    softwareSerialNo, cardNo, cardPass, resultInfo );
            return resultInfo;
        }
        int result = getClient().chargeUp(cardNo, cardPass);
        logger.info("spId:{} chargeUp with cardNo:{},cardPass:{} result:{}",
                 softwareSerialNo, cardNo, cardPass, result );
        if (result == 0) {
            resultInfo.setCode("0");
            resultInfo.setMessage("充值成功");
        } else if (result == 13) {
            resultInfo.setCode("13");
            resultInfo.setMessage("充值失败");
        } else if (result == 303) {
            resultInfo.setCode("303");
            resultInfo.setMessage("客户端网络故障");
        } else if (result == 305) {
            resultInfo.setCode("305");
            resultInfo.setMessage("返回值不是数字字符串");
        } else if (result == 999) {
            resultInfo.setCode("999");
            resultInfo.setMessage("操作频繁");
        } else {
            resultInfo.setCode("99");
            resultInfo.setMessage("未知错误");
        }
        return resultInfo;
    }

    /**
     * 即时发送信息
     * 
     * @param mobile 手机号码
     * @param content 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；
     *            亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，
     *            请客户应用程序不要自己分割短信以免造成混乱)
     * @param addSerial 扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @param smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
     * @return 返回code=0表示发送成功，其余表示发送失败
     */
    public ResultInfo sendSMS(String mobile, String content, String addSerial, int smsPriority) {
        return sendSMS(new String[] { mobile }, content, addSerial, smsPriority);
    }

    /**
     * 即时发送信息
     * 
     * @param mobiles 手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param content 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；
     *            亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，
     *            请客户应用程序不要自己分割短信以免造成混乱)
     * @param addSerial 扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @param smsPriority 优先级(级别从1到5的正整数，数字越大优先级越高，越先被发送)
     * @return 返回code=0表示发送成功，其余表示发送失败
     */
    public ResultInfo sendSMS(String[] mobiles, String content, String addSerial, int smsPriority) {
        ResultInfo resultInfo = new ResultInfo();
        if (getClient() == null) {
            resultInfo.setCode("1");
            resultInfo.setMessage("client=null");
            logger.info("spId:{} sendSMS mobiles:{},content:{} failed resultInfo:{}",
                    softwareSerialNo,
                            mobiles == null ? "null" : Arrays.toString(mobiles), content,
                            resultInfo );
            return resultInfo;
        }
        int result = getClient().sendSMS(mobiles, content, addSerial, smsPriority);
        logger.info("spId:{} sendSMS mobiles:{},content:{} result:{}",
                softwareSerialNo,
                        mobiles == null ? "null" : Arrays.toString(mobiles), content, result );
        if (result == 0) {
            resultInfo.setCode("0");
            resultInfo.setMessage("短信发送成功");
        } else if (result == 17) {
            resultInfo.setCode("17");
            resultInfo.setMessage("发送信息失败");
        } else if (result == 303) {
            resultInfo.setCode("303");
            resultInfo.setMessage("客户端网络故障");
        } else if (result == 305) {
            resultInfo.setCode("305");
            resultInfo.setMessage("返回值不是数字字符串");
        } else if (result == 307) {
            resultInfo.setCode("307");
            resultInfo.setMessage("目标电话号码不符合规则，电话号码必须是以0、1开头");
        } else if (result == 997) {
            resultInfo.setCode("997");
            resultInfo.setMessage("平台返回找不到超时的短信，该信息是否成功无法确定");
        } else if (result == 999) {
            resultInfo.setCode("998");
            resultInfo.setMessage("由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定");
        } else {
            resultInfo.setCode("99");
            resultInfo.setMessage("未知错误");
        }
        return resultInfo;
    }

    /**
     * 发送定时短信（带扩展号） 不是十分靠谱，亿美定时短信默认的情况下是 定时时间的前后5分钟发出去,定时时间最好设置当前时间一个小时之后
     * 
     * @param mobiles 手机号码(群发为字符串数组推荐最多为200个手机号码或以内)
     * @param smsContent 短信内容(最多500个汉字或1000个纯英文，emay服务器程序能够自动分割；
     *            亿美有多个通道为客户提供服务，所以分割原则采用最短字数的通道为分割短信长度的规则，
     *            请客户应用程序不要自己分割短信以免造成混乱)
     * @param sendTime 定时时间.格式为：年年年年月月日日时时分分秒秒
     * @param addSerial 扩展号 (长度小于15的字符串) 用户可通过扩展号自定义短信类别
     * @return ResultInfo
     */
    public ResultInfo sendScheduledSMS(String[] mobiles, String smsContent, String sendTime,
                                       String addSerial) {
        ResultInfo resultInfo = new ResultInfo();
        if (getClient() == null) {
            resultInfo.setCode("1");
            resultInfo.setMessage("client=null");
            logger.info("spId:{} sendScheduledSMS mobiles:{},content:{},sendTime:{} resultInfo:{}",
                    softwareSerialNo,
                            mobiles == null ? "null" : Arrays.toString(mobiles), smsContent,
                            sendTime, resultInfo );
            return resultInfo;
        }
        int result = getClient().sendScheduledSMS(mobiles, smsContent, sendTime);
        logger.info("spId:{} sendScheduledSMS mobiles:{},content:{},sendTime:{} result:{}",
                softwareSerialNo,
                        mobiles == null ? "null" : Arrays.toString(mobiles), smsContent, sendTime,
                        result );
        if (result == 0) {
            resultInfo.setCode("0");
            resultInfo.setMessage("短信发送成功");
        } else if (result == 17) {
            resultInfo.setCode("17");
            resultInfo.setMessage("发送信息失败");
        } else if (result == 18) {
            resultInfo.setCode("18");
            resultInfo.setMessage("发送定时信息失败，一般用户是定时格式不规范所致");
        } else if (result == 303) {
            resultInfo.setCode("303");
            resultInfo.setMessage("客户端网络故障");
        } else if (result == 305) {
            resultInfo.setCode("305");
            resultInfo.setMessage("返回值不是数字字符串");
        } else if (result == 307) {
            resultInfo.setCode("307");
            resultInfo.setMessage("目标电话号码不符合规则，电话号码必须是以0、1开头");
        } else if (result == 997) {
            resultInfo.setCode("997");
            resultInfo.setMessage("平台返回找不到超时的短信，该信息是否成功无法确定");
        } else if (result == 998) {
            resultInfo.setCode("998");
            resultInfo.setMessage("由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定");
        } else {
            resultInfo.setCode("99");
            resultInfo.setMessage("未知错误");
        }
        return resultInfo;
    }

    /**
     * 发送带有信息ID的短信（可查状态报告） sendSMSEx(mobiles, smsContent, addSerial, srcCharset,
     * smsPriority, smsID) 1、mobiles 手机数组长度不能超过1000 2、smsContent
     * 最多500个汉字或1000个纯英文、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、
     * 亿美推荐短信长度70字以内 3、addSerial 附加码(长度小于15的字符串) 用户可通过附加码自定义短信类别,或添加自定义主叫号码(
     * 联系亿美索取主叫号码列表) 4、srcCharset 字符编码，默认为"GBK" 5、优先级范围1~5，数值越高优先级越高(相对于同一序列号)
     * 6、信息ID，此ID可以与查询查询状态报告的方法中对照发送信息的状态（成功，失败） 7、其它定时短信发送请参考使用手册自己尝试使用
     */
    public ResultInfo sendSMSAddMessageId(String[] mobiles, String smsContent, String addSerial,
                                          String srcCharset, int smsPriority, long smsID) {
        ResultInfo resultInfo = new ResultInfo();
        if (getClient() == null) {
            resultInfo.setCode("1");
            resultInfo.setMessage("client=null");
            logger.info("spId:{} sendSMSAddMessageId mobiles:{},content:{} resultInfo:{}",
                    softwareSerialNo,
                            mobiles == null ? "null" : Arrays.toString(mobiles), smsContent,
                            resultInfo );
            return resultInfo;
        }
        int result = getClient().sendSMSEx(mobiles, smsContent, addSerial, srcCharset, smsPriority,
                smsID);
        logger.info("spId:{} sendSMSAddMessageId mobiles:{},content:{} result:{}",
                softwareSerialNo,
                        mobiles == null ? "null" : Arrays.toString(mobiles), smsContent, result);
        if (result == 0) {
            resultInfo.setCode("0");
            resultInfo.setMessage("短信发送成功");
        } else if (result == 17) {
            resultInfo.setCode("17");
            resultInfo.setMessage("发送信息失败");
        } else if (result == 18) {
            resultInfo.setCode("18");
            resultInfo.setMessage("发送定时信息失败，一般用户是定时格式不规范所致");
        } else if (result == 303) {
            resultInfo.setCode("303");
            resultInfo.setMessage("客户端网络故障");
        } else if (result == 305) {
            resultInfo.setCode("305");
            resultInfo.setMessage("返回值不是数字字符串");
        } else if (result == 307) {
            resultInfo.setCode("307");
            resultInfo.setMessage("目标电话号码不符合规则，电话号码必须是以0、1开头");
        } else if (result == 997) {
            resultInfo.setCode("997");
            resultInfo.setMessage("平台返回找不到超时的短信，该信息是否成功无法确定");
        } else if (result == 998) {
            resultInfo.setCode("998");
            resultInfo.setMessage("由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定");
        } else {
            resultInfo.setCode("99");
            resultInfo.setMessage("未知错误");
        }
        return resultInfo;
    }

    /**
     * 接收短信上行，已包含短信通道字段 1、从EUCP平台接收手机用户上行的短信 2、返回值list中的每个元素为一个Mo对象
     * 
     * @return 返回size为零或者不为零的list（list不可能=null）
     */
    public List<MO> getMO() {
        List<MO> resultList = new ArrayList<>();
        if (getClient() == null) {
            logger.info("spId:{} getMO failed:client=null", new Object[] { softwareSerialNo });
            return resultList;
        }
        try {
            resultList = getClient().getMO();
            if (resultList != null) {
                logger.info("spId getMo with list size:",
                        softwareSerialNo, resultList.size() );
            } else {
                resultList = new ArrayList<>();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultList;
    }

    /**
     * 获取下行短信的状态报告
     * 
     * @return 返回size为零或者不为零的list（list不可能=null）
     */
    public List<StatusReport> getStatusReport() {
        List<StatusReport> resultList = new ArrayList<>();
        if (getClient() == null) {
            logger.info("spId:{} getStatusReport failed:client=null",
                    new Object[] { softwareSerialNo });
            return resultList;
        }
        try {
            resultList = getClient().getReport();
            if (resultList != null) {
                logger.info("list size:" + resultList.size());
            } else {
                resultList = new ArrayList<>();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultList;
    }

    /**
     * 修改软件序列号密码、注意修改软件序列号密码以后不需要重新注册(激活)
     * 
     * @param oldPwd 旧密码
     * @param newPwd 新密码、长度为6位的数字字符串
     * @return -99程序错误,其他返回码见亿美文档
     */
    public int modifySerialPwdUpd(String oldPwd, String newPwd) {
        if (getClient() == null) {
            logger.info("spId:{} modifySerialPwdUpd failed:client=null",
                    softwareSerialNo);
            return -99;
        }
        try {
            return getClient().serialPwdUpd(oldPwd, newPwd);
        } catch (Exception e) {
            logger.error("", e);
            return -99;
        }
    }

}
