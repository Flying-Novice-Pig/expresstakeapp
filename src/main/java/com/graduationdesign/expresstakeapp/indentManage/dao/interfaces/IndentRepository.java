package com.graduationdesign.expresstakeapp.indentManage.dao.interfaces;

import com.graduationdesign.expresstakeapp.indentManage.bo.Indent;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("IndentRepository")
@Scope("prototype")
public interface IndentRepository  extends JpaRepository<Indent,Integer> {
    public Indent findIndentByIndentCode(int indentCode);




}
