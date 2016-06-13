package net.company.action;

import java.io.File;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.company.db.dao.CompanyDAO;
import net.company.dto.CompanyBean;

public class CompanyJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		Integer sessionId=(Integer)session.getAttribute("id");
		CompanyBean cb = new CompanyBean();
		CompanyDAO cdao = new CompanyDAO();
		ActionForward forward=new ActionForward();
		//저장경로 지정
		String uploadPath=request.getSession().getServletContext().getRealPath("/upload/company");
		//만약 업로드 폴더가 존재하지 않으면 폴더 만든다.
		File f = new File(uploadPath);
		if(!f.exists()){
			f.mkdirs();
		}
		
		int maxSize=10*1024*1024;
		MultipartRequest multi=new MultipartRequest(request, uploadPath, maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		if(sessionId!=null){
			
			Integer members = null;
			if(multi.getParameter("c_members")!=null){
				members = Integer.parseInt(multi.getParameter("c_members"));
			}
			
			Date birth = null;
			if(multi.getParameter("c_birthday")!=null){
				birth = Date.valueOf(multi.getParameter("c_birthday"));
			}
			
			String cover_img =null;
			if(multi.getFilesystemName("c_cover")!=null){
				cover_img = "/upload/company/"+multi.getFilesystemName("c_cover");
			}
			
			String logo_img =null;
			if(multi.getFilesystemName("c_logo")!=null){
				logo_img ="/upload/company/"+multi.getFilesystemName("c_logo");
			}
			
			cb.setCNamek(multi.getParameter("c_nameK"));
			cb.setCNamee(multi.getParameter("c_nameE"));
			cb.setCEmail(multi.getParameter("c_email"));
			cb.setCPart(multi.getParameter("c_part"));
			cb.setCRole(multi.getParameter("c_role"));
			cb.setCMembers(members);
			cb.setCAddress(multi.getParameter("c_address"));
			cb.setCHomepage(multi.getParameter("c_homepage"));
			cb.setCTel(multi.getParameter("c_tel"));
			cb.setCIntro(multi.getParameter("c_intro"));
			cb.setCIntroduce(multi.getParameter("c_introduce"));
			cb.setCBirthday(birth);
			cb.setCLogo(logo_img);
			cb.setCCover(cover_img);
			
			int c_id = cdao.insertCompany(cb, sessionId);
			
			request.getSession().setAttribute("c_id", c_id);
			
			forward.setRedirect(true);
			forward.setPath("CompanyList.co");
			return forward;
			
		}else{
			
			forward.setRedirect(true);
			forward.setPath("CompanyList.co");
			return forward;
			
		}

	}

}
