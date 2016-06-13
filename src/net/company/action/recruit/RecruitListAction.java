package net.company.action.recruit;

import net.company.action.ActionForward;
import net.company.action.Action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.db.dao.CompanyRecruitDAO;


public class RecruitListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CompanyRecruitDAO crdao = new CompanyRecruitDAO();
		
		List<HashMap<String, Object>> list = crdao.getRecruitList(1, 20);
		
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/company/company_recruit_list.jsp");
		return forward;
	}

}
