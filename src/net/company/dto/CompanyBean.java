package net.company.dto;

import java.sql.Date;
import java.sql.Timestamp;

//회사기본정보
public class CompanyBean {

	  //  회사 인덱스
    private Integer id;

    //  회사이름
    private String cNamek;

    //  회사이름 영문
    private String cNamee;

    //  회사이메일
    private String cEmail;

    //  등록일
    private Timestamp cDate;

    //  분야1
    private String cPart;

    //  채용담당자
    private String cRole;

    //  구성원수
    private Integer cMembers;

    //  회사주소
    private String cAddress;

    //  회사홈페이지
    private String cHomepage;

    //  회사전화번호
    private String cTel;

    //  회사소개(간단히)
    private String cIntro;

    //  회사소개(상세히)
    private String cIntroduce;

    //  설립일
    private Date cBirthday;

    //  회사로고
    private String cLogo;

    //  회사cover
    private String cCover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCNamek() {
        return cNamek;
    }

    public void setCNamek(String cNamek) {
        this.cNamek = cNamek;
    }

    public String getCNamee() {
        return cNamee;
    }

    public void setCNamee(String cNamee) {
        this.cNamee = cNamee;
    }

    public String getCEmail() {
        return cEmail;
    }

    public void setCEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public Timestamp getCDate() {
        return cDate;
    }

    public void setCDate(Timestamp cDate) {
        this.cDate = cDate;
    }

    public String getCPart() {
        return cPart;
    }

    public void setCPart(String cPart) {
        this.cPart = cPart;
    }

    public String getCRole() {
        return cRole;
    }

    public void setCRole(String cRole) {
        this.cRole = cRole;
    }

    public Integer getCMembers() {
        return cMembers;
    }

    public void setCMembers(Integer cMembers) {
        this.cMembers = cMembers;
    }

    public String getCAddress() {
        return cAddress;
    }

    public void setCAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getCHomepage() {
        return cHomepage;
    }

    public void setCHomepage(String cHomepage) {
        this.cHomepage = cHomepage;
    }

    public String getCTel() {
        return cTel;
    }

    public void setCTel(String cTel) {
        this.cTel = cTel;
    }

    public String getCIntro() {
        return cIntro;
    }

    public void setCIntro(String cIntro) {
        this.cIntro = cIntro;
    }

    public String getCIntroduce() {
        return cIntroduce;
    }

    public void setCIntroduce(String cIntroduce) {
        this.cIntroduce = cIntroduce;
    }

    public Date getCBirthday() {
        return cBirthday;
    }

    public void setCBirthday(Date cBirthday) {
        this.cBirthday = cBirthday;
    }

    public String getCLogo() {
        return cLogo;
    }

    public void setCLogo(String cLogo) {
        this.cLogo = cLogo;
    }

    public String getCCover() {
        return cCover;
    }

    public void setCCover(String cCover) {
        this.cCover = cCover;
    }
}
