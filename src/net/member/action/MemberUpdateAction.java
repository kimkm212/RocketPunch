package net.member.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.dao.MemberDAO;
import net.member.db.dto.MemberBean;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
	
		HttpSession session = request.getSession();
		int sessionId = (Integer)session.getAttribute("id");
		
		//저장경로 지정
		String realPath=request.getSession().getServletContext().getRealPath("/upload/"+sessionId);
		//만약 업로드 폴더가 존재하지 않으면 폴더 만든다.
		File f = new File(realPath);
		if(!f.exists()){
			f.mkdirs();
		}
		
		int maxSize=10*1024*1024;
		MultipartRequest multi=new MultipartRequest(request, realPath, maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		int paramId = Integer.parseInt(multi.getParameter("id"));
	
		//세션 아이디와 파라미터로 넘어온 아이디가 같을 경우에만 업데이트를 실행함....
		if(sessionId == paramId){
			
			String lookjob = multi.getParameter("look_for_a_job");
			int ilookjob;
			if(lookjob==null){
				ilookjob=0;
			}else{
				ilookjob=1;
			}
			
			//프로필과 커버 이미지는 새로운 파일이 안들어 올 경우 원래 저장된 값을 다시 입력함
			String cover_img = multi.getFilesystemName("image_cover");
			if(cover_img!=null){
				cover_img = "/upload/"+sessionId+"/"+multi.getFilesystemName("image_cover");
			}else{
				cover_img=multi.getParameter("h_image_cover");
			}
			
			String pro_img = multi.getFilesystemName("image_profile");
			if(pro_img!=null){
				pro_img="/upload/"+sessionId+"/"+multi.getFilesystemName("image_profile");
			}else{
				pro_img=multi.getParameter("h_image_profile");
			}
			
			MemberBean mb = new MemberBean();
			mb.setId(paramId);
			mb.setFirstNameK(multi.getParameter("first_name_k"));
			mb.setLastNameK(multi.getParameter("last_name_k"));
			mb.setPwd(multi.getParameter("pwd"));
			mb.setSelfIntro(multi.getParameter("self_intro"));
			mb.setExperise(multi.getParameter("experise"));
			mb.setLookForAJob(ilookjob);
			mb.setCRole(multi.getParameter("c_role"));
			
			mb.setImageCover(cover_img);
			mb.setImageProfile(pro_img);
			MemberDAO mdao = new MemberDAO();
			mdao.updateMember(mb);
			
			String name = multi.getParameter("first_name_k")+multi.getParameter("last_name_k");
			session.setAttribute("pro_img", pro_img);
			session.setAttribute("name", name);
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/MemberInfoAction.me?id="+paramId);
		return forward;
	}

}
