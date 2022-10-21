package com.itniuma.service.impl;

import com.itniuma.dao.AccountDao;
import com.itniuma.domain.Account;
import com.itniuma.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Autowired
    private AccountDao accountDao;


    public void transfer(String out, String in, Double money) {
        accountDao.outMoney(out, money);
        int i = 1 / 0;
        accountDao.inMoney(in, money);
    }

    @Override
    public void showAll() {
        List<Account> accounts = accountDao.selectAll();
        System.out.println(accounts);
    }

}
