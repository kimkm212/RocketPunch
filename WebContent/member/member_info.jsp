<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="net.member.db.dto.*,
            net.company.dto.CompanyBean,
            java.util.List,
            java.util.HashMap"
%>
<%
//sessionId 변수에 세션 id값 담겨 있음 int형

  HashMap<String, Object> result = (HashMap<String, Object>)request.getAttribute("result");
  List<MemberProject> mp = (List<MemberProject>)request.getAttribute("mp");
  List<MemberCareer> mc = (List<MemberCareer>)request.getAttribute("mc");
  List<MemberAward> ma = (List<MemberAward>)request.getAttribute("ma");
  List<MemberLicense> ml = (List<MemberLicense>)request.getAttribute("ml");
  List<MemberSchool> ms = (List<MemberSchool>)request.getAttribute("ms");

  MemberBean mb = (MemberBean) result.get("mb");
  CompanyBean cb = (CompanyBean) result.get("cb");

  //sessionId와 thisId를 비교해 같을경우 삽입 수정 삭제 버튼을 넣음
  //즉 로그인된 사용자가 자기 자신의 memberInfo에 접근하면 수정 삽입 삭제가 가능하도록 함

  int thisId = mb.getId();

%>
		<%@ include file="/sub/header.jsp"%>
    <%
      // 세션아이디가 존재(로그인 상태)일 경우 로그인 사용자와의 친구관계를 시별 해야 한다.
      Integer to_from = -1;
      Integer from_to = -1;
      if(sessionId != null){
    	  to_from = (Integer) result.get("to_from");
    	  from_to = (Integer) result.get("from_to");
      }
      //세션이 없을 경우 비교 불가 따라서 null 값이면 비교할 수 있도록 dummyValue를 설정
      if(sessionId == null){
        sessionId=0;
      }


    %>
		<!-- 헤더 드가는 부분 -->
		<section>
			<article id="m_info_head">
				<div class="cover" style="background:url('<%=mb.getImageCover()%>') no-repeat center center;">
					<div class="container">
						<div class="pro_img" style="background:url('<%=mb.getImageProfile()%>') no-repeat center center;"></div>
						<div class="name_tag">
							<p><Strong><%=mb.getCRole() %></Strong></p>
							<h2><%=mb.getFirstNameK()+mb.getLastNameK()%></h2>
						</div>
					</div>
				</div>
				<div class="container info">
					<div class="row">
            <div class="col-md-3 btn_area">
            <%if(sessionId==thisId){%>
              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#m_update">기본프로필 수정하기</button>
            <%}
            if(sessionId!=0){%>
              <div class="btn_friends" id="<%=mb.getId()%>">
                <%if(from_to==-1 && to_from==-1 && mb.getId()!=sessionId){%>
                <button class="btn btn-warning btn_xs addFriend">친구추가</button>
                <%}%>
                <%if(from_to==0 && mb.getId()!=sessionId){%>
                <button class="btn btn-primary btn_xs cancelFriend">친구신청 철회</button>
                <%}%>
                <%if(to_from==0 && mb.getId()!=sessionId){%>
                <button class="btn btn-info btn_xs acceptFriend">친구 수락</button>
                <button class="btn btn-info btn_xs rejectFriend">거절</button>
                <%}%>
                <%if((to_from==2 || from_to==2) && mb.getId()!=sessionId){%>
                <button class="btn btn-default btn_xs">친구</button>
                <%}%>
              </div>
            <%}%>
            </div>
						<div class="col-md-9 head_info">
							<div class="row">
								<div class="col-md-12 in_name_tag">
									<p><Strong><%=mb.getCRole() %></Strong></p>
					     		<h2><%=mb.getFirstNameK()+mb.getLastNameK()%></h2>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<p><%=mb.getSelfIntro()%></p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<span class="field"><i class="fa fa-building-o" aria-hidden="true"></i>경력1</span>
									<span class="field"><i class="fa fa-graduation-cap" aria-hidden="true"></i>대학교학교</span>
									<span class="field"><i class="fa fa-tags" aria-hidden="true"></i>기술</span>
									<span class="field"><i class="fa fa-briefcase" aria-hidden="true"></i>프로젝트</span>
									<span class="field"><i class="fa fa-users" aria-hidden="true"></i>친구</span>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<p><span>내친구 2</span><span>내 프로필 방문자 31</span></p>
									<div class="btns">
										<button type="button" class="btn btn-primary">추천서 부탁하기</button>
										<button type="button" class="btn btn-primary">공유</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</article>

			<article class="container" id="m_info_body">

				<article class="add_pro">
					<h3>프로필 추가하기</h3>
					<div class="row">
						<div class="col-xs-6 col-sm-2">
							<div class="pro_pannel">
								<p>프로젝트</p>
								<p>할말</p>
							</div>
						</div>
						<div class="col-xs-6 col-sm-2">
							<div class="pro_pannel">
								<p>경력</p>
								<p>할말</p>
							</div>
						</div>
						<div class="col-xs-6 col-sm-2">
							<div class="pro_pannel">
								<p>수상</p>
								<p>할말</p>
							</div>
						</div>
						<div class="col-xs-6 col-sm-2">
							<div class="pro_pannel">
								<p>학교</p>
								<p>할말</p>
							</div>
						</div>
						<div class="col-xs-6 col-sm-2">
							<div class="pro_pannel">
								<p>자격증</p>
								<p>할말</p>
							</div>
						</div>
						<div class="col-xs-6 col-sm-2">
							<div class="pro_pannel">
								<p>개인정보</p>
								<p>할말</p>
							</div>
						</div>
					</div>
				</article>

				<article class="project">
					<h3>프로젝트</h3>
          <%if(sessionId==thisId){%>
          <button data-toggle="collapse" data-target="#co_project" class="btn btn-xs add_btn">추가</button>
          <%}%>
					<div id="co_project" class="collapse row">
						<!--추가버튼 누르면 나오는 이력폼-->
						<form class="form-horizontal col-md-8 col-md-offset-2" action="./ProjectInsertAction.me" method="post">
              <input type="hidden" name="member_id" value="<%=sessionId%>">
							<div class="form-group form-inline">
								<label class="col-sm-2 control-label">프로젝트명</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="프로젝트" name="project_name">
									<input class="form-control" type="text" placeholder="Ownership" name="ownership">
								</div>
							</div>
							<div class="form-group form-inline">
								<label class="col-sm-2 control-label">참여 기간</label>
								<div class="col-sm-10">
									<input class="form-control" type="date" placeholder="시작일" name="pro_in_year"> -
									<input class="form-control" type="date" placeholder="종료일" name="pro_out_year">
									<label><input type="checkbox" name="ongoing" value="1">진행중</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">결과물</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="프로젝트 웹 URL" name="result_web">
									<input class="form-control" type="text" placeholder="애플 앱스토어 URL" name="result_apple">
									<input class="form-control" type="text" placeholder="구글 플레이 스토어 URL" name="result_goggle">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">팀</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="팀" name="team">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">간단설명</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"></textarea>
								</div>
							</div>
							<button type="submit" name="button" class="btn btn-primary">저장</button>
					 </form>

					</div>
          <%
          for(MemberProject e : mp){
           %>
          <div class="info_pannel">
            <img src="./imgs/1.jpg" alt="" />
            <%
              if(sessionId==thisId){
                %>
                <div class="ud">
                  <button data-toggle="collapse" data-target="#project_<%=e.getId()%>" class="btn btn-xs btn-info">수정</button>
                  <a href="/ProjectDeleteAction.me?id=<%=e.getId()%>&member_id=<%=e.getMemberId()%>" class="btn btn-xs btn-warning" role="button">삭제</a>
                </div>
            <%}%>
            <div class="info nowrap">
              <h4><strong><%=e.getProjectName()%></strong><%=e.getOwnership()%></h4>
              <p><%=e.getProInYear()%>~<%=e.getProOutYear()%></p>
              <p><%=e.getResultWeb() %></p>
              <p><%=e.getResultApple() %></p>
              <p><%=e.getResultGoggle() %></p>
              <p><%=e.getAbout() %></p>
            </div>

            <!--프로젝트 수정-->
            <%if(sessionId==thisId){%>
            <div id="project_<%=e.getId()%>" class="collapse row">
  						<form class="form-horizontal col-md-8 col-md-offset-2" action="/ProjectUpdateAction.me" method="post">
                <input type="hidden" name="member_id" value="<%=sessionId%>">
                <input type="hidden" name="id" value="<%=e.getId()%>">
  							<div class="form-group form-inline">
  								<label class="col-sm-2 control-label">프로젝트명</label>
  								<div class="col-sm-10">
  									<input class="form-control" type="text" placeholder="프로젝트" value="<%=e.getProjectName()%>" name="project_name">
  									<input class="form-control" type="text" placeholder="Ownership" value="<%=e.getOwnership()%>" name="ownership">
  								</div>
  							</div>
  							<div class="form-group form-inline">
  								<label class="col-sm-2 control-label">참여 기간</label>
  								<div class="col-sm-10">
  									<input class="form-control" type="date" placeholder="시작일" value="<%=e.getProInYear()%>" name="pro_in_year"> -
  									<input class="form-control" type="date" placeholder="종료일" value="<%=e.getProOutYear()%>" name="pro_out_year">
  									<label><input type="checkbox" name="ongoing" value="1" <%if(e.getOngoing()==1){%>checked<%}%>>진행중</label>
  								</div>
  							</div>
  							<div class="form-group">
  								<label class="col-sm-2 control-label">결과물</label>
  								<div class="col-sm-10">
  									<input class="form-control" type="text" placeholder="프로젝트 웹 URL" value="<%=e.getResultWeb()%>" name="result_web">
  									<input class="form-control" type="text" placeholder="애플 앱스토어 URL" value="<%=e.getResultApple()%>" name="result_apple">
  									<input class="form-control" type="text" placeholder="구글 플레이 스토어 URL" value="<%=e.getResultGoggle()%>" name="result_goggle">
  								</div>
  							</div>
  							<div class="form-group">
  								<label class="col-sm-2 control-label">팀</label>
  								<div class="col-sm-10">
  									<input class="form-control" type="text" placeholder="팀" value="<%=e.getTeam()%>" name="team">
  								</div>
  							</div>
  							<div class="form-group">
  								<label class="col-sm-2 control-label">간단설명</label>
  								<div class="col-sm-10">
  									<textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"><%=e.getAbout()%></textarea>
  								</div>
  							</div>
  							<button type="submit" name="button" class="btn btn-primary">수정</button>
  					 </form>
  					</div>
            <%}%>
          </div>

         <%}%>
				</article>

				<article class="career">
					<h3>경력</h3>
          <%if(sessionId==thisId){%>
        	<button data-toggle="collapse" data-target="#co_career" class="btn btn-xs add_btn">추가</button>
          <%}%>
					<div id="co_career" class="collapse row">
						<form class="form-horizontal col-md-8 col-md-offset-2" action="./CareerInsertAction.me" method="post">
              <input type="hidden" name="member_id" value="<%=sessionId%>">
							<div class="form-group">
								<label class="col-sm-2 control-label">기업명</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="기업명" name="company">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">역할</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="역할" name="role">
								</div>
							</div>
							<div class="form-group form-inline">
								<label class="col-sm-2 control-label">재직 기간</label>
								<div class="col-sm-10">
									<input class="form-control" type="date" placeholder="시작일" name="com_in_date"> -
									<input class="form-control" type="date" placeholder="종료일" name="com_out_date">
									<label><input type="checkbox" name="in_company" value="1">재직중</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">간단설명</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"></textarea>
								</div>
							</div>
							<button type="submit" name="button" class="btn btn-primary">저장</button>
						</form>
					</div>
          <%
          for(MemberCareer e : mc){
          %>
          <div class="info_pannel">
            <img src="./imgs/1.jpg" alt="" />
            <%
              if(sessionId==thisId){
                %>
                <div class="ud">
                  <button data-toggle="collapse" data-target="#career_<%=e.getId()%>" class="btn btn-xs btn-info" role="button">수정</button>
                  <a href="/CareerDeleteAction.me?id=<%=e.getId()%>&member_id=<%=e.getMemberId()%>" class="btn btn-xs btn-warning" role="button">삭제</a>
                </div>
            <%}%>
            <div class="info nowrap">
              <h4><strong><%=e.getCompany() %></strong></h4>
              <p><%=e.getRole() %></p>
              <p><%=e.getComInDate()%>~<%=e.getComOutDate()%></p>
              <p><%=e.getAbout()%></p>
            </div>

            <!--경력 수정 폼-->
          <%if(sessionId==thisId){%>
            <div id="career_<%=e.getId()%>" class="collapse row">
              <form class="form-horizontal col-md-8 col-md-offset-2" action="/CareerUpdateAction.me" method="post">
                <input type="hidden" name="member_id" value="<%=sessionId%>">
                <input type="hidden" name="id" value="<%=e.getId()%>">
                <div class="form-group">
                  <label class="col-sm-2 control-label">기업명</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="기업명" value="<%=e.getCompany()%>" name="company">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">역할</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="역할" value="<%=e.getRole()%>" name="role">
                  </div>
                </div>
                <div class="form-group form-inline">
                  <label class="col-sm-2 control-label">재직 기간</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="date" placeholder="시작일" value="<%=e.getComInDate()%>" name="com_in_date"> -
                    <input class="form-control" type="date" placeholder="종료일" value="<%=e.getComOutDate()%>" name="com_out_date">
                    <label><input type="checkbox" name="in_company" value="1" <%if(e.getInCompany()==1){%>checked<%}%>>재직중</label>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">간단설명</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"><%=e.getAbout()%></textarea>
                  </div>
                </div>
                <button type="submit" name="button" class="btn btn-primary">수정</button>
              </form>
            </div>
          <%}%>
          </div>
          <%}%>
				</article>

				<article class="award">
					<h3>수상</h3>
          <%if(sessionId==thisId){%>
        	<button data-toggle="collapse" data-target="#co_award" class="btn btn-xs add_btn">추가</button>
					<div id="co_award" class="collapse row">
						<form class="form-horizontal col-md-8 col-md-offset-2" action="./AwardInsertAction.me" method="post">
            <input type="hidden" name="member_id" value="<%=sessionId%>">
							<div class="form-group form-inline">
								<label class="col-sm-2 control-label">수상부문</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="수상부문(필수)" name="award_type">
									<input class="form-control" type="text" placeholder="상" name="award_name">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">수상기관</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="수상기관" name="award_agency">
								</div>
							</div>
							<div class="form-group form-inline">
								<label class="col-sm-2 control-label">수상년도</label>
								<div class="col-sm-10">
									<input class="form-control" type="date"name="award_date">
								</div>
							</div>
							<button type="submit" name="button" class="btn btn-primary">저장</button>
						</form>
					</div>
          <%}
          for(MemberAward e : ma){
          %>
          <div class="info_pannel">
            <div class="info nowrap">
              <%
                if(sessionId==thisId){
                  %>
                  <div class="ud">
                    <button data-toggle="collapse" data-target="#award_<%=e.getId()%>" class="btn btn-xs btn-info" role="button">수정</button>
                    <a href="/AwardDeleteAction.me?id=<%=e.getId()%>&member_id=<%=e.getMemberId()%>" class="btn btn-xs btn-warning" role="button">삭제</a>
                  </div>
                <%}%>
              <h4><strong><%=e.getAwardName()%></strong><%=e.getAwardType()%></h4>
              <p><%=e.getAwardDate()%></p>
              <p><%=e.getAwardAgency()%></p>
            </div>

            <!-- 수상 수정폼 -->
            <%if(sessionId==thisId){%>
            <div id="award_<%=e.getId()%>" class="collapse row">
              <form class="form-horizontal col-md-8 col-md-offset-2" action="/AwardUpdateAction.me" method="post">
                <input type="hidden" name="member_id" value="<%=sessionId%>">
                <input type="hidden" name="id" value="<%=e.getId()%>">
                <div class="form-group form-inline">
                  <label class="col-sm-2 control-label">수상부문</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="수상부문(필수)" value="<%=e.getAwardType()%>" name="award_type">
                    <input class="form-control" type="text" placeholder="상" value="<%=e.getAwardName()%>" name="award_name">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">수상기관</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="수상기관" value="<%=e.getAwardAgency()%>" name="award_agency">
                  </div>
                </div>
                <div class="form-group form-inline">
                  <label class="col-sm-2 control-label">수상년도</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="date" value="<%=e.getAwardDate()%>" name="award_date">
                  </div>
                </div>
                <button type="submit" name="button" class="btn btn-primary">수정</button>
              </form>
            </div>
            <%}%>
          </div>
          <%}%>
				</article>

				<article class="school">
					<h3>학교</h3>
          <%if(sessionId==thisId){%>
        	<button data-toggle="collapse" data-target="#co_school" class="btn btn-xs add_btn">추가</button>
					<div id="co_school" class="collapse row">
						<form class="form-horizontal col-md-8 col-md-offset-2" action="/SchoolInsertAction.me" method="post">
              <input type="hidden" name="member_id" value="<%=sessionId%>">
							<div class="form-group">
								<label class="col-sm-2 control-label">학교</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="학교" name="school">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">전공</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="전공" name="major">
								</div>
							</div>
							<div class="form-group form-inline">
								<label class="col-sm-2 control-label">재학 기간</label>
								<div class="col-sm-10">
									<input class="form-control" type="date" name="sch_in_year">
									<input class="form-control" type="date" name="sch_out_year">
									<select class="form-control" name="finish">
										<option value="졸업">졸업</option>
										<option value="재학">재학</option>
										<option value="휴학">휴학</option>
										<option value="중퇴">중퇴</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">간단설명</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"></textarea>
								</div>
							</div>
							<button type="submit" name="button" class="btn btn-primary">저장</button>
						</form>
					</div>
          <%}
          for(MemberSchool e : ms){
          %>
          <div class="info_pannel">
            <img src="./imgs/1.jpg" alt="" />
            <%
              if(sessionId==thisId){
             %>
             <div class="ud">
               <button data-toggle="collapse" data-target="#school_<%=e.getId()%>" class="btn btn-xs btn-info" role="button">수정</button>
               <a href="/SchoolDeleteAction.me?id=<%=e.getId()%>&member_id=<%=e.getMemberId()%>" class="btn btn-xs btn-warning" role="button">삭제</a>
             </div>
            <%}%>
            <div class="info nowrap">
              <h4><strong><%=e.getSchool()%></strong></h4>
              <p><%=e.getSchInYear()%>~<%=e.getSchOutYear()%><span><%=e.getFinish()%></span></p>
              <p><%=e.getMajor()%></p>
              <p><%=e.getAbout()%></p>
            </div>

            <!-- 학교 수정 -->
            <%if(sessionId==thisId){%>
            <div id="school_<%=e.getId()%>" class="collapse row">
              <form class="form-horizontal col-md-8 col-md-offset-2" action="/SchoolUpdateAction.me" method="post">
                <input type="hidden" name="member_id" value="<%=sessionId%>">
                <input type="hidden" name="id" value="<%=e.getId()%>">
                <div class="form-group">
                  <label class="col-sm-2 control-label">학교</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="학교" value="<%=e.getSchool()%>" name="school">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">전공</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="전공" value="<%=e.getMajor()%>" name="major">
                  </div>
                </div>
                <div class="form-group form-inline">
                  <label class="col-sm-2 control-label">재학 기간</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="date" value="<%=e.getSchInYear()%>" name="sch_in_year">
                    <input class="form-control" type="date" value="<%=e.getSchOutYear()%>" name="sch_out_year">
                    <select class="form-control" name="finish">
                      <option value="졸업" <%if(e.getFinish().equals("졸업")){%>selected<%}%>>졸업</option>
                      <option value="재학" <%if(e.getFinish().equals("재학")){%>selected<%}%>>재학</option>
                      <option value="휴학" <%if(e.getFinish().equals("휴학")){%>selected<%}%>>휴학</option>
                      <option value="중퇴" <%if(e.getFinish().equals("중퇴")){%>selected<%}%>>중퇴</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">간단설명</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"><%=e.getAbout()%></textarea>
                  </div>
                </div>
                <button type="submit" name="button" class="btn btn-primary">수정</button>
              </form>
            </div>
            <%}%>
          </div>
          <%}%>
				</article>

				<article class="license">
					<h3>자격증</h3>
          <%if(sessionId==thisId){%>
        	<button data-toggle="collapse" data-target="#co_license" class="btn btn-xs add_btn">추가</button>
					<div id="co_license" class="collapse row">
						<form class="form-horizontal col-md-8 col-md-offset-2" action="/LicenseInsertAction.me" method="post">
            <input type="hidden" name="member_id" value="<%=sessionId%>">
							<div class="form-group">
								<label class="col-sm-2 control-label">자격증 명</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="자격증 명" name="license_name">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">발급기관</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" placeholder="발급기관" name="license_agency">
								</div>
							</div>
							<div class="form-group form-inline">
								<label class="col-sm-2 control-label">취득년도</label>
								<div class="col-sm-10">
									<input class="form-control" type="date" name="lic_get_date">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">간단설명</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"></textarea>
								</div>
							</div>
							<button type="submit" name="button" class="btn btn-primary">저장</button>
						</form>
					</div>
          <%}
          for(MemberLicense e : ml){
          %>
          <div class="info_pannel">
            <div class="info nowrap">
              <%
                if(sessionId==thisId){
                %>
                <div class="ud">
                  <button data-toggle="collapse" data-target="#license_<%=e.getId()%>" class="btn btn-xs btn-info" role="button">수정</button>
                  <a href="/LicenseDeleteAction.me?id=<%=e.getId()%>&member_id=<%=e.getMemberId()%>" class="btn btn-xs btn-warning" role="button">삭제</a>
                </div>
              <%}%>
              <h4><strong><%=e.getLicenseName()%></strong></h4>
              <p><%=e.getLicenseAgency()%></p>
              <p><%=e.getLicGetDate()%></p>
              <p><%=e.getAbout()%></p>
            </div>

            <!-- 자격증 수정 -->
            <%if(sessionId==thisId){%>
            <div id="license_<%=e.getId()%>" class="collapse row">
              <form class="form-horizontal col-md-8 col-md-offset-2" action="/LicenseUpdateAction.me" method="post">
                <input type="hidden" name="member_id" value="<%=sessionId%>">
                <input type="hidden" name="id" value="<%=e.getId()%>">
                <div class="form-group">
                  <label class="col-sm-2 control-label">자격증 명</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="자격증 명" value="<%=e.getLicenseName()%>" name="license_name">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">발급기관</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="text" placeholder="발급기관" value="<%=e.getLicenseAgency()%>" name="license_agency">
                  </div>
                </div>
                <div class="form-group form-inline">
                  <label class="col-sm-2 control-label">취득년도</label>
                  <div class="col-sm-10">
                    <input class="form-control" type="date" value="<%=e.getLicGetDate()%>" name="lic_get_date">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">간단설명</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" rows="4" cols="40" placeholder="간단설명" name="about"><%=e.getAbout()%></textarea>
                  </div>
                </div>
                <button type="submit" name="button" class="btn btn-primary">수정</button>
              </form>
            </div>
            <%}%>
          </div>
          <%
          }
          %>
				</article>

				<article class="stacks">
					<h3>전문분야</h3>
          <%if(sessionId==thisId){%>
        	<button data-toggle="collapse" data-target="#co_stacks" class="btn btn-xs add_btn">추가</button>
          <%}%>
					<div id="co_stacks" class="collapse">
						Lorem ipsum dolor text....
					</div>
				</article>

				<article class="friends">
					<h3>친구들</h3>
				</article>

			</article>

		</section>


    <%if(sessionId==thisId){%>
    <!--기본프로필 수정 모달-->
    <div id="m_update" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">기본정보 수정</h4>
          </div>
          <div class="modal-body row">
            <!--수정 폼-->
            <form class="form-horizontal col-md-9 col-md-offset-1" action="/MemberUpdateAction.me" method="post" enctype="multipart/form-data">
              <input type="hidden" name="id" value="<%=mb.getId()%>">
              <div class="form-group form-inline">
                <label class="col-sm-3 control-label">이름</label>
                <div class="col-sm-9">
                  <input class="form-control" type="text" value="<%=mb.getFirstNameK()%>" name="first_name_k">
                  <input class="form-control" type="text" value="<%=mb.getLastNameK()%>" name="last_name_k">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label">패스워드</label>
                <div class="col-sm-9">
                  <input class="form-control" type="password" value="<%=mb.getPwd()%>" name="pwd">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label">자기소개</label>
                <div class="col-sm-9">
                  <textarea class="form-control" rows="4" cols="40" placeholder="자기소개" name="self_intro"><%=mb.getSelfIntro()%></textarea>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label">구직 여부</label>
                <div class="col-sm-9">
                  <input class="form-control" type="checkbox" value="1" name="look_for_a_job" <%if(mb.getLookForAJob()==1){%>checked<%}%>>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label">전문 분야</label>
                <div class="col-sm-9">
                  <input class="form-control" type="text" value="<%=mb.getExperise()%>" name="experise">
                </div>
              </div>

               <div class="form-group">
                <label class="col-sm-3 control-label">직위</label>
                <div class="col-sm-9">
                  <input class="form-control" type="text" value="<%=mb.getCRole()%>" name="c_role">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label">프로필 이미지</label>
                <div class="col-sm-9">
                  <input class="form-control" type="file" name="image_profile">
                   <input class="form-control" type="hidden" value="<%=mb.getImageProfile()%>" name="h_image_profile">
                </div>
              </div>
               <div class="form-group">
                <label class="col-sm-3 control-label">커버 이미지</label>
                <div class="col-sm-9">
                  <input class="form-control" type="file" name="image_cover">
                  <input class="form-control" type="hidden" value="<%=mb.getImageCover()%>" name="h_image_cover">
                </div>
              </div>

              <button type="submit" name="button" class="btn btn-primary">수정</button>
            </form>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>

      </div>
    </div>
    <%}%>


		<!-- 푸터 드가는 부분 -->
		<%@ include file="/sub/footer.jsp" %>
		<!-- 푸터 드가는 부분 -->
