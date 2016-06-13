package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.dao.MemberDAO;

public class MemberJoinCompanyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int m_id = (Integer)request.getSession().getAttribute("id");
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.memberJoinCompany(m_id, c_id);
		
		if(result==1){
			request.getSession().setAttribute("c_id", c_id);
		}
		//결과값을 리턴해 ajax처리함
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.close();
		return null;
	}

}
