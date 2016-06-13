package net.member.action.project;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberProjectDAO;
import net.member.db.dto.MemberProject;

public class ProjectUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberProject mp = new MemberProject();
		ActionForward forward = new ActionForward();
		//세션 아이디값 얻어옴
		HttpSession session = request.getSession();
		int userId = ((Integer)session.getAttribute("id")).intValue();
		int paramId = Integer.parseInt(request.getParameter("member_id"));
		int dataId = Integer.parseInt(request.getParameter("id"));
		
		//세션 아이디와 파라미터로 넘어온 아이디가 같을경우 삽입
		if(userId==paramId){
			String sOngoing = request.getParameter("ongoing");
			int ongoing;
			if(sOngoing==null){
				ongoing = 0;
			}else{
				ongoing = 1;
			}
			mp.setId(dataId);
			mp.setProjectName(request.getParameter("project_name"));
			mp.setOwnership(request.getParameter("ownership"));
			mp.setProInYear(Date.valueOf(request.getParameter("pro_in_year")));
			mp.setProOutYear(Date.valueOf(request.getParameter("pro_out_year")));
			mp.setOngoing(ongoing);
			mp.setResultWeb(request.getParameter("result_web"));
			mp.setResultApple(request.getParameter("result_apple"));
			mp.setResultGoggle(request.getParameter("result_goggle"));
			mp.setTeam(request.getParameter("team"));
			mp.setAbout(request.getParameter("about"));
			MemberProjectDAO pdao = new MemberProjectDAO();
			pdao.updateProject(mp);
			
			
			forward.setRedirect(true);
			forward.setPath("/MemberInfoAction.me?id="+userId);
			
			}
		return forward;
	}

	}
