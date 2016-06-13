package net.company.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import commonDAO.DbConnector;
import net.company.dto.CompanyApplyBean;

public class CompanyApplyDAO extends DbConnector{

	
		//채용지원
		public void insertApply(CompanyApplyBean cab, int member_id, int recruit_id) { 
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "";
			ResultSet rs=null;

			try {
				con = getConnection();
				
				sql = "insert into company_apply(member_id, recruit_id, po_url, po_url_file, money, tel, content) "
						+ "values(?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);			
					
				pstmt.setInt(1, member_id);			//아이디
				pstmt.setInt(2, recruit_id);					//채용공고 인덱스
				pstmt.setString(3, cab.getPoUrl());			//포트폴리오주소
				pstmt.setString(4, cab.getPoUrlFile());	//포트폴리오파일
				pstmt.setString(5, cab.getMoney());			//희망연봉
				pstmt.setString(6, cab.getTel());
				pstmt.setString(7, cab.getContent());		//지원 메시지
				pstmt.executeUpdate();
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//닫기
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
	
		}//채용지원 
		
		
		//채용지원 삭제
		public void deleteApply(int id) { 
			
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "";
			ResultSet rs=null;
			
			try {

				con = getConnection();

				sql = "delete from jobin where c_jobin_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);

				// Action
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//닫기
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			
		}
	}
}
