<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
          import = "net.company.dto.CompanyRecruitBean,
                    net.company.dto.CompanyBean,
                    net.company.dto.CompanyApplyBean,
                    net.member.db.dto.MemberBean,
                    java.util.List,
                    java.util.HashMap"%>

<%@ include file="/sub/header.jsp" %><!-- 헤더  -->
<%
List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)request.getAttribute("list");
%>
<!------------------------------------------------------->
  <section class="container">
    <div class="row">
      <article class="col-md-12" id="comp_admin">
        <h3><strong>진행중인 채용정보</strong></h3>
      <%if(list!=null){
          for(HashMap<String, Object> e : list){
            CompanyBean cb = (CompanyBean)e.get("cb");
            CompanyRecruitBean crb = (CompanyRecruitBean)e.get("crb");
            List<HashMap<String, Object>> applyers = (List<HashMap<String, Object>>)e.get("applyers");
      %>
        <!--하나의 채용정보 시작-->
        <article class="col-md-12 admin_wrap">
          <div class="row">
            <div class="col-md-8 ">
              <div class="rec_wrap">
                <div class="info nowrap">
                  <div class="rec_img" style="background:url('<%=cb.getCLogo()%>') no-repeat center center;"></div>
                  <h4><strong><a href="/RecruitContent.co?id=<%=crb.getId()%>"><%=crb.getCTitle()%></a></strong><span><%=cb.getCNamek()%></span></h4>
                  <p><%=cb.getCIntro()%></p>
                </div>
                <span><%=crb.getCJobDateEnd()%></span>
                <div class="stacks nowrap">
                  <span class="label label-default">JAVA</span>
                  <span class="label label-primary">JSP</span>
                  <span class="label label-success">Mysql</span>
                  <span class="label label-info">Oracle</span>
                  <span class="label label-warning">JavaScript</span>
                  <span class="label label-danger">Node.js</span>
                </div>
                <div class="field_lists nowrap">
                  <span class="field"><i class="fa fa-cogs" aria-hidden="true"></i>개발자</span>
                  <span class="field"><i class="fa fa-picture-o" aria-hidden="true"></i>디자이너</span>
                  <span class="field"><i class="fa fa-building-o" aria-hidden="true"></i>기획자</span>
                  <span class="field"><i class="fa fa-briefcase" aria-hidden="true"></i>영업</span>
                  <span><%=crb.getCCareer()%></span>
                </div>
              </div><!--rec_wrap-->
            </div>
            <div class="col-md-4">
              <button type="button" name="button" class="btn btn-warning btn-block">채용 수정</button>
              <button type="button" name="button" class="btn btn-warning btn-block" onclick="location.href='/RecruitDelete.co?c_id=<%=cb.getId()%>&r_id=<%=crb.getId()%>'">채용 종료(삭제)</button>
            </div>
          </div>

          <!--지원자 목록이 나옴-->
          <div class="row recruiters">
            <h4>지원자<span><%=applyers.size()%></span></h4>
        <%if(applyers!=null){
          for(HashMap<String, Object> a : applyers){
            MemberBean mb = (MemberBean)a.get("mb");
            CompanyApplyBean cab = (CompanyApplyBean)a.get("cab");
        %>
            <!--지원자들-->
            <div class="col-sm-6">
              <div class="comp_member_wrap">
                <div class="prof_info">
                  <h4><strong><a href="MemberInfoAction.me?id=<%=mb.getId()%>"><%=mb.getFirstNameK()+mb.getLastNameK()%></a></strong></h4>
                  <p>포트폴리오 URL : <span><a href="<%=cab.getPoUrl()%>"><%=cab.getPoUrl() %></a></span></p>
                  <p>포트폴리오 FILE : <span><a href="<%=cab.getPoUrlFile() %>" download>다운받기</a></span></p>
                  <p>희망연봉 : <span><%=cab.getMoney()%></span></p>
                  <p>연락처 : <span><%=cab.getTel()%></span></p>
                  <p>메시지 : <span><%=cab.getContent()%></span></p>
                </div>
                <div class="mem_img" style="background:url('<%=mb.getImageProfile()%>') no-repeat center center;"></div>
              </div>
            </div>
<%} %>
          </div>
        </article><!--채용정보 한묶음-->
<%}}}%>
      </article><!-- #comp_admin-->
    </div>

	</section>
<!------------------------------------------------------->
<jsp:include page="/sub/footer.jsp"/><!-- 푸터  -->
