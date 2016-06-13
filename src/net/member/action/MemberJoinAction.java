package net.member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.dao.MemberDAO;
import net.member.db.dto.MemberBean;

public class MemberJoinAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("MemberJoinAction execute()");
		//한글처리
		request.setCharacterEncoding("utf-8");
		// 자바빈 파일 net.member.db  MemberBean
		// 자바빈 객체 생성  mb
		MemberBean mb=new MemberBean();
		mb.setEmail(request.getParameter("email"));
		mb.setFirstNameK(request.getParameter("first_name_k"));
		mb.setLastNameK(request.getParameter("last_name_k"));
		mb.setPwd(request.getParameter("pwd"));
		MemberDAO mdao=new MemberDAO();
		// 메서드호출 insertMember(mb)
		mdao.insertMember(mb);
		// 이동  ./MemberLogin.me
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/");
		return forward;
	}
}
