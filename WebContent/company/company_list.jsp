<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.company.dto.CompanyBean"%>
<%@page import="java.util.List"%>
<%
List<CompanyBean> companyList=(List<CompanyBean>)request.getAttribute("companyList");
%>

<jsp:include page="/sub/header.jsp"/><!-- 헤더  -->
    <!-- 해더 삽입 부분-->
    <section class="container">
      <div class="row" id="company">
        <!--회사 리스트 영역-->
        <div class="col-md-8" id="comp_list">
          <div class="comp_sort">
            <span>최신순</span>
            <span>인기순</span>
          </div>

          <%
          if(companyList != null){
            for(CompanyBean e : companyList){
          %>
          <article class="row">
         	 <a href="./CompanyInfo.co?id=<%=e.getId()%>">
            <div class="col-md-12 comp_wrap">
              <div class="comp_cover" style="background:url('<%=e.getCCover()%>') no-repeat center center;"></div>
              <div class="comp_img" style="background:url('<%=e.getCLogo()%>') no-repeat center center;"></div>
              <h4><strong><%=e.getCNamek() %></strong></h4>
              <p class="rec_">채용중</p>
              <p class="nowrap"><%=e.getCIntro()%></p>
              <span class="label label-default">JAVA</span>
              <span class="label label-primary">JSP</span>
              <span class="label label-success">Mysql</span>
              <span class="label label-info">Oracle</span>
              <span class="label label-warning">JavaScript</span>
              <span class="label label-danger">Node.js</span>
            </div>
          	</a>
          </article>
	        <%}}%>

        </div>
        <!-- 옆구리 -->
        <aside class="col-md-4" id="comp_aside">
          <div class="comp_regi_area">
            <button data-toggle="modal" data-target="#singup_comp" type="button" class="btn btn-primary btn-block">스타트업 등록</button>
          </div>

          <div class="comp_banner">
            <img src="/imgs/comp_banner.jpg" alt="" />
          </div>

          <div class="memb_regi_area">
            <p>로켓펀치에 가입하시면 회사 연락처를 볼 수 있고, 중요한 업데이트 알림을 받아볼 수 있습니다.</p>
            <button type="button" class="btn btn-info btn-block">무료가입</button>
          </div>
        </aside>
      </div>

    </section>
    <!--스타트업 등록 모달 시작 -->
    <div id="singup_comp" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">스타트업 등록</h4>
          </div>
          <div class="modal-body row">
            <form action="/companyJoin.co" class="form-horizontal col-md-12" method="post" enctype="multipart/form-data">
              <div class="form-group">
                <label class="col-sm-2 control-label">기업명</label>
                <div class="col-sm-10">
                  <input name="c_nameK" class="form-control" type="text" placeholder="기업명을 한글로 기재해 주세요." >
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">기업명 영문</label>
                <div class="col-sm-10">
                  <input name="c_nameE" class="form-control" type="text" placeholder="기업명을 영문으로 기재해주세요" >
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">기업 공식 E-mail</label>
                <div class="col-sm-10">
                  <input name="c_email" class="form-control" type="email" >
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
                      <option value="웹">웹</option>
                      <option value="앱">앱</option>
                      <option value="서버">서버</option>
                      <option value="디자인">디자인</option>
                    </select>
                  </div>
                </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">내 역할</label>
		                <div class="col-sm-10">
		                  <input name="c_role" class="form-control" type="text" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">구성원수</label>
		                <div class="col-sm-2">
		                  <input name="c_members" class="form-control" type="number" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">회사주소</label>
		                <div class="col-sm-10">
		                  <input name="c_address" class="form-control" type="text" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">회사홈페이지</label>
		                <div class="col-sm-10">
		                  <input name="c_homepage" class="form-control" type="text" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">전화번호</label>
		                <div class="col-sm-10">
		                  <input name="c_tel" class="form-control" type="text" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">회사소개(간단히)</label>
		                <div class="col-sm-10">
		                  <input name="c_intro" class="form-control" type="text" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">회사소개(상세히)</label>
		                <div class="col-sm-10">
                 			<textarea name="c_introduce" cols="10" rows="5" maxlength="150" class="form-control" style="width: 500px;" placeholder="150자까지 가능합니다."></textarea>
               			</div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">설립일</label>
		                <div class="col-sm-4">
		                  <input name="c_birthday" class="form-control" type="date" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">회사로고</label>
		                <div class="col-sm-10">
		                  <input name="c_logo" class="form-control" type="file" placeholder="" >
		                </div>
		              </div>
		              <div class="form-group">
		                <label class="col-sm-2 control-label">회사사진</label>
		                <div class="col-sm-10">
		                  <input name="c_cover" class="form-control" type="file" placeholder="" >
		                </div>
		              </div>
                    </div>
		            <!-- 추가사항 입력폼 시작 -->
	          </div>
              <!-- null가능한 컬랩스 끝 -->

              <div class="form-group">
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                  <input type="submit" value="스타트업 등록" class="btn btn-sm btn-success">
                  <input type="reset" value="새로작성" class="btn btn-sm btn-warning">
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!--스타트업 등록 모달 끝 -->
<jsp:include page="/sub/footer.jsp"/><!-- 푸터  -->
