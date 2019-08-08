package com.springbootweb.web.dao;

import org.apache.ibatis.annotations.Select;
import com.springbootweb.web.entity.Account;
import org.apache.ibatis.annotations.Update;

public interface AccountDao {

    @Select("select count(*) from Account where username=#{username}")
    int checkAccoByUsername(String username);

    @Select("select * from Account where username=#{username}")
    Account getAccoByUsername(String username);

    @Update("update Account set password=#{password} where username=#{username}")
    void modifyPsw(Account account);
}
