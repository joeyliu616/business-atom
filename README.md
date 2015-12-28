# business-atom
项目业务常用模块/微服务. 最大限度保证可复用性, 降低运维难度

本项目尽可能的采用0配置的方式.  
如果提示一些配置找不到. 通常是涉及到需要保密或者费用的问题。
开发者可以将这些配置放到本机环境变量. 或者临时写到application-{*}.properties中

SMS.
目前整合的是国外软件服务提供商. Twilio. 
你需要配置sms_twilio_sid,sms_twilio_token,sms_twilio_from
                                  