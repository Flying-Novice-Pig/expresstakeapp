package com.graduationdesign.expresstakeapp.indentManage.dao.interfaces;

import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import com.graduationdesign.expresstakeapp.util.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndentDAO{
    /**
     * 根据订单编号和发单人手机号码查询订单信息（判断是否重复发单）
     */
    public Indent findIndentByExpressIdAndSenderPhoneAndIsExitBySender(int expressId,String senderPhone);
    /**
     * 根据发单人手机号码查询订单信息并分页
     */
    public List<Indent> getIndentInfoBySenderPhone(String phone, int pageNum, int pageCount);
    /**
     * 根据接单人手机号码查询订单信息并分页
     */
    public List<Indent> getIndentInfoByReceiverPhone(String phone,int pageNum,int pageCount);
    /**
     * 根据接单人手机号码和订单时间,订单状态查询一天内发单次数
     */
    public int getIndentInfoBySenderPhoneAndSendIndentTime(String phone);







}
