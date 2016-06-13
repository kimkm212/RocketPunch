<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="net.company.dto.*,
            net.company.dto.CompanyBean,
            java.util.List,
            java.util.HashMap"%>

<%@ include file="/sub/header.jsp" %>
<%
  List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)request.getAttribute("list");
%>
		<!-- 헤더 드가는 부분 -->
		<section>

			<article id="memb_tags">
				<div class="container">
					<div class="tags_div">
						<h3>카테고리</h3>
						<span class="field"><i class="fa fa-cogs" aria-hidden="true"></i>개발자</span>
						<span class="field"><i class="fa fa-picture-o" aria-hidden="true"></i>디자이너</span>
						<span class="field"><i class="fa fa-building-o" aria-hidden="true"></i>기획자</span>
						<span class="field"><i class="fa fa-briefcase" aria-hidden="true"></i>영업</span>
					</div>

					<div class="tags_div">
						<h3>카테고리</h3>
						<span class="label label-default">JAVA</span>
						<span class="label label-primary">JSP</span>
						<span class="label label-success">Mysql</span>
						<span class="label label-info">Oracle</span>
						<span class="label label-warning">JavaScript</span>
						<span class="label label-danger">Node.js</span>
					</div>

				</div>
			</article>

			<article class="container">
				<div class="row">
					<aside class="col-md-4 col-md-push-8" id="rec_aside">
						<div class="comp_regi_area">
	            <button data-toggle="modal" data-target="#recruit_write" type="button" class="btn btn-primary btn-block">채용 등록</button>
	          </div>
	          <div class="comp_banner">
	            <img src="imgs/comp_banner.jpg" alt="" />
	          </div>
	          <div class="memb_regi_area">
	            <p>로켓펀치에 가입하시면 회사 연락처를 볼 수 있고, 중요한 업데이트 알림을 받아볼 수 있습니다.</p>
	            <button type="button" class="btn btn-info btn-block">무료가입</button>
	          </div>
					</aside>
					<div class="col-md-8 col-md-pull-4" id="rec_list">
						<div class="rec_sort">
							<span>최신순</span>
							<span>인기순</span>
						</div>

						<div class="row">
            
            <%if(list!=null){
            for(HashMap<String, Object> e : list){
              CompanyRecruitBean crb = (CompanyRecruitBean)e.get("crb");
            	CompanyBean cb = (CompanyBean)e.get("cb");
            %>
              <article class="col-md-12 rec_wrap">
                <div class="info nowrap">
									<div class="rec_img" style="background:url('<%=cb.getCLogo()%>') no-repeat center center;"></div>
                 <h4> <a href="/RecruitContent.co?id=<%=crb.getId()%>"><strong><%=crb.getCTitle()%></strong><span><%=cb.getCNamek()%></span></a></h4>
                  <p><%=cb.getCIntro()%></p>
                </div>
                <span><%=crb.getCJobDateEnd()%>까지</span>
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
                
              </article>
              
           <%}}%>
           
		<!--페이지네이션-->
							<div class="row">
								<div class="col-md-12 pagi">
									<ul class="pagination pagination-lg">
										<li><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>

				</div>
			</article>

		</section>

    <!-- 채용등록 모달 -->
    <div id="recruit_write" class="modal fade" role="dialog">
      <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">기본정보 수정</h4>
          </div>
          <div class="modal-body row">
            <!--수정 폼-->
            	<form action="/RecruitWrite.co" method="post" name="fr" id="joinForm">
<!-- 		<input name="c_id" class="form-control" type="hidden" placeholder=""> -->
    	<div class="panel-body form-horizontal payment-form">
        	<div class="form-group">
	        	<label class="col-sm-2 control-label">
	        	채용공고 타이틀
	        	</label>
	            <div class="col-sm-10">
	            	<input name="c_title" class="form-control" type="text" placeholder="" required>
	            </div>
	        </div>
	        <div class="form-group">
	        	<label class="col-sm-2 control-label">
	        	채용 시작일
	        	</label>
	            <div class="col-sm-4">
	            	<input name="c_job_date_start" class="form-control" type="date" placeholder="" required>
	            </div>
	        </div>
	        <div class="form-group">
	        	<label class="col-sm-2 control-label">
	        	채용 마감일
	        	</label>
	            <div class="col-sm-4">
	            	<input name="c_job_date_end" class="form-control" type="date" placeholder="" required>
	            </div>
	        </div>
	        <div class="form-group">
	        	<label class="col-sm-2 control-label">직무분야</label>
            <div class="col-sm-10">
							<select name="c_field" class="form-control">
										<option>----</option>
						        <option value="디자인">디자인</option>
						        <option value="웹">웹</option>
						        <option value="앱">앱</option>
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
		        	<label class="col-sm-2 control-label">
		        	경력
		        	</label>
		            <div class="col-sm-10">
						<select name="c_career" class="form-control">
						<option>----</option>
					        <option value="신입">신입</option>
					        <option value="경력">경력</option>
						</select>
		            </div>
		        </div>
			    <div class="form-group">
		        	<label class="col-sm-2 control-label">
		        	주요업무
		        	</label>
		            <div class="col-sm-10">
		            	<input name="c_task" class="form-control" type="text" placeholder="" required>
		            </div>
		        </div>
			    <div class="form-group">
		        	<label class="col-sm-2 control-label">
		        	근무지
		        	</label>
		            <div class="col-sm-3">
		            	<select name="c_locations" class="form-control">
							<option>----</option>
					        <option value="서울">서울</option>
					        <option value="부산">부산</option>
					        <option value="그외">그외</option>
						</select>
		            </div>
		        </div>
			    <div class="form-group">
		        	<label class="col-sm-2 control-label">
		        	연봉
		        	</label>
		            <div class="col-sm-3">
		            	<select name="c_salary" class="form-control">
							<option>----</option>
					        <% for(int i=18; i<=50; i++){ %>
					        <option value="<%=i*100+"만원"%>"><%=i %>00만원</option>
					        <% } %>
						</select>
		            </div>
		        </div>
		        <div class="form-group">
					<label class="col-sm-2 control-label">
		        	문의전화
		        	</label>
		            <div class="col-sm-10">
		            	<input name="c_tel_question" class="form-control" type="text" placeholder="" required>
		            </div>

	        	</div>
		        <div class="form-group">
		        	<label class="col-sm-2 control-label">
		        	채용상세정보
		        	</label>
		            <div class="col-sm-10">
		            	<textarea name="c_job_content" cols="10" rows="5" maxlength="150" class="form-control" style="width: 500px;" placeholder="제목..150자까지 가능합니다."></textarea>
		            </div>
		        </div>
	        </div><!-- <div class="panel-collapse collapse" id="collapseOne"> -->
			<!-- 추가사항 입력폼 끝 -->
		    </div><!-- <div class="panel panel-default"> -->
            <!-- null가능한 컬랩스 끝 -->
            <div class="form-group">
               	<label for="concept" class="col-xs-2 control-label"></label>
                <div class="col-xs-10">
                   	<input type="submit" value="채용등록" class="btn btn-sm btn-success">
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
		<!-- 푸터 드가는 부분 -->
		<%@ include file="/sub/footer.jsp" %>
		<!-- 푸터 드가는 부분 -->
