<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
          import = "java.util.List,
                    java.util.HashMap,
                    net.company.dto.*,
                    net.member.db.dto.MemberBean"
%>
    <%@ include file="/sub/header.jsp" %>
    <!-- 해더 삽입 부분-->
<%
List<HashMap<String,Object>> mem_list = (List<HashMap<String,Object>>)request.getAttribute("mem_list");
List<CompanyBean> com_list = (List<CompanyBean>) request.getAttribute("com_list");
List<HashMap<String,Object>> rec_list = (List<HashMap<String,Object>>)request.getAttribute("rec_list");
%>
    <section>
      <!-- 맨 위 큰그림-->
      <article class="wrap100 area_wrap" id="main_wideImg">
        <div class="textArea">
          <h2>로켓펀치에 오신걸 환영합니다.</h2>
          <p>지금 가입하고 비즈니스와 커리어를 성장시킬 수 있는 다양한 기회들을 만나보세요!</p>
          <button class="btn btn-lg btn-info" type="button" name="button">가입하기</button>
        </div>
      </article>

      <!--기업 광고 배너 -->
      <article class="container area_wrap" id="main_banner">
        <div class="row">
          <div class="col-md-6">
            <img src="imgs/banner1.jpg" alt="" />
          </div>
          <div class="col-md-6">
            <img src="imgs/banner2.jpg" alt="" />
          </div>
        </div>
      </article>

      <!-- 인기 채용정보-->
      <article class="container area_wrap" id="main_recruit">
        <h4>인기 채용정보</h4>
        <div class="row">
        <%if(rec_list !=null){
        	 for(HashMap<String, Object> e : rec_list){
             CompanyRecruitBean crb = (CompanyRecruitBean)e.get("crb");
             CompanyBean cb = (CompanyBean) e.get("cb");
          %>

          <article class="col-md-4 main_pannel">
            <div class="pannel_wrap">
              <div class="row">
                <div class="col-md-12 comp_info">
                  <div style="background:url('<%=cb.getCLogo()%>') no-repeat center center;" class="img-thumbnail entImg"></div>
                  <h5 class="nowrap"><a href="/RecruitContent.co?id=<%=crb.getId()%>"><strong><%=crb.getCTitle()%></strong></a></h5>
                  <h6><%=cb.getCNamek()%></h6>
                  <h6><%=crb.getCCareer()%></h6>
                </div>
              </div>
              <!-- 기술스택 버튼 -->
              <div class="row">
                <div class="col-md-12">
                  <button type="button" name="button" class="btn btn-xs">JAVA</button>
                  <button type="button" name="button" class="btn btn-xs">JSP</button>
                  <button type="button" name="button" class="btn btn-xs">C++</button>
                  <button type="button" name="button" class="btn btn-xs">Mysql</button>
                  <button type="button" name="button" class="btn btn-xs">JavaScript</button>
                </div>
              </div>
              <!-- 함께 일할넘 -->
              <div class="row">
                <div class="col-md-12">
                  <img src="imgs/proimg.jpg" alt="" class="img-circle"/>
                  <img src="imgs/proimg.jpg" alt="" class="img-circle"/>
                  <span><%=crb.getCJobDateEnd()%>까지</span>
                </div>
              </div>
            </div>
          </article>
          <%}} %>
<!-- --------------------------------- -->
        </div>
      </article>


      <!--인기 기업정보-->
      <article class="container area_wrap" id="main_company">
        <h4>인기 기업 정보</h4>

        <div class="row">
        <% if(com_list!=null){
          for(CompanyBean e : com_list){
        %>
          <article class="col-md-4 main_pannel">
            <div class="pannel_wrap">
              <div class="wrap100 compHead" style="background:url('<%=e.getCCover()%>') no-repeat center center;">
                <div style="background:url('<%=e.getCLogo()%>') no-repeat center center;"></div>
              </div>
              <div class="comp_info">
                <h4><strong><a href="/CompanyInfo.co?id=<%=e.getId()%>"><%=e.getCNamek()%></a></strong></h4>
                <p><%=e.getCIntro()%></p>
                <div class="row">
                  <div class="col-md-12">
                    <img src="imgs/proimg.jpg" alt="" class="img-circle"/>
                    <img src="imgs/proimg.jpg" alt="" class="img-circle"/>
                  </div>
                </div>
              </div>
            </div>
          </article>
        <%}}%>

        </div>
      </article>

      <!-- 인기 프로필 -->
      <article class="container area_wrap" id="main_profile">
        <h4>인기 프로필</h4>

        <div class="row">
       <%if(mem_list != null){
    	     for(HashMap<String, Object> e : mem_list){
            MemberBean mb = (MemberBean)e.get("mb");
            CompanyBean cb = (CompanyBean)e.get("cb");
        %>
          <article class="col-md-6 main_pannel">
            <div class="container-fluid pannel_wrap">
              <div class="prof_info">
                <h4><strong><a href="MemberInfoAction.me?id=<%=mb.getId()%>"><%=mb.getFirstNameK()+mb.getLastNameK()%></a></strong></h4>
                <p><%=mb.getCRole()%></p>
                <p><%=cb.getCNamek()%></p>
              </div>
              <div class="pro_img" style="background:url('<%=mb.getImageProfile()%>') no-repeat center center;"></div>
            </div>
          </article>
      <%}}%>

        </div>
      </article>

    </section>
<!-- 푸터삽입부분 -->
    <%@ include file="sub/footer.jsp" %>
<!-- 푸터삽입부분 -->
