package net.member.action.school;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberSchoolDAO;
import net.member.db.dto.MemberSchool;

public class SchoolUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberSchool ms = new MemberSchool();
		ActionForward forward = new ActionForward();
		//세션 아이디값 얻어옴
		HttpSession session = request.getSession();
		int userId = ((Integer)session.getAttribute("id")).intValue();
		int paramId = Integer.parseInt(request.getParameter("member_id"));
		int dataId = Integer.parseInt(request.getParameter("id"));
		
		if(userId==paramId){
			ms.setId(dataId);
			ms.setSchool(request.getParameter("school"));
			ms.setMajor(request.getParameter("major"));
			ms.setSchInYear(Date.valueOf(request.getParameter("sch_in_year")));
			ms.setSchOutYear(Date.valueOf(request.getParameter("sch_out_year")));
			ms.setFinish(request.getParameter("finish"));
			ms.setAbout(request.getParameter("about"));
			MemberSchoolDAO msdao = new MemberSchoolDAO();
			msdao.updateSchool(ms);
		
			forward.setRedirect(true);
			forward.setPath("/MemberInfoAction.me?id="+userId);
			
		}
		return forward;
	}

}
