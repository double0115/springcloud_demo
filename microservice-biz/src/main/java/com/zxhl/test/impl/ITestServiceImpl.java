package com.zxhl.test.impl;


import com.zxhl.test.ITestDao;
import com.zxhl.test.ITestService;
import com.zxhl.utils.Logger;
import com.zxhl.utils.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("testService")
public class ITestServiceImpl implements ITestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ITestServiceImpl.class);

    @Autowired
    ITestDao testDao;


    @Override
    public int addUser() {
        return testDao.addUser("2");
    }
}
