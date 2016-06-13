package net.member.db.dto;

import java.sql.Timestamp;
//개인 정보
public class MemberPrivate {

//  고유 넘버
private Integer id;

//  멤버 식별 넘버
private Integer memberId;

//  주소
private String address;

//  이메일
private String email;

//  페이스북
private String facebook;

//  트위터
private String twitter;

//  인스타그램
private String instagram;

//  깃헙
private String github;

//  behance
private String behance;

//  linkedln
private String linkedln;

//  구글
private String google;

//  블로그
private String blog;

//  병역 면제
private Boolean milEmpty;

//  입대 년도
private Timestamp milInDate;

//  전역 년도
private Timestamp milOutDate;

//  복무 중
private Boolean milOngoing;

//  병역 구분
private String milType;

//  간단설명
private String about;

//  언어
private String lang;

//  언어 구사 수준
private Integer langStep;

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

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getFacebook() {
    return facebook;
}

public void setFacebook(String facebook) {
    this.facebook = facebook;
}

public String getTwitter() {
    return twitter;
}

public void setTwitter(String twitter) {
    this.twitter = twitter;
}

public String getInstagram() {
    return instagram;
}

public void setInstagram(String instagram) {
    this.instagram = instagram;
}

public String getGithub() {
    return github;
}

public void setGithub(String github) {
    this.github = github;
}

public String getBehance() {
    return behance;
}

public void setBehance(String behance) {
    this.behance = behance;
}

public String getLinkedln() {
    return linkedln;
}

public void setLinkedln(String linkedln) {
    this.linkedln = linkedln;
}

public String getGoogle() {
    return google;
}

public void setGoogle(String google) {
    this.google = google;
}

public String getBlog() {
    return blog;
}

public void setBlog(String blog) {
    this.blog = blog;
}

public Boolean getMilEmpty() {
    return milEmpty;
}

public void setMilEmpty(Boolean milEmpty) {
    this.milEmpty = milEmpty;
}


public Timestamp getMilInDate() {
	return milInDate;
}

public void setMilInDate(Timestamp milInDate) {
	this.milInDate = milInDate;
}

public Timestamp getMilOutDate() {
	return milOutDate;
}

public void setMilOutDate(Timestamp milOutDate) {
	this.milOutDate = milOutDate;
}

public Boolean getMilOngoing() {
    return milOngoing;
}

public void setMilOngoing(Boolean milOngoing) {
    this.milOngoing = milOngoing;
}

public String getMilType() {
    return milType;
}

public void setMilType(String milType) {
    this.milType = milType;
}

public String getAbout() {
    return about;
}

public void setAbout(String about) {
    this.about = about;
}

public String getLang() {
    return lang;
}

public void setLang(String lang) {
    this.lang = lang;
}

public Integer getLangStep() {
    return langStep;
}

public void setLangStep(Integer langStep) {
    this.langStep = langStep;
}

// MemberPrivate 모델 복사
public void CopyData(MemberPrivate param)
{
    this.id = param.getId();
    this.memberId = param.getMemberId();
    this.address = param.getAddress();
    this.email = param.getEmail();
    this.facebook = param.getFacebook();
    this.twitter = param.getTwitter();
    this.instagram = param.getInstagram();
    this.github = param.getGithub();
    this.behance = param.getBehance();
    this.linkedln = param.getLinkedln();
    this.google = param.getGoogle();
    this.blog = param.getBlog();
    this.milEmpty = param.getMilEmpty();
    this.milInDate = param.getMilInDate();
    this.milOutDate = param.getMilOutDate();
    this.milOngoing = param.getMilOngoing();
    this.milType = param.getMilType();
    this.about = param.getAbout();
    this.lang = param.getLang();
    this.langStep = param.getLangStep();
}
}