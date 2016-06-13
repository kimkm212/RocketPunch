package net.member.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commonDAO.DbConnector;
import net.member.db.dto.MemberSchool;


public class MemberSchoolDAO extends DbConnector{

	public void insertSchool(MemberSchool ms) { // Insert Award
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {
			con = getConnection();
			sql = "insert into member_school(member_id, school, major, sch_in_year, sch_out_year, finish, about) values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ms.getMemberId());
			pstmt.setString(2, ms.getSchool());
			pstmt.setString(3, ms.getMajor());
			pstmt.setDate(4, ms.getSchInYear());
			pstmt.setDate(5, ms.getSchOutYear());
			pstmt.setString(6, ms.getFinish());
			pstmt.setString(7, ms.getAbout());
	
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	
	}
	
	public List<MemberSchool> getSchool(int id) {
		List<MemberSchool> list = new ArrayList<MemberSchool>();

		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			sql = "select * from member_school where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberSchool ma = new MemberSchool();
				ma.setId(rs.getInt("id"));
				ma.setMemberId(rs.getInt("member_id"));
				ma.setSchool(rs.getString("school"));
				ma.setMajor(rs.getString("major"));
				ma.setSchInYear(rs.getDate("sch_in_year"));
				ma.setSchOutYear(rs.getDate("sch_out_year"));
				ma.setFinish(rs.getString("finish"));
				ma.setAbout(rs.getString("about"));
				list.add(ma);
			}

		} catch (Exception e) {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return list;
	}
	
	
	public void updateSchool(MemberSchool ms){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {

			con = getConnection();
			
			sql = "update  member_school set school=?,major=?,sch_in_year=?,sch_out_year=?,finish=?,about=? where id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, ms.getSchool());
			pstmt.setString(2, ms.getMajor());
			pstmt.setDate(3, ms.getSchInYear());
			pstmt.setDate(4, ms.getSchOutYear());
			pstmt.setString(5, ms.getFinish());
			pstmt.setString(6, ms.getAbout());
			pstmt.setInt(7, ms.getId());
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
	}
	
	public void deleteSchool(int id) { // Delete project
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {
	
			con = getConnection();
	
			sql = "delete from member_school where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
	
			// Action
			pstmt.executeUpdate();
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if(con != null) try{con.close();}catch(SQLException ex){}
		}
	
	
	} 
		
	
}
