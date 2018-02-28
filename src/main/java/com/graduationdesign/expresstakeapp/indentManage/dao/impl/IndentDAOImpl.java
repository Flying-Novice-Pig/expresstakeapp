package com.graduationdesign.expresstakeapp.indentManage.dao.impl;

import com.graduationdesign.expresstakeapp.constant.Constant;
import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import com.graduationdesign.expresstakeapp.indentManage.dao.interfaces.IndentDAO;
import com.graduationdesign.expresstakeapp.util.ApplicationContextProvider;
import com.graduationdesign.expresstakeapp.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Repository("IndentDAOImpl")
@Scope("prototype")
public class IndentDAOImpl implements IndentDAO{
    @PersistenceContext
    EntityManager em;
    private ResultUtil resultUtil = ApplicationContextProvider.getBean("ResultUtil",ResultUtil.class);

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    List<Indent> indentList = null;

    public Indent findIndentByExpressIdAndSenderPhoneAndIsExitBySender(int expressId,String senderPhone){
        String sql = "select * from indent t where t.express_id = "+expressId+" and t.sender_phone = "+senderPhone+"" +
                " and t.is_exit_by_sender = 0";
        Query query = em.createNativeQuery(sql);
        List<Object> objectList = query.getResultList();
        try{
            indentList = resultUtil.convertObjectToIndent(objectList);
        }catch(ArrayIndexOutOfBoundsException e){
            logger.error(e.getMessage());
        }finally {
            em.close();
        }
        if(null!=indentList&&indentList.size()>0){
            return indentList.get(0);
        }else{
            return null;
        }

    }
    public List<Indent> getIndentInfoBySenderPhone(String phone, int pageNum, int pageCount) {

        if (0==pageNum){
            pageNum = 1;      //默认查第一页
        }
        if(0 == pageCount){
            pageCount = 5;   //默认查5条
        }
        //分页
        int start = 0;
        int end = pageCount;
        if(pageNum>1){
            start=(pageNum-1)*pageCount+1;
            end = pageNum*pageCount;
        }

        String sql = "select * from indent t where t.sender_phone = "+phone+" and t.is_exit_by_sender = 0 order by t.indent_state limit "+start+","+end+"  ";
        Query query = em.createNativeQuery(sql);
        List<Object> objectList = query.getResultList();
        try{
            indentList = resultUtil.convertObjectToIndent(objectList);
        }catch(ArrayIndexOutOfBoundsException e){
            logger.error(e.getMessage());
        }finally {
            em.close();
        }
        return indentList;
    }

    @Override
    public List<Indent> getIndentInfoByReceiverPhone(String phone, int pageNum, int pageCount) {
        return null;
    }

    public int getIndentInfoBySenderPhoneAndSendIndentTime(String phone){
        BigInteger count = new BigInteger("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = sdf.format(new Date());
        String sql = "select count(*) from indent t where t.sender_phone = "+phone+" and t.is_exit_by_sender = 0 and t.indent_state = "+ Constant.INDENT_STATE_0+" and " +
                "Convert(t.send_indent_time,char(10)) like '"+currentTime+"%'";
        Query query = em.createNativeQuery(sql);
        List list = query.getResultList();
        for(int i=0;i<list.size();i++){
            count = (BigInteger)list.get(i);
        }
        em.close();
        return count.intValue();
    }
}
