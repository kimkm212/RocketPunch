package net.company.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.company.db.dao.CompanyDAO;
import net.company.db.dao.CompanyRecruitDAO;
import net.company.dto.CompanyBean;
import net.company.dto.CompanyRecruitBean;
import net.member.db.dao.MemberDAO;

public class CompanyInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer sessionId = (Integer)request.getSession().getAttribute("id");
		int c_id = Integer.parseInt(request.getParameter("id"));
		
		CompanyDAO cdao = new CompanyDAO();
		MemberDAO mdao = new MemberDAO();
		CompanyBean cb = cdao.getCompany(c_id);
		List<HashMap<String, Object>> memberList = null;
		
		//맴버정보 저장
		if(sessionId==null){
			memberList = mdao.getMemberListInCompany(c_id);
		}else{
			memberList = mdao.getMemberListInCompany(c_id, sessionId);
		}
		
		CompanyRecruitDAO crdao = new CompanyRecruitDAO();
		List<CompanyRecruitBean> recruitList = crdao.getRecruitListInCompany(c_id);
		
		request.setAttribute("cb", cb);
		request.setAttribute("memberList", memberList);
		request.setAttribute("recruitList", recruitList);
		
		ActionForward forward=new ActionForward();
		forward.setPath("/company/company_info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
