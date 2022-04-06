package com.dsj.dao;

import com.dsj.pojo.Member;

public interface MemberDao {
    Member getMemberByTelephone(String telephone);

    void add(Member member);

    int findMemberCountByMonth(String month);

    int getTodayNewMember(String today);

    int getTotalMember();

    int getThisWeekAndMonthNewMember(String weekMonday);
}
