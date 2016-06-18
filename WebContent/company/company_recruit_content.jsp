<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        import="net.company.dto.CompanyRecruitBean,
                net.company.dto.CompanyBean,
                net.member.db.dto.MemberBean,
                java.util.HashMap,
                java.util.List"%>
<%@ include file="/sub/header.jsp" %><!-- 헤더  -->
<%
  HashMap<String, Object> result = (HashMap<String, Object>)request.getAttribute("result");
  List<HashMap<String, Object>> memberList = (List<HashMap<String, Object>>)request.getAttribute("memberList");
  CompanyBean cb = (CompanyBean)result.get("cb");
  CompanyRecruitBean crb = (CompanyRecruitBean)result.get("crb");
%>
<section>
  <article class="" id="job_li_head">
  <div class="container">
  <div class="row">
  	<div class="col-md-8 comp_pannel">
  		<div class="com_img" style="background:url('<%=cb.getCLogo()%>') no-repeat center center;"></div>
  		<div class="comp_info">
  			<h3><strong><%=crb.getCTitle() %></strong></h3>
  			<p><%=cb.getCNamek()%></p>
  			<span class="label label-default">JAVA</span>
  			<span class="label label-primary">JSP</span>
  			<span class="label label-success">Mysql</span>
  			<span class="label label-info">Oracle</span>
  			<span class="label label-warning">JavaScript</span>
  			<span class="label label-danger">Node.js</span>
  		</div>
  	</div><!-- <div class="col-md-8 comp_pannel"> -->
  	<div class="col-md-4 btn_area">
    <%if(sessionC_Id==cb.getId()){ %>
    	<button data-toggle="modal" data-target="#recruit_update" type="button" class="btn btn-primary btn-block">채용수정</button>
    <%}%>
  		<button data-toggle="modal" data-target="#apply_modal" type="button" class="btn btn-primary btn-block">지원하기</button>
  	</div><!-- <div class="col-md-4 btn_area"> -->
  </div><!-- <div class="row"> -->
  </div><!-- <div class="container"> -->
  </article>
  <article class="container" id="job_bwrap">
    <div class="row">
    	<!-- 옆구리 부분임 -->
      <article class="col-md-4 col-md-push-8" id="job_li_aside">
        <div class="aside_pannel">
          <table class="table">
            <tr>
              <td>채용분야</td><td><b><%=crb.getCField() %></b></td>
            </tr>
            <tr>
              <td>직무분야</td><td><b>웹 노예</b></td>
            </tr>
            <tr>
              <td>경력여부</td><td><b><%=crb.getCCareer() %></b></td>
            </tr>
            <tr>
              <td>연봉</td><td><b><%=crb.getCSalary() %></b></td>
            </tr>
            <tr>
              <td>마감일</td><td><b><%=crb.getCJobDateEnd() %></b></td>
            </tr>
            <tr>
              <td>수정일</td><td><b><%=crb.getCJobDate() %></b></td>
            </tr>
          </table>
        </div><!-- <div class="aside_pannel"> -->
      </article>

      <div class="col-md-8 col-md-pull-4" id="job_li_body">

        <article class="rec_role">
          <h3>주요업무</h3>
          <div class="comp_pannel">
            <p><%=crb.getCTask() %></p>
          </div>
        </article>

        <article class="rec_role">
          <h3>채용상세</h3>
          <div class="comp_pannel">
            <p><%=crb.getCJobContent() %></p>
          </div>
        </article>

        <article class="job_li_elements">
          <h3>함께 일할 사람들</h3>
          <div class="row">
          <%if(memberList!=null){
        	     for(HashMap<String, Object> e : memberList){
        	    	 MemberBean mb = (MemberBean)e.get("mb");
        	       
          %>
            <div class="col-sm-6">
              <div class="comp_member_wrap">
                <div class="prof_info">
                  <h4><strong><a href="/MemberInfoAction.me?id=<%=mb.getId()%>"><%=mb.getFirstNameK()+mb.getLastNameK()%></a></strong></h4>
                  <p><%=mb.getCRole()%></p>
                  <p><%=cb.getCNamek()%></p>
                </div>
                <div class="mem_img" style="background:url('<%=mb.getImageProfile()%>') no-repeat center center;"></div>
              </div>
            </div>
      <%}}%>

          </div>
        </article>

        <article class="job_li_elements">
          <h3>기업소개</h3>
          <div class="comp_pannel">
            <p><%=cb.getCIntroduce()%></p>
          </div>
        </article>


        <article class="job_li_elements">
          <h3>복지/혜택</h3>
          <div class="comp_pannel">
          	<table class="table">
                <tr><td>보험,의료</td><td>없음</td></tr>
                <tr><td>근무 형태</td><td>매일 야근</td></tr>
                <tr><td>연차,휴가</td><td>1년에 1번</td></tr>
                <tr><td>식사,간식</td><td>니 돈 주고</td></tr>
                <tr><td>개인장비</td><td>할부12개월</td></tr>
                <tr>
                	<td>문의전화</td>
                	<td><b><%=crb.getCTelQuestion() %></b></td>
              	</tr>
              </table>
          </div>
        </article>

        <article class="job_li_elements">
        <h3>근무지</h3>
          <div class="comp_pannel">
          <%=crb.getCLocations() %>
          </div>
        </article>
    	</div><!-- <div class="col-md-8 col-md-pull-4" id="job_li_body"> -->
    </div><!-- <div class="row"> -->
  </article><!-- <article class="container" id="job_bwrap"> -->
