package com.graduationdesign.expresstakeapp.userManage.controller;

import com.graduationdesign.expresstakeapp.indentManage.controller.BaseController;
import com.graduationdesign.expresstakeapp.util.ApplicationContextProvider;
import com.graduationdesign.expresstakeapp.util.Result;
import com.graduationdesign.expresstakeapp.userManage.bo.User;
import com.graduationdesign.expresstakeapp.userManage.service.interfaces.UserManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController      //处理http请求，并返回json数据
@Scope("prototype")
public class UserManageController{
    @Autowired        //依赖注入UserManage实现层的实例
    private UserManage userManage;

    @GetMapping(value = "/userManage/getUserInfoList")
    public Result getUserInfoList(){
        return userManage.getUserInfoList();
    }

    @GetMapping(value = "/userManage/getUserInfoByPhone/{phone}")
    public Result getUserInfoByPhone(@PathVariable("phone") String phone){
        return userManage.getUserInfoByPhone(phone);
    }
    @Transactional
    @PostMapping(value = "/userManage/addUserInfo")
    public Result addUserInfo(User user){
        return userManage.addUserInfo(user);
    }
    @Transactional
    @PostMapping(value = "/userManage/updateUserInfoByPhone")
    public Result updateUseInfoByPhone(User user){
        return userManage.updateUserInfoByPhone(user);
    }
    @Transactional
    @PostMapping(value = "/userManage/updateUserPwd")
    public Result updateUserPwd(@RequestParam("phone") String phone,
                                @RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd){
        return userManage.updateUserPwd(phone,oldPwd,newPwd);
    }

    @Transactional       //不加此注解，就必须用DeleteMapping的方式，加此注解，也可以用GetMapping
    @DeleteMapping(value = "/userManage/deleteUserInfoByPhone/{phone}")
    public Result deleteUserInfoByPhone(@PathVariable("phone") String phone){
        return userManage.deleteUserInfoByPhone(phone);
    }


}
