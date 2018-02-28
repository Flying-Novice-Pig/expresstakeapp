package com.graduationdesign.expresstakeapp.userManage.service.impl;

import com.graduationdesign.expresstakeapp.constant.Constant;
import com.graduationdesign.expresstakeapp.indentManage.service.impl.IndentManageImpl;
import com.graduationdesign.expresstakeapp.util.ApplicationContextProvider;
import com.graduationdesign.expresstakeapp.util.Result;
import com.graduationdesign.expresstakeapp.userManage.bo.User;
import com.graduationdesign.expresstakeapp.userManage.service.interfaces.UserManage;
import com.graduationdesign.expresstakeapp.userManage.service.interfaces.UserRepository;
import com.graduationdesign.expresstakeapp.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.abs;

@Service("UserManageImpl")       //配合controller层中依赖注入此实现层的实例
@Scope("prototype")
public class UserManageImpl implements UserManage {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User user;
    @Autowired
    private ResultUtil resultUtil;

    private Result result = null;

    @Override
    public Result getUserInfoList() {
        List<User> userInfoList = userRepository.findAll();
        result = resultUtil.querySuccess(userInfoList);
        return result;
    }

    @Override
    public Result getUserInfoByPhone(String phone) {
        User userInfo = userRepository.findUserByUserPhoneAndIsDeleted(phone, Integer.parseInt(Constant.IS_DELETE_0));
        if (null != userInfo) {
            result = resultUtil.querySuccess(userInfo);
        } else {
            result = resultUtil.failure();
        }
        return result;
    }

    /**
     * 首先判断该号码所对应的IS_DELETE状态是否被删除，如果已被删除，则新增时重新将状态置为未删除。
     */
    public Result addUserInfo(User userInfo) {
        User returnUserInfo = null;
        User returnUser = userRepository.findUserByUserPhoneAndIsDeleted(userInfo.getUserPhone(), Integer.parseInt(Constant.IS_DELETE_1));
        if (null != returnUser) {
            returnUser.setIsDeleted(Integer.parseInt(Constant.IS_DELETE_0));
            returnUserInfo = userRepository.save(returnUser);
        } else {
            user.setUserName(userInfo.getUserName());
            user.setUserPhone(userInfo.getUserPhone());
            user.setUserAddress(userInfo.getUserAddress());
            user.setUserPassword(userInfo.getUserPassword());
            user.setUserWealth(5);    //新增用户时初始化财富值为0
            user.setIsDeleted(Integer.parseInt(Constant.IS_DELETE_0));
            returnUserInfo = userRepository.save(user);
        }
        if (null != returnUserInfo) {
            result = resultUtil.addOrUpdateOrDelSuccess();
        } else {
            result = resultUtil.failure();
        }
        return result;
    }

    @Override
    public Result updateUserInfoByPhone(User userInfo) {
        User returnUser = userRepository.findUserByUserPhoneAndIsDeleted(userInfo.getUserPhone(), Integer.parseInt(Constant.IS_DELETE_0));
        if (null != returnUser) {
            returnUser.setUserName(userInfo.getUserName());
            returnUser.setUserPhone(userInfo.getUserPhone());
            returnUser.setUserAddress(userInfo.getUserAddress());
            returnUser.setUserAddress(userInfo.getUserAddress());
            User returnUserInfo = userRepository.save(returnUser);         //如果要修改的信息和数据库中的数据一致，则默认不执行update语句。
            if (null != returnUserInfo) {
                result = resultUtil.addOrUpdateOrDelSuccess();
            } else {
                result = resultUtil.failure();
            }
        } else {
            result = resultUtil.failure();
        }
        return result;
    }

    @Override
    public Result deleteUserInfoByPhone(String phone) {
        User returnUser = userRepository.findUserByUserPhoneAndIsDeleted(phone, Integer.parseInt(Constant.IS_DELETE_0));
        if (null != returnUser) {
            returnUser.setIsDeleted(Integer.parseInt(Constant.IS_DELETE_1));
            User returnUserInfo = userRepository.save(returnUser);
            if (null != returnUserInfo) {
                result = resultUtil.addOrUpdateOrDelSuccess();
            } else {
                result = resultUtil.failure();
            }
        } else {
            result = resultUtil.failure();
        }

        return result;
    }

    @Override
    public Result updateUserPwd(String phone, String oldPwd, String newPwd) {
        User returnUser = userRepository.findUserByUserPhoneAndIsDeleted(phone, Integer.parseInt(Constant.IS_DELETE_0));
        String password = null;
        if (null != returnUser) {
            password = returnUser.getUserPassword();
            if (!oldPwd.equals(password)) {
                result = resultUtil.updatePwdFailure();
            } else {
                returnUser.setUserPassword(newPwd);
                User returnUserInfo = userRepository.save(returnUser);
                if (null != returnUserInfo) {
                    result = resultUtil.addOrUpdateOrDelSuccess();
                } else {
                    result = resultUtil.failure();
                }
            }
        } else {
            result = resultUtil.failure();
        }
        return result;
    }

    public Result updateUserWealthByPhone(String phone, int wealth) {
        User returnUser = userRepository.findUserByUserPhoneAndIsDeleted(phone, Integer.parseInt(Constant.IS_DELETE_0));
        if (null != returnUser) {
            if (wealth >= 0) {
                returnUser.setUserWealth(returnUser.getUserWealth() + wealth);
            } else {
                int trueWealth = returnUser.getUserWealth() - abs(wealth);
                returnUser.setUserWealth(trueWealth);
            }
            User returnUserInfo = userRepository.save(returnUser);         //如果要修改的信息和数据库中的数据一致，则默认不执行update语句。
            if (null != returnUserInfo) {
                result = resultUtil.addOrUpdateOrDelSuccess();
            } else {
                result = resultUtil.failure();
            }
        } else {
            result = resultUtil.failure();
        }
        return result;
    }
}
