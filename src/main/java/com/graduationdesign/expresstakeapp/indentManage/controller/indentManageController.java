package com.graduationdesign.expresstakeapp.indentManage.controller;

import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import com.graduationdesign.expresstakeapp.indentManage.service.impl.IndentManageImpl;
import com.graduationdesign.expresstakeapp.indentManage.service.interfaces.IndentManage;
import com.graduationdesign.expresstakeapp.util.ApplicationContextProvider;
import com.graduationdesign.expresstakeapp.util.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("prototype")
public class indentManageController{
    private IndentManage indentManage = ApplicationContextProvider.getBean("IndentManageImpl",IndentManageImpl.class);

    @GetMapping(value = "/indentManage/getIndentInfoBySenderPhone/{senderPhone}/{pageNum}/{pageCount}")
    public Result getIndentInfoBySenderPhone(@PathVariable("senderPhone") String senderPhone,
                                             @PathVariable("pageNum")  int pageNum,@PathVariable("pageCount") int pageCount ){
        return indentManage.getIndentBySenderPhone(senderPhone,pageNum,pageCount);
    }

    @GetMapping(value = "/indentManage/getIndentInfoByReceiverPhone/{receiverPhone}/{pageNum}/{pageCount}")
    public Result getIndentInfoByReceiverPhone(@PathVariable("receiverPhone") String receiverPhone,
                                               @PathVariable("pageNum")  int pageNum,@PathVariable("pageCount") int pageCount){
        return indentManage.getIndentByReceiverPhone(receiverPhone,pageNum,pageCount);
    }
    @GetMapping(value = "/indentManage/getIndentInfoByIndentCode/{indentCode}")
    public Result getIndentInfoByIndentCode(@PathVariable("indentCode") int indentCode){
        return indentManage.getIndentByIndentCode(indentCode);
    }

    @Transactional
    @PostMapping(value = "/indentManage/addIndentInfo")
    public Result addIndentInfo(Indent indent){
        return indentManage.addIndent(indent);
    }

    @Transactional
    @PostMapping(value = "/indentManage/updateIndentInfo")
    public Result updateIndentInfo(Indent indent){
        return indentManage.updateIndent(indent);
    }
    @Transactional
    @GetMapping(value = "/indentManage/deleteIndentInfo/{indentCode}/{phone}")
    public Result deleteIndentInfo(@PathVariable("indentCode") int indentCode,@PathVariable("phone") String phone){
        return indentManage.deleteIndentByIndentCode(indentCode,phone);
    }

}
