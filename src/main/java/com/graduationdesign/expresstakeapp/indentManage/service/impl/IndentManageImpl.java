package com.graduationdesign.expresstakeapp.indentManage.service.impl;

import com.graduationdesign.expresstakeapp.constant.Constant;
import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import com.graduationdesign.expresstakeapp.indentManage.dao.impl.IndentDAOImpl;
import com.graduationdesign.expresstakeapp.indentManage.dao.interfaces.IndentDAO;
import com.graduationdesign.expresstakeapp.indentManage.service.interfaces.IndentManage;
import com.graduationdesign.expresstakeapp.indentManage.dao.interfaces.IndentRepository;
import com.graduationdesign.expresstakeapp.userManage.bo.User;
import com.graduationdesign.expresstakeapp.userManage.service.impl.UserManageImpl;
import com.graduationdesign.expresstakeapp.userManage.service.interfaces.UserManage;
import com.graduationdesign.expresstakeapp.userManage.service.interfaces.UserRepository;
import com.graduationdesign.expresstakeapp.util.ApplicationContextProvider;
import com.graduationdesign.expresstakeapp.util.StringConverterDate;
import com.graduationdesign.expresstakeapp.util.Result;
import com.graduationdesign.expresstakeapp.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service("IndentManageImpl")
@Scope("prototype")
public class IndentManageImpl implements IndentManage{

    private IndentDAO indentDAO = ApplicationContextProvider.getBean("IndentDAOImpl",IndentDAOImpl.class);
    private IndentRepository indentRepository = ApplicationContextProvider.getBean("IndentRepository",IndentRepository.class);
    private UserRepository userRepository =  ApplicationContextProvider.getBean("UserRepository",UserRepository.class);
    private ResultUtil resultUtil = ApplicationContextProvider.getBean("ResultUtil",ResultUtil.class);
    private UserManage userManage = ApplicationContextProvider.getBean("UserManageImpl",UserManageImpl.class);

    private Result result = null;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result getIndentBySenderPhone(String phone,int pageNum,int pageCount) {
        List<Indent> indentList = indentDAO.getIndentInfoBySenderPhone(phone,pageNum,pageCount);
        if(null!=indentList&&indentList.size()>0){
            result =  resultUtil.querySuccess(indentList);
        }else{
            result =  resultUtil.queryBlank(indentList);
        }
        return result;
    }

    public Result getIndentByReceiverPhone(String phone,int pageNum,int pageCount){
        List<Indent> indentList = indentDAO.getIndentInfoByReceiverPhone(phone,pageNum,pageCount) ;
        if(null!=indentList&&indentList.size()>0){
            result =  resultUtil.querySuccess(indentList);
        }else{
            result =  resultUtil.queryBlank(indentList);
        }
        return result;


    }

    public Result getIndentByIndentCode(int indentCode){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentTime = sdf.format(new Date());
        System.out.println(currentTime);
        Indent indent = indentRepository.findIndentByIndentCode(indentCode);
        if(null!=indent){
            result =  resultUtil.querySuccess(indent);
        }else{
            result =  resultUtil.queryBlank(indent);
        }
        return result;
    }

    @Override
    public  Result addIndent(Indent indent) {
        int falg = 0;
            try{
                User userInfo = userRepository.findUserByUserPhoneAndIsDeleted(indent.getSenderPhone(), Integer.parseInt(Constant.IS_DELETE_0));
                if(indent.getIndentWealth()>userInfo.getUserWealth()){     //判断用户剩余财富值是否足够
                    result = resultUtil.addIndentFailureByWealth();
                    falg = 1;
                }
                if(0==falg){
                    Indent indentInfo = indentDAO.findIndentByExpressIdAndSenderPhoneAndIsExitBySender(indent.getExpressId(),indent.getSenderPhone());
                    if(null!=indentInfo){  //判断是否重复发单
                        result = resultUtil.addIndentFailureIndentCode();
                        falg = 1;
                    }
                }

                if (0==falg){
                    int count = indentDAO.getIndentInfoBySenderPhoneAndSendIndentTime(indent.getSenderPhone());
                    if(count>=Constant.DEFAULT_ADD_INDENT_MAX_VALUE){         //您有3笔或以上的订单尚未被接单，不能发单
                        result = resultUtil.addIndentFailureByCount();
                        falg = 1;
                    }
                }

                if(0==falg){
                    indent.setIndentState(Constant.INDENT_STATE_0);  //新增加一个订单时，默认订单的初始状态为0，未接单
                    indent.setSendIndentTime(new Date(System.currentTimeMillis()));
                    Indent indentInfo = (Indent) indentRepository.save(indent);
                    if(null!=indentInfo){
                        //pushAndroid.jiguangPush();
                        userManage.updateUserWealthByPhone(indent.getSenderPhone(),-indent.getIndentWealth());//新增订单成功时，扣除财富值。
                        result = resultUtil.addOrUpdateOrDelSuccess();
                    }else{
                        result = resultUtil.failure();
                    }
                }
            }catch (Exception e){
                logger.error(e.getMessage());
                result = resultUtil.failure();
                throw e;
            }
        return result;
    }

