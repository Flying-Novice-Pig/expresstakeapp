package com.graduationdesign.expresstakeapp.constant;

public class Constant {
    public static final int RETURN_SUCCESS_CODE = 0;
    public static final String RETURN_SUCCESS_MESSAGE = "操作成功";
    public static final int RETURN_FAILURE_CODE = -1;
    public static final String RETURN_FAILURE_MESSAGE = "操作失败，请稍后重试";
    public static final String RETURN_UPDATE_PWD_FAILURE_MESSAGE = "原密码错误，请重新设置";
    public static final String RETURN_SUCCESS_BLANK_MESSAGE = "暂无数据";

    public static final String IS_DELETE_1 = "1";   //已删除
    public static final String IS_DELETE_0 = "0";   //未删除

    public static final int IDENTITY_0 = 0;//发单人
    public static final int IDENTITY_1 = 1;//接单人

    public static final String RETURNE_ADD_INDENT_FAILURE_WEALTH = "您的剩余财富值不足！";
    public static final String RETURNE_ADD_INDENT_FAILURE_COUNT = "您有3笔或以上的订单尚未被接单，不能发单!";
    public static final String RETURNE_ADD_INDENT_FAILURE_INDENTCODE = "快递单号已存在，不能重复发单";
    public static final int  DEFAULT_ADD_INDENT_MAX_VALUE = 3;    //默认3笔订单无人接单，则不能发单


    public static final int INDENT_STATE_0 = 0;  //未接单
    public static final int INDENT_STATE_1 = 1;  //已接单
    public static final int INDENT_STATE_2 = 2;  //已完成
}
