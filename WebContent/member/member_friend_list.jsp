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

			<article class="container" id="memb_profile">
        <h3>내 친구 목록</h3>
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
              <div class="btn_friends">
                <button class="btn btn-default btn_xs" id="<%=mb.getId()%>">친구</button>
              </div>
            </div>
          </article>

        <%}}%>
				</div>

			</article>

		</section>
		<!-- 푸터 드가는 부분 -->
		<%@ include file="/sub/footer.jsp" %>
		<!-- 푸터 드가는 부분 -->
