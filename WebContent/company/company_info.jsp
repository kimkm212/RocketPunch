<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,
                java.util.HashMap,
                net.company.dto.CompanyBean,
                net.member.db.dto.MemberBean,
                net.company.dto.CompanyRecruitBean"%>
<%@ include file="/sub/header.jsp" %><!-- 헤더  -->
<%
	CompanyBean cb = (CompanyBean)request.getAttribute("cb");
  List<HashMap<String, Object>> memberList = (List<HashMap<String, Object>>)request.getAttribute("memberList");
  List<CompanyRecruitBean> recruitList = (List<CompanyRecruitBean>)request.getAttribute("recruitList");
%>

		<section>
			<article id="c_info_head">
				<div class="cover" style="background:url('<%=cb.getCCover()%>') no-repeat center center;"></div>
				<div class="container">
					<div class="row">
						<div class="col-md-8 comp_pannel">
							<div src="<%=cb.getCLogo()%> "></div>
              <div class="logo" style="background:url('<%=cb.getCLogo()%>') no-repeat center center;"></div>
							<div class="comp_info">
								<h3><strong><%=cb.getCNamek() %></strong><span><%=cb.getCNamee() %></span></h3>
								<p><%=cb.getCIntro() %></p>
								<span class="label label-default">JAVA</span>
								<span class="label label-primary">JSP</span>
								<span class="label label-success">Mysql</span>
								<span class="label label-info">Oracle</span>
								<span class="label label-warning">JavaScript</span>
								<span class="label label-danger">Node.js</span>
							</div>
							<div class="comp_info2">
								<div>
									<i class="fa fa-building fa-2x" aria-hidden="true"></i><br>
									<span>설립:<%=1 %>년차</span>
								</div>

							</div>
						</div>
						<div class="col-md-4 btn_area">
              <div class="in">
                <%
                if(sessionC_Id !=null){       
                if(sessionC_Id==cb.getId()){%>
                <button data-toggle="modal" data-target="#update_comp" type="button" class="btn btn-info btn-block">회사정보 수정</button>
                <%}
                
                if(sessionC_Id==0){%>
                <button type="button" class="btn btn-warning btn-block" id="join_co" value="<%=cb.getId()%>">구성원으로 가입하기</button>
                <%}}%>
                <button type="button" class="btn btn-primary btn-block">진행중인 채용정보가 <%=recruitList.size()%>건 있습니다.</button>
              </div>
            </div>
					</div>
				</div>
			</article>

			<article class="container" id="c_info_body">
				<div class="row">
					<div class="col-md-8">

						<article class="comp_intro ">
							<h3>회사소개</h3>
							<div class="comp_pannel">
								<p>
									<%=cb.getCIntroduce()%>
								</p>
							</div>
						</article>

						<article class="comp_member">
							<h3>구성원</h3>
							<div class="row">
              <!-- 구성원 하나하나 -->
              <%if(memberList!=null){
                  for(HashMap<String, Object> e : memberList){
                    MemberBean mb = (MemberBean)e.get("mb");
              %>
               <div class="col-md-6 main_pannel">
                  <div class="container-fluid pannel_wrap">
                    <div class="prof_info">
                      <h4><strong><a href="MemberInfoAction.me?id=<%=mb.getId()%>"><%=mb.getFirstNameK()+mb.getLastNameK()%> </a></strong></h4>
                      <p><%=mb.getCRole()%></p>
                      <p><%=cb.getCNamek()%><%if(cb.getCNamee()!=""){ %><span><%="("+cb.getCNamee()+")"%></span><%}%></p>
                    </div>
                    <div class="pro_img" style="background:url('<%=mb.getImageProfile()%>') no-repeat center center;"></div>
                    <%
                    if(sessionId!=null){
                      Integer from_to = (Integer)e.get("from_to");
                      Integer to_from = (Integer)e.get("to_from");%>
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
                </div>
              <%}}%>
							</div>
						</article>

						<article class="job_li">
							<h3>채용 정보</h3>
	            <div class="row">

              <%if(recruitList!=null){
                for(CompanyRecruitBean e : recruitList){
               %>
								<div class="col-md-12 ">
									<div class="rec_wrap">
										<div class="info nowrap">
    									<div class="rec_img" style="background:url('<%=cb.getCLogo()%>') no-repeat center center;"></div>
                      <h4> <a href="/RecruitContent.co?id=<%=e.getId()%>"><strong><%=e.getCTitle()%></strong><span><%=cb.getCNamek()%></span></a></h4>
                      <p><%=cb.getCIntro()%></p>
                    </div>
                    <span><%=e.getCJobDateEnd()%>까지</span>
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
                      <span><%=e.getCCareer()%></span>
                    </div>
									</div>
								</div>
              <%}}%>
	            </div><!--row-->
	          </article><!--job_list-->


					</div>
					<div class="col-md-4" id="c_info_aside">
						<article class="banner">
							<img src="/imgs/comp_banner.jpg" alt="" />
						</article>
						<article class="comp_aside_info" onload="initialize()">
							<table class="table">
								<tr><td>설립일</td><td><%=cb.getCDate()%></td></tr>
								<tr><td>홈페이지</td><td><%=cb.getCHomepage() %></td></tr>
								<tr><td>E-mail</td><td><%=cb.getCEmail() %></td></tr>
								<tr><td>전화번호</td><td><%=cb.getCTel() %></td></tr>
								<tr><td>사무실</td><td><%=cb.getCAddress() %></td></tr>
							</table>
							<div id="map_canvas"style="width:100%; height:300px;"></div>
						</article>
						<article class="comp_history">
							<h4>연혁</h4>
							<table class="table">
								<tr><td>2016-03-22</td><td>설립</td></tr>
								<tr><td>2016-03-22</td><td>설립</td></tr>
								<tr><td>2016-03-22</td><td>설립</td></tr>
							</table>
						</article>
					</div>
				</div>
			</article>
		</section>

    <!--수정 모달-->
    <div id="update_comp" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">기업정보 수정</h4>
          </div>
          <div class="modal-body row">

            <form action="/CompanyUpdate.co" class="form-horizontal col-md-12" method="post" enctype="multipart/form-data">
              <input name="id" type="hidden" value="<%=cb.getId()%>" >
              <div class="form-group">
                <label class="col-sm-2 control-label">기업명</label>
                <div class="col-sm-10">
                  <input name="c_nameK" class="form-control" type="text" value="<%=cb.getCNamek()%>" >
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">기업명 영문</label>
                <div class="col-sm-10">
                  <input name="c_nameE" class="form-control" type="text" value="<%=cb.getCNamee()%>" >
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">기업 공식 E-mail</label>
                <div class="col-sm-10">
                  <input name="c_email" class="form-control" type="email" value="<%=cb.getCEmail()%>">
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
                    </div>
                </div>
                  <!-- 추가사항 입력폼 시작 -->
              <div class="panel-collapse collapse" id="collapseOne">
                <div class="form-group">
                  <label class="col-sm-2 control-label">분야</label>
                  <div class="col-sm-3">
                    <select name="c_part" class="form-control">
                      <option>----</option>
                      <option value="웹" <%if(cb.getCPart().equals("웹")){%>selected<%}%>>웹</option>
                      <option value="앱" <%if(cb.getCPart().equals("앱")){%>selected<%}%>>앱</option>
                      <option value="서버" <%if(cb.getCPart().equals("서버")){%>selected<%}%>>서버</option>
                      <option value="디자인" <%if(cb.getCPart().equals("디자인")){%>selected<%}%>>디자인</option>
                    </select>
                  </div>
                </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">내 역할</label>
                    <div class="col-sm-10">
                      <input name="c_role" class="form-control" type="text" value="<%=cb.getCRole()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">구성원수</label>
                    <div class="col-sm-2">
                      <input name="c_members" class="form-control" type="number" value="<%=cb.getCMembers()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">회사주소</label>
                    <div class="col-sm-10">
                      <input name="c_address" class="form-control" type="text" value="<%=cb.getCAddress()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">회사홈페이지</label>
                    <div class="col-sm-10">
                      <input name="c_homepage" class="form-control" type="text" value="<%=cb.getCHomepage()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">전화번호</label>
                    <div class="col-sm-10">
                      <input name="c_tel" class="form-control" type="text" value="<%=cb.getCTel()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">회사소개(간단히)</label>
                    <div class="col-sm-10">
                      <input name="c_intro" class="form-control" type="text" value="<%=cb.getCIntro()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">회사소개(상세히)</label>
                    <div class="col-sm-10">
                      <textarea name="c_introduce" cols="10" rows="5" maxlength="2000" class="form-control" style="width: 500px;"><%=cb.getCIntroduce()%></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">설립일</label>
                    <div class="col-sm-4">
                      <input name="c_birthday" class="form-control" type="date" value="<%=cb.getCBirthday()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">회사로고</label>
                    <div class="col-sm-10">
                      <input name="c_logo" class="form-control" type="file" >
                      <input name="h_c_logo" class="form-control" type="hidden" value="<%=cb.getCLogo()%>" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">회사사진</label>
                    <div class="col-sm-10">
                      <input name="c_cover" class="form-control" type="file"  >
                      <input name="h_c_cover" class="form-control" type="hidden" value="<%=cb.getCCover()%>" >
                    </div>
                  </div>
                    </div>
                <!-- 추가사항 입력폼 시작 -->
            </div>
              <!-- null가능한 컬랩스 끝 -->

              <div class="form-group">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                  <input type="submit" value="기업정보 수정" class="btn btn-sm btn-success">
                  <input type="reset" value="새로작성" class="btn btn-sm btn-warning">
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

<jsp:include page="/sub/footer.jsp"/><!-- 푸터  -->
