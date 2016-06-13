<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
     import="java.util.HashMap,
            java.util.List,
            net.member.db.dto.MemberBean,
            net.member.db.dto.MemberFriend,
            net.company.dto.CompanyBean"
%>
<%
  List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)request.getAttribute("list");

%>
		<%@ include file="/sub/header.jsp" %>
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

			<article class="container" id="memb_profile">
				<div class="row">
        <%
        if(list!=null){
          for(HashMap<String, Object> e : list){
            MemberBean mb = (MemberBean)e.get("mb");
            CompanyBean cb = (CompanyBean)e.get("cb");
            Integer from_to = (Integer)e.get("from_to");
            Integer to_from = (Integer)e.get("to_from");

        %>

          <article class="col-md-6 main_pannel">
            <div class="container-fluid pannel_wrap">
              <div class="prof_info">
                <h4><strong><a href="MemberInfoAction.me?id=<%=mb.getId()%>"><%=mb.getFirstNameK()+mb.getLastNameK()%> </a></strong></h4>
                <p><%=mb.getCRole()%></p>
                <p><%=cb.getCNamek()%><%if(cb.getCNamee()!=""){ %><span><%="("+cb.getCNamee()+")"%></span><%}%></p>
              </div>
              <div class="pro_img" style="background:url('<%=mb.getImageProfile()%>') no-repeat center center;"></div>
              <%
              if(sessionId!=null){%>
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
          </article>

        <%}}%>
				</div>

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
			</article>

			<!--회원가입 종용 -->
			<article class="container">
				<div class="row">
					<div class="col-md-12">
						<h3>로켓펀치에 가입하면 더 많은 분야의 전문가들을 만날 수 있습니다.</h3>
						<p>로켓펀치에 가입하면 전체 목록을 볼 수 있고, 직접 연락을 주고 받을 수 있습니다.
							로켓펀치를 통해 다양한 분야의 전문가들과 만나 보세요.
						</p>
							<button type="button" class="btn btn-primary btn-block">가입하기</button>
					</div>
				</div>
			</article>

		</section>
		<!-- 푸터 드가는 부분 -->
		<%@ include file="/sub/footer.jsp" %>
		<!-- 푸터 드가는 부분 -->
