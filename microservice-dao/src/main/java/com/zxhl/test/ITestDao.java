package com.zxhl.test;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

//@DbSplit(db= BbEnum.UserDb)
@Repository
public interface ITestDao {
     @Insert("INSERT INTO test(name) VALUES (#{name});")
     int addUser(String name);
}
