package net.company.action.recruit;

import net.company.action.ActionForward;
import net.company.db.dao.CompanyRecruitDAO;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.action.Action;

public class RecruitDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		Integer sessionC_id = (Integer)request.getSession().getAttribute("c_id");
		Integer paramC_id = Integer.parseInt(request.getParameter("c_id"));
		Integer paramR_id = Integer.parseInt(request.getParameter("r_id"));
		
		if(sessionC_id == paramC_id){
			CompanyRecruitDAO crdao = new CompanyRecruitDAO();
			crdao.deleteRecruit(paramR_id);
			

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('공고 삭제 완료');");
			out.println("location.href='/RecruitAdmin.co?id="+sessionC_id+"';");
			out.println("</script>");
			out.close();
			return null;
		}
		return null;
	}

}
