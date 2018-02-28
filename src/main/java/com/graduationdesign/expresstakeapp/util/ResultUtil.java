package com.graduationdesign.expresstakeapp.util;

import com.graduationdesign.expresstakeapp.constant.Constant;
import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import com.graduationdesign.expresstakeapp.indentManage.dao.impl.IndentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("ResultUtil")
@Scope("prototype")
public class ResultUtil {

   private  Result result = ApplicationContextProvider.getBean("Result",Result.class);
   public  Result querySuccess(Object object){
       result.setCode(Constant.RETURN_SUCCESS_CODE);
       result.setMessage(Constant.RETURN_SUCCESS_MESSAGE);
       result.setData(object);
       return result;
   }

   public Result queryBlank(Object object){
       result.setCode(Constant.RETURN_SUCCESS_CODE);
       result.setMessage(Constant.RETURN_SUCCESS_BLANK_MESSAGE);
       result.setData(object);
       return result;
   }

   public  Result addOrUpdateOrDelSuccess(){
       result.setCode(Constant.RETURN_SUCCESS_CODE);
       result.setMessage(Constant.RETURN_SUCCESS_MESSAGE);
       return result;
   }

   public Result failure(){
       result.setCode(Constant.RETURN_FAILURE_CODE);
       result.setMessage(Constant.RETURN_FAILURE_MESSAGE);
       return result;
   }

//   public Result expection(String message){
//       result.setCode(Constant.RETURN_FAILURE_CODE);
//       result.setMessage(message);
//       return result;
//   }

   public Result updatePwdFailure(){
       result.setCode(Constant.RETURN_FAILURE_CODE);
       result.setMessage(Constant.RETURN_UPDATE_PWD_FAILURE_MESSAGE);
       return result;
   }

   public Result addIndentFailureByWealth(){
       result.setCode(Constant.RETURN_SUCCESS_CODE);
       result.setMessage(Constant.RETURNE_ADD_INDENT_FAILURE_WEALTH);
       return result;
   }

   public Result addIndentFailureByCount(){
       result.setCode(Constant.RETURN_SUCCESS_CODE);
       result.setMessage(Constant.RETURNE_ADD_INDENT_FAILURE_COUNT);
       return result;
   }
    public Result addIndentFailureIndentCode(){
        result.setCode(Constant.RETURN_SUCCESS_CODE);
        result.setMessage(Constant.RETURNE_ADD_INDENT_FAILURE_INDENTCODE);
        return result;
    }


   public List<Indent> convertObjectToIndent(List objectList){

       List<Indent> indentList = new ArrayList<Indent>();
       if(null!=objectList&&objectList.size()>0){
           Indent indent = new Indent();
           for(int i=0;i<objectList.size();i++){
               Object[] object = (Object[])objectList.get(i);
               indent.setIndentId((int)object[0]);
               indent.setIndentCode((int)object[1]);
               indent.setIndentWealth((int)object[2]);
               indent.setExpressId((int)object[3]);
               indent.setExpressType((int)object[4]);
               indent.setExpressSize((int)object[5]);
               indent.setExpressAddress((String)object[6]);
               indent.setSenderName((String)object[7]);
               indent.setSenderPhone((String)object[8]);
               indent.setSenderAddress((String)object[9]);
               indent.setSendIndentTime((Date)object[10]);
               indent.setIndentState((int)object[11]);
               indent.setReceiverName((String)object[12]);
               indent.setReceiverPhone((String)object[13]);
               indent.setReceiveIndentTime((Date)object[14]);
               indent.setPredictArriveTime((Date)object[15]);
               indent.setCompleteIndentTime((Date)object[16]);
               indent.setIsExitBySender((int)object[17]);
               indent.setIsExitByReceiver((int)object[18]);
               indentList.add(indent);
           }

       }
       return indentList;
   }


}
