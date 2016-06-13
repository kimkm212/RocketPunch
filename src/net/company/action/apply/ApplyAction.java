package net.company.action.apply;

import net.company.action.ActionForward;
import net.company.db.dao.CompanyApplyDAO;
import net.company.dto.CompanyApplyBean;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.company.action.Action;

public class ApplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		Integer sessionId = (Integer)request.getSession().getAttribute("id");
		
		//저장경로 지정
		String realPath=request.getSession().getServletContext().getRealPath("/upload/"+sessionId);
		//만약 업로드 폴더가 존재하지 않으면 폴더 만든다.
		File f = new File(realPath);
		if(!f.exists()){
			f.mkdirs();
		}
		
		int maxSize=10*1024*1024;
		MultipartRequest multi=new MultipartRequest(request, realPath, maxSize,"utf-8",new DefaultFileRenamePolicy());
				
		int member_id= Integer.parseInt(multi.getParameter("member_id"));
		int recruit_id = Integer.parseInt(multi.getParameter("recruit_id"));
		
		if(sessionId==member_id){
			
			CompanyApplyBean cab = new CompanyApplyBean();
			cab.setMemberId(member_id);
			cab.setRecruitId(recruit_id);
			cab.setPoUrl(multi.getParameter("po_url"));
			cab.setPoUrlFile("/upload/"+sessionId+"/"+multi.getFilesystemName("po_url_file"));
			cab.setMoney(multi.getParameter("money"));
			cab.setTel(multi.getParameter("tel"));
			cab.setContent(multi.getParameter("content"));
			
			CompanyApplyDAO cadao = new CompanyApplyDAO();
			cadao.insertApply(cab, member_id, recruit_id);
			
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/RecruitContent.co?id="+recruit_id);
		return forward;
	}

}
