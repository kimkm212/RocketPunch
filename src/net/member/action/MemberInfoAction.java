package net.member.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.dao.*;
import net.member.db.dto.*;

public class MemberInfoAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberDAO mdao=new MemberDAO();
		MemberProjectDAO pdao = new MemberProjectDAO();
		MemberCareerDAO mcdao = new MemberCareerDAO();
		MemberAwardDAO madao = new MemberAwardDAO();
		MemberLicenseDAO mldao = new MemberLicenseDAO();
		MemberSchoolDAO msdao = new MemberSchoolDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Integer sessionId = (Integer)request.getSession().getAttribute("id");

		HashMap<String, Object> result = null;
		
		//세션 정보가 있을땐 로그인 사용자와의 친구 관계를 같이 가져온다. 
		if(sessionId==null){
			result = mdao.getMember(id);
		}else{
			result = mdao.getMember(id, sessionId);
		}
		
		List<MemberProject> mp = pdao.getProject(id);
		List<MemberCareer> mc = mcdao.getCareer(id);
		List<MemberAward> ma = madao.getAward(id);
		List<MemberLicense> ml = mldao.getLicense(id);
		List<MemberSchool> ms = msdao.getSchool(id);
		
		//저장 request
		request.setAttribute("result", result);
		request.setAttribute("mp", mp);
		request.setAttribute("mc", mc);
		request.setAttribute("ma", ma);
		request.setAttribute("ml", ml);
		request.setAttribute("ms", ms);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/member/member_info.jsp");
		return forward;
		
	}
}