</section>

<!--지원하기 모달-->
<div id="apply_modal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">지원하기</h4>
      </div>
      <div class="modal-body row">
        <form action="/Apply.co" class="form-horizontal col-md-12" method="post" enctype="multipart/form-data">
          <input name="recruit_id" value="<%=crb.getId()%>" type=hidden>
          <input name="member_id" value="<%=sessionId%>" type=hidden>
          <div class="form-group">
            <div class="col-sm-4">
              <label class="control-label">포트폴리오 주소</label>
            </div>
            <div class="col-sm-8">
              <input name="po_url" class="form-control" type="text" placeholder="포트폴리오 주소" value="http://">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <label class="control-label">포트폴리오 파일</label>
            </div>
            <div class="col-sm-8">
              <input name="po_url_file" class="form-control" type="file" placeholder="포트폴리오 파일" >
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <label class="control-label">희망연봉</label>
            </div>
            <div class="col-sm-8">
              <input name="money" class="form-control" type="text" placeholder="희망연봉" >
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <label class="control-label">휴대전화</label>
            </div>
            <div class="col-sm-8">
              <input name="tel" class="form-control" type="text" placeholder="휴대전화" >
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <label class="control-label">메세지</label>
            </div>
            <div class="col-sm-8">
              <input name="content" class="form-control" type="text" placeholder="메세지" >
            </div>
          </div>
          <div class="form-group">
            <label class="control-label"></label>
            <div class="col-sm-12">
              <input type="submit" value="지원하기" class="btn btn-sm btn-success">
              <input type="reset" value="새로작성" class="btn btn-sm btn-warning">
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- 채용수정하기 모달 -->
<div id="recruit_update" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">채용공고 수정</h4>
      </div>
      <div class="modal-body row">
        <!--수정 폼-->
        <form action="/RecruitUpdate.co" method="post" name="fr" id="joinForm">
          <input name="id" class="form-control" type="hidden" value="<%=crb.getId()%>">
          <input name="company_id" class="form-control" type="hidden" value="<%=crb.getCompanyId()%>">
          <div class="panel-body form-horizontal payment-form">
          <div class="form-group">
            <label class="col-sm-2 control-label">채용공고 타이틀</label>
            <div class="col-sm-10">
              <input name="c_title" class="form-control" type="text" value="<%=crb.getCTitle()%>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">채용 시작일</label>
            <div class="col-sm-4">
              <input name="c_job_date_start" class="form-control" type="date" value="<%=crb.getCJobDateStart()%>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">채용 마감일</label>
            <div class="col-sm-4">
              <input name="c_job_date_end" class="form-control" type="date" value="<%=crb.getCJobDateEnd()%>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">직무분야</label>
            <div class="col-sm-10">
              <select name="c_field" class="form-control">
                    <option>----</option>
                    <option value="디자인" <%if(crb.getCField().equals("디자인")){%>selected<%}%>>디자인</option>
                    <option value="웹" <%if(crb.getCField().equals("웹")){%>selected<%}%>>웹</option>
                    <option value="앱" <%if(crb.getCField().equals("앱")){%>selected<%}%>>앱</option>
              </select>
            </div>
          </div>
          <!-- null가능한 컬랩스 시작 -->
          <div class="panel panel-default">
          <div class="panel-heading" id="accordion">
                    추가사항 입력
          <div class="btn-group pull-center">
            <a type="button" class="btn btn-default btn-xs" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
              <span class="glyphicon glyphicon-chevron-down"></span>
            </a>
          </div><!-- <div class="btn-group pull-center"> -->
          </div><!-- <div class="panel-heading" id="accordion"> -->
          <!-- 추가사항 입력폼 시작 --><br>
          <div class="panel-collapse collapse" id="collapseOne">
          <div class="form-group">
            <label class="col-sm-2 control-label">경력</label>
            <div class="col-sm-10">
              <select name="c_career" class="form-control">
                <option>----</option>
                <option value="신입" <%if(crb.getCCareer().equals("신입")){%>selected<%}%>>신입</option>
                <option value="경력" <%if(crb.getCCareer().equals("경력")){%>selected<%}%>>경력</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">주요업무</label>
            <div class="col-sm-10">
              <input name="c_task" class="form-control" type="text" value="<%=crb.getCTask()%>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">근무지</label>
            <div class="col-sm-3">
              <select name="c_locations" class="form-control">
                <option>----</option>
                <option value="서울" <%if(crb.getCLocations().equals("서울")){%>selected<%}%>>서울</option>
                <option value="부산" <%if(crb.getCLocations().equals("부산")){%>selected<%}%>>부산</option>
                <option value="그외" <%if(crb.getCLocations().equals("그외")){%>selected<%}%>>그외</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">연봉</label>
            <div class="col-sm-3">
              <select name="c_salary" class="form-control">
                <option>----</option>
              <% for(int i=18; i<=50; i++){ %>
                <option value="<%=i*100+"만원"%>" <%if(crb.getCSalary().equals(i*100+"만원")){%>selected<%}%>><%=i %>00만원</option>
              <% } %>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">문의전화</label>
            <div class="col-sm-10">
              <input name="c_tel_question" class="form-control" type="text" value="<%=crb.getCTelQuestion()%>" required>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">채용상세정보</label>
            <div class="col-sm-10">
              <textarea name="c_job_content" cols="10" rows="5" maxlength="150" class="form-control" style="width: 500px;"><%=crb.getCJobContent()%></textarea>
            </div>
          </div>
          </div><!-- <div class="panel-collapse collapse" id="collapseOne"> -->
          <!-- 추가사항 입력폼 끝 -->
          </div><!-- <div class="panel panel-default"> -->
          <!-- null가능한 컬랩스 끝 -->
            <div class="form-group">
              <label for="concept" class="col-xs-2 control-label"></label>
              <div class="col-xs-10">
                <input type="submit" value="채용수정" class="btn btn-sm btn-success">
                <input type="reset" value="새로작성" class="btn btn-sm btn-warning">
              </div>
            </div>
          </div><!-- <div class="panel-body form-horizontal payment-form"> -->
        </form>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
<jsp:include page="/sub/footer.jsp"/><!-- 푸터  -->
