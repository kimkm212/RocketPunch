package net.member.db.dto;

import java.sql.Date;

//수상내역
public class MemberAward {
	
	//  고유 넘버
	private Integer id;
	
	//  멤버 식별 넘버
	private Integer memberId;
	
	//  수상 부문
	private String awardType;
	
	//  수상 명
	private String awardName;
	
	//  수상기관
	private String awardAgency;
	
	//  수상 일시
	private Date awardDate;
	
	public Integer getId() {
	    return id;
	}
	
	public void setId(Integer id) {
	    this.id = id;
	}
	
	public Integer getMemberId() {
	    return memberId;
	}
	
	public void setMemberId(Integer memberId) {
	    this.memberId = memberId;
	}
	
	public String getAwardType() {
	    return awardType;
	}
	
	public void setAwardType(String awardType) {
	    this.awardType = awardType;
	}
	
	public String getAwardName() {
	    return awardName;
	}
	
	public void setAwardName(String awardName) {
	    this.awardName = awardName;
	}
	
	public String getAwardAgency() {
	    return awardAgency;
	}
	
	public void setAwardAgency(String awardAgency) {
	    this.awardAgency = awardAgency;
	}
	
	public Date getAwardDate() {
	    return awardDate;
	}
	
	public void setAwardDate(Date awardDate) {
	    this.awardDate = awardDate;
	}
	
	// MemberAward 모델 복사
	public void CopyData(MemberAward param)
	{
	    this.id = param.getId();
	    this.memberId = param.getMemberId();
	    this.awardType = param.getAwardType();
	    this.awardName = param.getAwardName();
	    this.awardAgency = param.getAwardAgency();
	    this.awardDate = param.getAwardDate();
	}
}