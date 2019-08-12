package com.springbootweb.web.dao;

import org.apache.ibatis.annotations.Select;
import com.springbootweb.web.entity.Account;
import org.apache.ibatis.annotations.Update;

public interface AccountDao {
//  检测账号是否存在，不存在返回0
    @Select("select count(*) from Account where username=#{username}")
    int checkAccoByUsername(String username);
//  查找账号信息
    @Select("select * from Account where username=#{username}")
    Account getAccoByUsername(String username);
//  用户修改密码
    @Update("update Account set password=#{password} where username=#{username}")
    void modifyPsw(Account account);
}
