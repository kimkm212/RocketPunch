package net.member.db.dto;

import java.sql.Date;


//경력
public class MemberCareer {

	//  고유 넘버
	private Integer id;
	
	//  멤버 식별 넘버
	private Integer memberId;
	
	//  기업명
	private String company;
	
	//  역할
	private String role;
	
	//  입사 일시
	private Date comInDate;
	
	//  퇴사 일시
	private Date comOutDate;
	
	//  재직
	private Integer inCompany;
	
	//  간단 설명
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
	
	public String getCompany() {
	    return company;
	}
	
	public void setCompany(String company) {
	    this.company = company;
	}
	
	public String getRole() {
	    return role;
	}
	
	public void setRole(String role) {
	    this.role = role;
	}
	
	public Date getComInDate() {
	    return comInDate;
	}
	
	public void setComInDate(Date comInDate) {
	    this.comInDate = comInDate;
	}
	
	public Date getComOutDate() {
	    return comOutDate;
	}
	
	public void setComOutDate(Date comOutDate) {
	    this.comOutDate = comOutDate;
	}
	
	public Integer getInCompany() {
	    return inCompany;
	}
	
	public void setInCompany(Integer inCompany) {
	    this.inCompany = inCompany;
	}
	
	public String getAbout() {
	    return about;
	}
	
	public void setAbout(String about) {
	    this.about = about;
	}
	
	// MemberCareer 모델 복사
	public void CopyData(MemberCareer param)
	{
	    this.id = param.getId();
	    this.memberId = param.getMemberId();
	    this.company = param.getCompany();
	    this.role = param.getRole();
	    this.comInDate = param.getComInDate();
	    this.comOutDate = param.getComOutDate();
	    this.inCompany = param.getInCompany();
	    this.about = param.getAbout();
	}
}