package com.tenjishen.dao.impl;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.AdminLoginDao;
import com.tenjishen.model.log.LogAdminLogin;

@Repository
public class AdminLoginDaoImpl extends BaseDaoImpl<LogAdminLogin, Long> implements AdminLoginDao {

}
