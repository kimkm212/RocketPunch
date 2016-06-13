package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.dao.MemberDAO;
import net.member.db.dto.MemberBean;


public class MemberLoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction execute");
		//id,passwd  파라미터 가져오기
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		// 디비객체 생성 MemberDAO mdao
		MemberDAO mdao=new MemberDAO();
		//int check =메서드호출  userCheck(id, passwd)
		int check=mdao.userCheck(email, pwd);
		// check==0 "비밀번호틀림" 뒤로이동
		// check==-1 "아이디없음" 뒤로이동
		if(check==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==-1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else{
			// check==1  세션값 생성 이동  
			MemberBean mb = mdao.getMember(email);
			String name = mb.getFirstNameK()+mb.getLastNameK();
			HttpSession session=request.getSession();
			session.setAttribute("id", mb.getId());
			session.setAttribute("name", name);
			session.setAttribute("c_id", mb.getCId());
			session.setAttribute("pro_img", mb.getImageProfile());
			
			ActionForward forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/");
			return forward;
			
		}
		
	}
}




