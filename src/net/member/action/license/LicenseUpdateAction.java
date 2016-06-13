package net.member.action.license;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.Action;
import net.member.action.ActionForward;
import net.member.db.dao.MemberLicenseDAO;
import net.member.db.dto.MemberLicense;

public class LicenseUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberLicense ml = new MemberLicense();
		ActionForward forward = new ActionForward();
		//세션 아이디값 얻어옴
		HttpSession session = request.getSession();
		int userId = ((Integer)session.getAttribute("id")).intValue();
		int paramId = Integer.parseInt(request.getParameter("member_id"));
		int dataId = Integer.parseInt(request.getParameter("id"));
		
		//세션 아이디와 파라미터로 넘어온 아이디가 같을경우 삽입
		if(userId==paramId){
			ml.setId(dataId);
			ml.setLicenseName(request.getParameter("license_name"));
			ml.setLicenseAgency(request.getParameter("license_agency"));
			ml.setLicGetDate(Date.valueOf(request.getParameter("lic_get_date")));
			ml.setAbout(request.getParameter("about"));
			
			MemberLicenseDAO mldao = new MemberLicenseDAO();
			mldao.updateLicense(ml);

			forward.setRedirect(true);
			forward.setPath("/MemberInfoAction.me?id="+userId);
		}	
		
		return forward;
	}

}
