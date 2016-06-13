package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.award.*;
import net.member.action.career.*;
import net.member.action.license.*;
import net.member.action.project.*;
import net.member.action.school.*;
import net.member.action.friend.*;

public class MemberFrontController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3672650830621220020L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//가상주소 가져오기
		String requestURI=request.getRequestURI();

		System.out.println(requestURI);
		String contextPath=request.getContextPath();

		System.out.println(contextPath);
		String command=requestURI.substring(contextPath.length());

		System.out.println(command);
	
		ActionForward forward=null;
		Action action=null;
		
		//가상주소 비교
		switch (command) {
		
			case "/Main.me":
				action=new MainAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		
			case "/MemberJoinAction.me":
				action=new MemberJoinAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/MemberLoginAction.me":
				action=new MemberLoginAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "/MemberLogoutAction.me":
				action=new MemberLogoutAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/MemberInfoAction.me":
				action=new MemberInfoAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/MemberUpdateAction.me":
				action=new MemberUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/MemberList.me":
				action=new MemberListAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/MemberDelete.me":
				action=new MemberDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/MemberJoinCompany.me":
				action=new MemberJoinCompanyAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
				
			//맴버 이력 정보 삽입	
			case "/ProjectInsertAction.me":
				action=new ProjectInsertAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "/CareerInsertAction.me":
				action=new CareerInsertAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/AwardInsertAction.me":
				action=new AwardInsertAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/SchoolInsertAction.me":
				action=new SchoolInsertAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/LicenseInsertAction.me":
				action=new LicenseInsertAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		
				//
				//
				//
			//맴버 정보 삭제들
			case "/ProjectDeleteAction.me":
				action=new ProjectDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "/CareerDeleteAction.me":
				action=new CareerDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/AwardDeleteAction.me":
				action=new AwardDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/SchoolDeleteAction.me":
				action=new SchoolDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/LicenseDeleteAction.me":
				action=new LicenseDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				//
				//
				//
				//
				//맴버 정보 업데이트들
			case "/ProjectUpdateAction.me":
				action=new ProjectUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "/CareerUpdateAction.me":
				action=new CareerUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/AwardUpdateAction.me":
				action=new AwardUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/SchoolUpdateAction.me":
				action=new SchoolUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/LicenseUpdateAction.me":
				action=new LicenseUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				//
				//
				
			//친구 관계
				
			case "/FriendRequest.me":
				action=new FriendRequestAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/FriendAccept.me":
				action=new FriendAcceptAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/FriendDelete.me":
				action=new FriendDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/FriendList.me":
				action=new FriendListAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
		
		//이동
		if(forward!=null){
			if(forward.isRedirect()){
				//ture >> 데이터를 안 담아 갈 때
				response.sendRedirect(forward.getPath());	
			}else{
				//false >> 데이터를 담아 갈 때
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);	
			}
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("MemberFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("MemberFrontController doPost()");
		doProcess(request, response);
	}
}



