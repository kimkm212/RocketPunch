package net.member.db.dto;

import java.sql.Date;

//프로젝트
public class MemberProject {

	//  고유 넘버
	private Integer id;
	
	//  멤버 식별 넘버
	private Integer memberId;
	
	//  프로젝트 이름
	private String projectName;
	
	//  대표자or회사
	private String ownership;
	
	//  참여 일시
	private Date proInYear;
	
	//  종료 일시
	private Date proOutYear;
	
	//  진행 중
	private Integer ongoing;
	
	//  결과물(웹)
	private String resultWeb;
	
	//  결과물(앱스토어)
	private String resultApple;
	
	//  결과물(플레이스토어)
	private String resultGoggle;
	
	//  팀
	private String team;
	
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
	
	public String getProjectName() {
	    return projectName;
	}
	
	public void setProjectName(String projectName) {
	    this.projectName = projectName;
	}
	
	public String getOwnership() {
	    return ownership;
	}
	
	public void setOwnership(String ownership) {
	    this.ownership = ownership;
	}
	
	public Date getProInYear() {
	    return proInYear;
	}
	
	public void setProInYear(Date proInYear) {
	    this.proInYear = proInYear;
	}
	
	public Date getProOutYear() {
	    return proOutYear;
	}
	
	public void setProOutYear(Date proOutYear) {
	    this.proOutYear = proOutYear;
	}
	
	public Integer getOngoing() {
	    return ongoing;
	}
	
	public void setOngoing(Integer ongoing) {
	    this.ongoing = ongoing;
	}
	
	public String getResultWeb() {
	    return resultWeb;
	}
	
	public void setResultWeb(String resultWeb) {
	    this.resultWeb = resultWeb;
	}
	
	public String getResultApple() {
	    return resultApple;
	}
	
	public void setResultApple(String resultApple) {
	    this.resultApple = resultApple;
	}
	
	public String getResultGoggle() {
	    return resultGoggle;
	}
	
	public void setResultGoggle(String resultGoggle) {
	    this.resultGoggle = resultGoggle;
	}
	
	public String getTeam() {
	    return team;
	}
	
	public void setTeam(String team) {
	    this.team = team;
	}
	
	public String getAbout() {
	    return about;
	}
	
	public void setAbout(String about) {
	    this.about = about;
	}
	
	// MemberProject 모델 복사
	public void CopyData(MemberProject param)
	{
	    this.id = param.getId();
	    this.memberId = param.getMemberId();
	    this.projectName = param.getProjectName();
	    this.ownership = param.getOwnership();
	    this.proInYear = param.getProInYear();
	    this.proOutYear = param.getProOutYear();
	    this.ongoing = param.getOngoing();
	    this.resultWeb = param.getResultWeb();
	    this.resultApple = param.getResultApple();
	    this.resultGoggle = param.getResultGoggle();
	    this.team = param.getTeam();
	    this.about = param.getAbout();
	}
}