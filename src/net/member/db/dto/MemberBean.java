package net.member.db.dto;

import java.sql.Timestamp;
//멤버 기본 정보

public class MemberBean {

	 //  고유 넘버
    private Integer id;

    //  성(한)
    private String firstNameK;

    //  이름(한)
    private String lastNameK;

    //  이메일
    private String email;

    //  비밀번호
    private String pwd;

    //  가입일시
    private Timestamp mDate;

    //  자기소개
    private String selfIntro;

    //  커버 이미지
    private String imageCover;

    //  프로필 이미지
    private String imageProfile;

    //  전문분야/스키
    private String experise;

    //  구직중 여부
    private Integer lookForAJob;

    //  소속회사
    private Integer cId;

    //  회사서 역할
    private String cRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstNameK() {
        return firstNameK;
    }

    public void setFirstNameK(String firstNameK) {
        this.firstNameK = firstNameK;
    }

    public String getLastNameK() {
        return lastNameK;
    }

    public void setLastNameK(String lastNameK) {
        this.lastNameK = lastNameK;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Timestamp getMDate() {
        return mDate;
    }

    public void setMDate(Timestamp mDate) {
        this.mDate = mDate;
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getExperise() {
        return experise;
    }

    public void setExperise(String experise) {
        this.experise = experise;
    }

    public Integer getLookForAJob() {
        return lookForAJob;
    }

    public void setLookForAJob(Integer lookForAJob) {
        this.lookForAJob = lookForAJob;
    }

    public Integer getCId() {
        return cId;
    }

    public void setCId(Integer cId) {
        this.cId = cId;
    }

    public String getCRole() {
        return cRole;
    }

    public void setCRole(String cRole) {
        this.cRole = cRole;
    }

}