package net.member.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.company.db.dao.CompanyDAO;
import net.company.db.dao.CompanyRecruitDAO;
import net.company.dto.CompanyBean;
import net.member.db.dao.MemberDAO;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer sessionId = (Integer)session.getAttribute("id");
		
		CompanyDAO cdao = new CompanyDAO();
		List<CompanyBean> com_list = cdao.getCompanyList(1, 3);
		
		CompanyRecruitDAO crdao = new CompanyRecruitDAO();
		List<HashMap<String, Object>> rec_list = crdao.getRecruitList(1, 3);
		
		MemberDAO mdao = new MemberDAO();
		List<HashMap<String, Object>> mem_list = null;
		
		if(sessionId==null){
			mem_list = mdao.getMemberList(1,2);
		}else{
			mem_list = mdao.getMemberList(1,2,sessionId);
		}
		
		request.setAttribute("com_list", com_list);
		request.setAttribute("rec_list", rec_list);
		request.setAttribute("mem_list", mem_list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp");
		
		return forward;
	}

}
