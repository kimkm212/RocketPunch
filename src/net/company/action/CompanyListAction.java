package net.company.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.db.dao.CompanyDAO;
import net.company.dto.CompanyBean;

public class CompanyListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		CompanyDAO cdao = new CompanyDAO();
		
		List<CompanyBean> companyList = cdao.getCompanyList(1, 10);
		
		request.setAttribute("companyList", companyList);

		ActionForward forward = new ActionForward();
		forward.setPath("/company/company_list.jsp");
		forward.setRedirect(false);
				
		
		return forward;
	}

	
	
}
