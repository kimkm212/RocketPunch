package net.company.dto;

//채용지원
public class CompanyApplyBean {

	  //  채용지원 인덱스
    private Integer id;

    //  멤버 인덱스
    private Integer memberId;

    //  채용공고 인덱스
    private Integer recruitId;

    //  포트폴리오 주소
    private String poUrl;

    //  포트폴리오 파일선택
    private String poUrlFile;

    //  희망연봉
    private String money;

    //  휴대전화
    private String tel;

    //  메시지
    private String content;

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

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getPoUrl() {
        return poUrl;
    }

    public void setPoUrl(String poUrl) {
        this.poUrl = poUrl;
    }

    public String getPoUrlFile() {
        return poUrlFile;
    }

    public void setPoUrlFile(String poUrlFile) {
        this.poUrlFile = poUrlFile;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
	
}