    @Override
    public Result updateIndent(Indent indent) {
        Lock lock = new ReentrantLock();
        Indent returnIndentInfo = null;
        lock.lock();
        try{
            Indent indentInfo = indentRepository.findIndentByIndentCode(indent.getIndentCode());
            if(Constant.INDENT_STATE_1==indentInfo.getIndentState()){      //用户完成订单时的情况
                indentInfo.setIndentState(indent.getIndentState());
                indentInfo.setCompleteIndentTime(new Date(System.currentTimeMillis()));
                returnIndentInfo = indentRepository.save(indentInfo);
                userManage.updateUserWealthByPhone(indent.getReceiverPhone(),indent.getIndentWealth());//向接单人账户充入财富值
            }else{
                if(Constant.INDENT_STATE_0==indent.getIndentState()){    //发单人修改订单信息
                    indentInfo.setIndentWealth(indent.getIndentWealth());
                    indentInfo.setExpressId(indent.getExpressId());
                    indentInfo.setExpressType(indent.getExpressType());
                    indentInfo.setExpressSize(indent.getExpressSize());
                    indentInfo.setExpressAddress(indent.getExpressAddress());
                    indentInfo.setSenderName(indent.getSenderName());
                    indentInfo.setSenderPhone(indent.getSenderPhone());
                    indentInfo.setSenderAddress(indent.getSenderAddress());
                    indentInfo.setIndentState(indent.getIndentState());
                    indentInfo.setSendIndentTime(new Date(System.currentTimeMillis()));

                }else{        //接单人接单时的情况
                    indentInfo.setReceiverName(indent.getReceiverName());
                    indentInfo.setReceiverPhone(indent.getReceiverPhone());
                    indentInfo.setReceiveIndentTime(new Date(System.currentTimeMillis()));
                    indentInfo.setPredictArriveTime(indent.getPredictArriveTime());
                    indentInfo.setIndentState(indent.getIndentState());
                    indentInfo.setIsExitByReceiver(0);
                }

                returnIndentInfo = indentRepository.save(indentInfo);
            }
            if(null!=returnIndentInfo){
                result = resultUtil.addOrUpdateOrDelSuccess();
            }else{
                result = resultUtil.failure();
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            result = resultUtil.failure();
            throw e;
        }finally {
            lock.unlock();
        }
        return result;
    }

    @Override
    public Result deleteIndentByIndentCode(int indentCode,String phone) {
        Indent returnIndentInfo = null;
        Result result = null;
        Indent indentInfo = indentRepository.findIndentByIndentCode(indentCode);
        if(Constant.INDENT_STATE_0==indentInfo.getIndentState()){
            indentRepository.delete(indentInfo);//订单未被接受时，直接物理删除
            result =  userManage.updateUserWealthByPhone(indentInfo.getSenderPhone(),indentInfo.getIndentWealth());//用户取消订单时，返回财富值到账户
        }else if(Constant.INDENT_STATE_2==indentInfo.getIndentState()){   //订单已完成的情况
            if(phone.equals(indentInfo.getSenderPhone())){       //发单人删除订单记录
                indentInfo.setIsExitBySender(1);
            }else{                                   //接单人删除订单记录
                indentInfo.setIsExitByReceiver(1);
            }
            returnIndentInfo = indentRepository.save(indentInfo);
            if(null!=returnIndentInfo){
                result = resultUtil.addOrUpdateOrDelSuccess();
            }

        }else{        //订单已经被接受后取消订单的情况
            if(phone.equals(indentInfo.getSenderPhone())){       //发单人取消订单
                indentInfo.setIsExitBySender(1);
                //已接单的情况下发单人取消订单需要扣除2财富值
                userManage.updateUserWealthByPhone(indentInfo.getSenderPhone(),-2);
            }else{                                   //接单人取消订单
                indentInfo.setIsExitByReceiver(1);
                indentInfo.setIndentState(Constant.INDENT_STATE_0);
                indentInfo.setReceiverName(null);
                indentInfo.setReceiverPhone(null);
                indentInfo.setReceiveIndentTime(null);
                indentInfo.setPredictArriveTime(null);
                userManage.updateUserWealthByPhone(indentInfo.getReceiverPhone(),-2);
            }
            returnIndentInfo = indentRepository.save(indentInfo);
            if(null!=returnIndentInfo){
                result = resultUtil.addOrUpdateOrDelSuccess();
            }
        }
        return result;
    }
}
