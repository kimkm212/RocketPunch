package net.member.db.dto;

//친구 관계
public class MemberFriend {

//  멤버 식별 넘버 1
private Integer memberId1;

//  멤버 식별 넘버 2
private Integer memberId2;

//  관계
private String relation;

public Integer getMemberId1() {
    return memberId1;
}

public void setMemberId1(Integer memberId1) {
    this.memberId1 = memberId1;
}

public Integer getMemberId2() {
    return memberId2;
}

public void setMemberId2(Integer memberId2) {
    this.memberId2 = memberId2;
}

public String getRelation() {
    return relation;
}

public void setRelation(String relation) {
    this.relation = relation;
}

// MemberFriend 모델 복사
public void CopyData(MemberFriend param)
{
    this.memberId1 = param.getMemberId1();
    this.memberId2 = param.getMemberId2();
    this.relation = param.getRelation();
}
}