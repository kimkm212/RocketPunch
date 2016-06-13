package net.member.action.award;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberAwardDAO;
import net.member.db.dto.MemberAward;

public class AwardInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		MemberAward ma = new MemberAward();
		ActionForward forward = new ActionForward();
		//세션 아이디값 얻어옴
		HttpSession session = request.getSession();
		int userId = ((Integer)session.getAttribute("id")).intValue();
		int paramId = Integer.parseInt(request.getParameter("member_id"));
		
		if(userId==paramId){
			ma.setMemberId(paramId);
			ma.setAwardType(request.getParameter("award_type"));
			ma.setAwardName(request.getParameter("award_name"));
			ma.setAwardAgency(request.getParameter("award_agency"));
			ma.setAwardDate(Date.valueOf(request.getParameter("award_date")));
			MemberAwardDAO madao = new MemberAwardDAO();
			madao.insertAward(ma);
			
			forward.setRedirect(true);
			forward.setPath("/MemberInfoAction.me?id="+userId);
		}
		
		return forward;
	}

}
