package com.dsj.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dsj.dao.MemberDao;
import com.dsj.pojo.Member;
import com.dsj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Author    DSJ
 * date:   2022/4/4 11:54
 **/
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.getMemberByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
       memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
        ArrayList<Integer> list = new ArrayList<>();
        if(months!=null && months.size()>0){
             for (String month : months) {
                int count= memberDao.findMemberCountByMonth(month);
                list.add(count);
             }
         }
        return list;
    }
}
