package net.member.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.dao.MemberDAO;


public class MemberListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer sessionId = (Integer)session.getAttribute("id");
		MemberDAO mdao = new MemberDAO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		if(sessionId==null){
			list = mdao.getMemberList(1,20);
		}else{
			list = mdao.getMemberList(1,20,sessionId);
		}
		
		
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/member/member_list.jsp");
		return forward;
	}

}
