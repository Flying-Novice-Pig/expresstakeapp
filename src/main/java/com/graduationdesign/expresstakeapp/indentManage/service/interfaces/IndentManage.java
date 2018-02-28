package com.graduationdesign.expresstakeapp.indentManage.service.interfaces;

import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import com.graduationdesign.expresstakeapp.util.Result;

public interface IndentManage {
    /**
     * 根据发单人手机号码查询订单信息
     * @param phone
     * @return
     */
    public Result getIndentBySenderPhone(String phone,int pageNum,int pageCount);
    /**
     * 根据接单人手机号码查询订单信息
     * @param phone
     * @return
     */
    public Result getIndentByReceiverPhone(String phone,int pageNum,int pageCount);

    /**
     * 根据订单编号查询订单信息
     * @param indentCode
     * @return
     */
    public Result getIndentByIndentCode(int indentCode);

    /**
     * 新增一条订单记录
     * @param indent
     * @return
     */
    public  Result addIndent(Indent indent);

    /**
     * 根据订单编号修改订单信息
     * @param indent
     * @return
     */
    public Result updateIndent(Indent indent);

    /**
     * 根据订单编号删除一条订单记录或取消订单
     */
    public Result deleteIndentByIndentCode(int indentCode,String phone);

}
