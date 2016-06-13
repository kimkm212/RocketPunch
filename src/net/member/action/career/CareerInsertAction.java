package net.member.action.career;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberCareerDAO;
import net.member.db.dto.MemberCareer;


public class CareerInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		MemberCareer mc = new MemberCareer();
		ActionForward forward = new ActionForward();
		//세션 아이디값 얻어옴
		HttpSession session = request.getSession();
		int userId = ((Integer)session.getAttribute("id")).intValue();
		int paramId = Integer.parseInt(request.getParameter("member_id"));
		
		//세션 아이디와 파라미터로 넘어온 아이디가 같을경우 삽입
		if(userId==paramId){
			String sInCompany = request.getParameter("in_company");
			int inCompany;
			if(sInCompany==null){
				inCompany = 0;
			}else{
				inCompany = 1;
			}
			mc.setMemberId(paramId);
			mc.setCompany(request.getParameter("company"));
			mc.setRole(request.getParameter("role"));
			mc.setComInDate(Date.valueOf(request.getParameter("com_in_date")));
			mc.setComOutDate(Date.valueOf(request.getParameter("com_out_date")));
			mc.setInCompany(inCompany);
			mc.setAbout(request.getParameter("about"));
			MemberCareerDAO mcdao = new MemberCareerDAO();
			mcdao.insertCareer(mc);
			

			forward.setRedirect(true);
			forward.setPath("/MemberInfoAction.me?id="+userId);
		}
		
		return forward;
	}

}
