package net.company.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.company.action.Action;
import net.company.action.ActionForward;
import net.company.action.recruit.*;
import net.company.action.apply.*;


public class CompanyFrontController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7702753586728224539L;
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//가상주소 가져오기
		String requestURI=request.getRequestURI();

		String contextPath=request.getContextPath();

		String command=requestURI.substring(contextPath.length());

		System.out.println(command);
	
		ActionForward forward=null;
		Action action=null;
		
		
		switch (command) {
		
			case "/companyJoin.co":
				action = new CompanyJoinAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/CompanyInfo.co":
				action = new CompanyInfoAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/CompanyList.co":
				action = new CompanyListAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/CompanyUpdate.co":
				action = new CompanyUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/CompanyDelete.co":
				action = new CompanyDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
				
				
				//채용 관련 부분
				
			case "/RecruitWrite.co":
				action = new RecruitWriteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/RecruitList.co":
				action = new RecruitListAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/RecruitContent.co":
				action = new RecruitContentAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/RecruitUpdate.co":
				action = new RecruitUpdateAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/RecruitAdmin.co":
				action = new RecruitAdminAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "/RecruitDelete.co":
				action = new RecruitDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
				
			//지원부분
			case "/Apply.co":
				action = new ApplyAction();
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
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}                                                                                   
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
}
