package org.navistack.framework.captcha.tencentcloud;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TencentCloudCaptchaCodes {
    /**
     * 验证通过.
     */
    final Long OK = 1L;

    /**
     * 验证码长度不匹配，请检查请求是否带Randstr参数，Randstr参数大小写是否有误.
     */
    final Long USER_CODE_LEN_ERROR = 6L;

    /**
     * 验证码答案不匹配/Randstr参数不匹配，请重新生成Randstr、Ticket进行校验.
     */
    final Long CAPTCHA_NO_MATCH = 7L;

    /**
     * 验证码签名超时，票据已过期，请重新生成Randstr、Ticket票进行校验.
     */
    final Long VERIFY_TIMEOUT = 8L;

    /**
     * 验证码签名重放，票据重复使用，请重新生成Randstr、Ticket进行校验.
     */
    final Long SEQUNCE_REPEAT = 9L;

    /**
     * 验证码签名序列.
     */
    final Long SEQUNCE_INVALID = 10L;

    /**
     * 验证码cookie信息不合法，非法请求，可能存在不规范接入.
     */
    final Long COOKIE_INVALID = 11L;

    /**
     * 签名长度错误.
     */
    final Long SIG_LEN_ERROR = 12L;

    /**
     * 不匹配，非法请求，可能存在不规范接入.
     */
    final Long VERIFY_IP_NO_MATCH_IP = 13L;

    /**
     * 验证码签名解密失败，票据校验失败，请检查Ticket票据是否与前端返回Ticket一致.
     */
    final Long DECRYPT_FAIL = 15L;

    /**
     * 验证码强校验appid错误.
     * 前端代码 data-appid 和后端 CaptchaAppId 所填写的值，
     * 必须和 验证码控制台 中【验证详情】>【基础配置】内的 AppID 一致,
     * 请检查CaptchaAppId是否为控制台基础配置界面系统分配的APPID.
     */
    final Long APPID_NO_MATCH = 16L;

    /**
     * 验证码系统命令不匹配.
     */
    final Long CMD_NO_MUCH = 17L;

    /**
     * 号码不匹配.
     */
    final Long UIN_NO_MATCH = 18L;

    /**
     * 重定向验证.
     */
    final Long SEQ_REDIRECT = 19L;

    /**
     * 操作使用pt免验证码校验错误.
     */
    final Long OPT_NO_VCODE = 20L;

    /**
     * 差别，验证错误.
     */
    final Long DIFF = 21L;

    /**
     * 验证码类型与拉取时不一致.
     */
    final Long CAPTCHA_TYPE_NOT_MATCH = 22L;

    /**
     * 验证类型错误.
     */
    final Long VERIFY_TYPE_ERROR = 23L;

    /**
     * 非法请求包.
     */
    final Long INVALID_PKG = 24L;

    /**
     * 策略拦截.
     */
    final Long BAD_VISITOR = 25L;

    /**
     * 系统内部错误.
     */
    final Long SYSTEM_BUSY = 26L;

    /**
     * 参数校验错误，CaptchaAppId 与对应 AppSecretKey 不一致，需检查 AppSecretKey 参数是否有误。
     * 其中 CaptchaAppId、 AppSecretKey 在 验证码控制台 的【验证详情】>【基础配置】中获取.
     */
    final Long PARAM_ERR_APPSECRETKEY = 100L;

    /**
     * 票据重复使用，同个票据验证多次，请重新生成Randstr、Ticket进行校验.
     */
    final Long TICKET_REUSE = 104L;
}
