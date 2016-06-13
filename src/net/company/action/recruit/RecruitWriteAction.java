package net.company.action.recruit;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.action.ActionForward;
import net.company.action.Action;
import net.company.db.dao.CompanyRecruitDAO;
import net.company.dto.CompanyRecruitBean;


public class RecruitWriteAction implements Action{
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		int c_id=(Integer)request.getSession().getAttribute("c_id");
		
		CompanyRecruitDAO crdao = new CompanyRecruitDAO();
		CompanyRecruitBean crb = new CompanyRecruitBean();
	
		crb.setCompanyId(c_id);
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
		crdao.insertRecruit(crb);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/RecruitList.co");

		return forward;
	}
	
}
