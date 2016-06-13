package net.company.dto;

import java.sql.Date;
import java.sql.Timestamp;

//채용공고
public class CompanyRecruitBean {
	
	 //  채용공고 인덱스
    private Integer id;

    //  회사 인덱스
    private Integer companyId;

    //  채용정보 타이틀
    private String cTitle;

    //  채용 등록일
    private Timestamp cJobDate;

    //  채용 시작일
    private Date cJobDateStart;

    //  채용 마감일
    private Date cJobDateEnd;

    //  채용 직무분야
    private String cField;

    //  경력
    private String cCareer;

    //  주요업무
    private String cTask;

    //  근무지
    private String cLocations;

    //  연봉
    private String cSalary;

    //  문의전화
    private String cTelQuestion;

    //  채용상세정보
    private String cJobContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCTitle() {
        return cTitle;
    }

    public void setCTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public Timestamp getCJobDate() {
        return cJobDate;
    }

    public void setCJobDate(Timestamp cJobDate) {
        this.cJobDate = cJobDate;
    }

    public Date getCJobDateStart() {
        return cJobDateStart;
    }

    public void setCJobDateStart(Date cJobDateStart) {
        this.cJobDateStart = cJobDateStart;
    }

    public Date getCJobDateEnd() {
        return cJobDateEnd;
    }

    public void setCJobDateEnd(Date cJobDateEnd) {
        this.cJobDateEnd = cJobDateEnd;
    }

    public String getCField() {
        return cField;
    }

    public void setCField(String cField) {
        this.cField = cField;
    }

    public String getCCareer() {
        return cCareer;
    }

    public void setCCareer(String cCareer) {
        this.cCareer = cCareer;
    }

    public String getCTask() {
        return cTask;
    }

    public void setCTask(String cTask) {
        this.cTask = cTask;
    }

    public String getCLocations() {
        return cLocations;
    }

    public void setCLocations(String cLocations) {
        this.cLocations = cLocations;
    }

    public String getCSalary() {
        return cSalary;
    }

    public void setCSalary(String cSalary) {
        this.cSalary = cSalary;
    }

    public String getCTelQuestion() {
        return cTelQuestion;
    }

    public void setCTelQuestion(String cTelQuestion) {
        this.cTelQuestion = cTelQuestion;
    }

    public String getCJobContent() {
        return cJobContent;
    }

    public void setCJobContent(String cJobContent) {
        this.cJobContent = cJobContent;
    }
}
