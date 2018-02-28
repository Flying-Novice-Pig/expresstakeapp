package com.graduationdesign.expresstakeapp.userManage.service.interfaces;
import com.graduationdesign.expresstakeapp.userManage.bo.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
@Scope("prototype")
public interface UserRepository extends JpaRepository<User,Integer> {
    public User  findUserByUserPhoneAndIsDeleted(String UserPhone,int IsDeleted);

    public void deleteUserByUserPhone(String UserPhone);



}
