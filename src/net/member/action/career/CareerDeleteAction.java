package net.member.action.career;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberCareerDAO;

public class CareerDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		//세션 아이디값 얻어옴
		HttpSession session = request.getSession();
		int userId = ((Integer)session.getAttribute("id")).intValue();
		int paramId = Integer.parseInt(request.getParameter("member_id"));
		
		if(userId==paramId){
			
			MemberCareerDAO madao = new MemberCareerDAO();
			madao.deleteCareer(Integer.parseInt(request.getParameter("id")));
			
			forward.setRedirect(true);
			forward.setPath("/MemberInfoAction.me?id="+userId);
		}
		
		return forward;
	}

}
