package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.dao.MemberDAO;

public class MemberDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

				
				Integer id = (Integer)request.getSession().getAttribute("id");
				String passwd = request.getParameter("pwd");
				
				//디비객체 생성 MemberDAO mdao
				MemberDAO mdao=new MemberDAO();
				//결과 성공 여부 가져오기
				int check = mdao.deleteMember(id, passwd);
				//check==0 비밀번호 틀림 > 뒤로이동
				//check ==-1 아이디 없음 > 뒤로이동
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
				}
				
				//세션값가져오기, 삭제하기
				HttpSession session=request.getSession();
				session.invalidate();
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('탈퇴완료');");
				out.println("location.href='/';");
				out.println("</script>");
				out.close();
				return null;
				
				
	}

}