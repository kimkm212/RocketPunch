package net.member.db.dto;

import java.sql.Date;

//라이센스
public class MemberLicense {

	//  고유 넘버
	private Integer id;
	
	//  멤버 식별 넘버
	private Integer memberId;
	
	//  자격증
	private String licenseName;
	
	//  발급 기고
	private String licenseAgency;
	
	//  수상 일시
	private Date licGetDate;
	
	//  학교/ 경력에 링크
	private Integer licOverYear;
	
	//  관련 프로젝트 링크
	private Integer licOverMonth;
	
	//  유효기간 없음
	private Boolean neverExpire;
	
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
	
	public String getLicenseName() {
	    return licenseName;
	}
	
	public void setLicenseName(String licenseName) {
	    this.licenseName = licenseName;
	}
	
	public String getLicenseAgency() {
	    return licenseAgency;
	}
	
	public void setLicenseAgency(String licenseAgency) {
	    this.licenseAgency = licenseAgency;
	}
	
	public Date getLicGetDate() {
	    return licGetDate;
	}
	
	public void setLicGetDate(Date licGetDate) {
	    this.licGetDate = licGetDate;
	}
	
	public Integer getLicOverYear() {
	    return licOverYear;
	}
	
	public void setLicOverYear(Integer licOverYear) {
	    this.licOverYear = licOverYear;
	}
	
	public Integer getLicOverMonth() {
	    return licOverMonth;
	}
	
	public void setLicOverMonth(Integer licOverMonth) {
	    this.licOverMonth = licOverMonth;
	}
	
	public Boolean getNeverExpire() {
	    return neverExpire;
	}
	
	public void setNeverExpire(Boolean neverExpire) {
	    this.neverExpire = neverExpire;
	}
	
	public String getAbout() {
	    return about;
	}
	
	public void setAbout(String about) {
	    this.about = about;
	}
	
	// MemberLicense 모델 복사
	public void CopyData(MemberLicense param)
	{
	    this.id = param.getId();
	    this.memberId = param.getMemberId();
	    this.licenseName = param.getLicenseName();
	    this.licenseAgency = param.getLicenseAgency();
	    this.licGetDate = param.getLicGetDate();
	    this.licOverYear = param.getLicOverYear();
	    this.licOverMonth = param.getLicOverMonth();
	    this.neverExpire = param.getNeverExpire();
	    this.about = param.getAbout();
	}
}