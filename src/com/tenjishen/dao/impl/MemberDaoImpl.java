package com.tenjishen.dao.impl;

import org.springframework.stereotype.Repository;

import com.tenjishen.dao.MemberDao;
import com.tenjishen.model.Member;

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {


}
