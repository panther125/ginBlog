package com.panther.enums;

/**
 * @author Gin 琴酒
 * @data 2023/3/14 17:55
 */
public enum AppHttpCodeEnum {

    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    USERNAME_NOT_NULL(200003,"用户名不能为空"),
    PASSWORD_NOT_NULL(200004,"密码不能为空"),
    EMAIL_NOT_NULL(200005,"邮箱不能为空"),
    NICKNAME_NOT_NULL(200006,"昵称不能为空"),
    NICKNAME_EXIST(2000007,"昵称已经存在"),
    FILE_TYPE_ERROR(2000008,"文件类型不可用"),
    CONTENT_NOT_NULL(222222,"评论内容不能为空");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
