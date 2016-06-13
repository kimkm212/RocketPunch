package net.company.action.recruit;

import net.company.action.ActionForward;
import net.company.action.Action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.db.dao.CompanyRecruitDAO;
import net.company.dto.CompanyBean;
import net.member.db.dao.MemberDAO;

public class RecruitContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		Integer sessionId = (Integer)request.getSession().getAttribute("id");
		int paramC_id = Integer.parseInt(request.getParameter("id"));
		
		CompanyRecruitDAO crdao = new CompanyRecruitDAO();
		HashMap<String, Object> result = crdao.getRecruitContent(paramC_id);
		List<HashMap<String, Object>> memberList = null;
		
		int c_id = ((CompanyBean)result.get("cb")).getId();
	
		MemberDAO mdao = new MemberDAO();
		//맴버정보 저장
		if(sessionId==null){
			memberList = mdao.getMemberListInCompany(c_id);
		}else{
			memberList = mdao.getMemberListInCompany(c_id, sessionId);
		}
		
		request.setAttribute("result", result);
		request.setAttribute("memberList", memberList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/company/company_recruit_content.jsp");
		return forward;
	}

}
