package net.company.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.db.dao.CompanyDAO;

public class CompanyDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer sessionC_id = (Integer)request.getSession().getAttribute("c_id");
		Integer paramC_id = Integer.parseInt(request.getParameter("c_id"));
		
		if(sessionC_id == paramC_id){
		
			CompanyDAO cdao = new CompanyDAO();
			
			cdao.deleteCompany(paramC_id);
			
			request.getSession().setAttribute("c_id", null);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('기업삭제 완료');");
			out.println("location.href='/CompanyList.co';");
			out.println("</script>");
			out.close();
			return null;
			
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다');");
			out.println("location.href='/CompanyList.co';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		
		
	}

}
