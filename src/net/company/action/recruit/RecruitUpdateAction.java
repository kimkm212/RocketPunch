package net.company.action.recruit;

import net.company.action.ActionForward;
import net.company.db.dao.CompanyRecruitDAO;
import net.company.dto.CompanyRecruitBean;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.action.Action;

public class RecruitUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		Integer sessionC_id=(Integer)request.getSession().getAttribute("c_id");
		int paramC_id = Integer.parseInt(request.getParameter("company_id"));
		CompanyRecruitDAO crdao = new CompanyRecruitDAO();
		CompanyRecruitBean crb = new CompanyRecruitBean();
		ActionForward forward=new ActionForward();
		
		if(sessionC_id==paramC_id){
			crb.setId(Integer.parseInt(request.getParameter("id")));
			crb.setCompanyId(paramC_id);
			crb.setCTitle(request.getParameter("c_title"));
			crb.setCJobDateStart(Date.valueOf(request.getParameter("c_job_date_start")));
			crb.setCJobDateEnd(Date.valueOf(request.getParameter("c_job_date_end")));
			crb.setCField(request.getParameter("c_field"));
			crb.setCCareer(request.getParameter("c_career"));
			crb.setCTask(request.getParameter("c_task"));
			crb.setCLocations(request.getParameter("c_locations"));
			crb.setCSalary(request.getParameter("c_salary"));
			crb.setCTelQuestion(request.getParameter("c_tel_question"));
			crb.setCJobContent(request.getParameter("c_job_content"));
			crdao.updateRecruit(crb);

			forward.setRedirect(true);
			forward.setPath("/RecruitContent.co?id="+request.getParameter("id"));
		}else{
			
			forward.setRedirect(true);
			forward.setPath("/RecruitContent.co?id="+request.getParameter("id"));
		}
		

		return forward;
	}

}
