package com.graduationdesign.expresstakeapp.userManage.service.interfaces;

import com.graduationdesign.expresstakeapp.util.Result;
import com.graduationdesign.expresstakeapp.userManage.bo.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public interface UserManage {
    /**
     * 查询所有用户列表
     */
    public Result getUserInfoList();

    /**
     * 根据手机号码查询用户信息
     */
    public Result getUserInfoByPhone(String phone);

    /**
     * 新增一条记录
     */
    public Result addUserInfo(User user);

    /**
     * 根据手机号码更新用户信息
     * 默认修改用户信息是修改不了用户的密码的，并且手机号唯一，不能修改。
     */
    public Result updateUserInfoByPhone(User user);

    /**
     * 根据手机号码删除一条用户信息(逻辑删除)
     */
    public Result deleteUserInfoByPhone(String phone);

    /**
     * 修改用户密码
     * @param oldPwd
     * @param newPwd
     */
    public Result updateUserPwd(String phone,String oldPwd,String newPwd );

    public Result updateUserWealthByPhone(String phone,int wealth);
}
