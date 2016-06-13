package net.company.action.recruit;

import net.company.action.ActionForward;
import net.company.db.dao.CompanyRecruitDAO;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.action.Action;

public class RecruitAdminAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CompanyRecruitDAO crdao = new CompanyRecruitDAO();
		ActionForward forward = new ActionForward();
		
		Integer sessionC_id = (Integer)request.getSession().getAttribute("c_id");
		Integer paramC_id = Integer.parseInt(request.getParameter("id"));
		
		if(sessionC_id==paramC_id){
			
			List<HashMap<String, Object>> list = crdao.getRecruitListAdmin(paramC_id);
			
			request.setAttribute("list", list);
			
			forward.setRedirect(false);
			forward.setPath("/company/company_admin.jsp");
			return forward;
			
		}else{
			forward.setRedirect(true);
			forward.setPath("/");
			return forward;
		}
		
	}

}
