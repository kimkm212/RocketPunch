package net.member.db.dto;

import java.sql.Date;

//학교
public class MemberSchool {

	//  고유 넘버
	private Integer id;
	
	//  멤버 식별 넘버
	private Integer memberId;
	
	//  학교
	private String school;
	
	//  전공
	private String major;
	
	//  입학일시
	private Date schInYear;
	
	//  졸업일시
	private Date schOutYear;
	
	//  졸업여부(졸,재,휴,퇴)
	private String finish;
	
	//  간단설명
	private String about;
	
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
	
	public String getSchool() {
	    return school;
	}
	
	public void setSchool(String school) {
	    this.school = school;
	}
	
	public String getMajor() {
	    return major;
	}
	
	public void setMajor(String major) {
	    this.major = major;
	}
	
	public Date getSchInYear() {
	    return schInYear;
	}
	
	public void setSchInYear(Date schInYear) {
	    this.schInYear = schInYear;
	}
	
	public Date getSchOutYear() {
	    return schOutYear;
	}
	
	public void setSchOutYear(Date schOutYear) {
	    this.schOutYear = schOutYear;
	}
	
	public String getFinish() {
	    return finish;
	}
	
	public void setFinish(String finish) {
	    this.finish = finish;
	}
	
	public String getAbout() {
	    return about;
	}
	
	public void setAbout(String about) {
	    this.about = about;
	}
	
	// MemberSchool 모델 복사
	public void CopyData(MemberSchool param)
	{
	    this.id = param.getId();
	    this.memberId = param.getMemberId();
	    this.school = param.getSchool();
	    this.major = param.getMajor();
	    this.schInYear = param.getSchInYear();
	    this.schOutYear = param.getSchOutYear();
	    this.finish = param.getFinish();
	    this.about = param.getAbout();
	}
}