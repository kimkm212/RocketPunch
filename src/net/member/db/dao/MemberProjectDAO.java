package net.member.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commonDAO.DbConnector;
import net.member.db.dto.MemberProject;

public class MemberProjectDAO extends DbConnector{

	
	public void insertProject(MemberProject mp){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {

			con = getConnection();

			sql = "insert into member_project(member_id,project_name,ownership,pro_in_year,pro_out_year,ongoing, result_web, result_apple, result_goggle,team, about) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mp.getMemberId());
			pstmt.setString(2, mp.getProjectName());
			pstmt.setString(3, mp.getOwnership());
			pstmt.setDate(4, mp.getProInYear());
			pstmt.setDate(5, mp.getProOutYear());
			pstmt.setInt(6, mp.getOngoing());
			pstmt.setString(7, mp.getResultWeb());
			pstmt.setString(8, mp.getResultApple());
			pstmt.setString(9, mp.getResultGoggle());
			pstmt.setString(10, mp.getTeam());
			pstmt.setString(11, mp.getAbout());
		
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	
	}// END InsertProject
	

	public List<MemberProject> getProject(int id){
		
		List<MemberProject> list = new ArrayList<MemberProject>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = getConnection();
	
			sql = "select * from member_project where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				MemberProject mp = new MemberProject();
				mp.setId(rs.getInt("id"));
				mp.setMemberId(rs.getInt("member_id"));
				mp.setProjectName(rs.getString("project_name"));
				mp.setOwnership(rs.getString("ownership"));
				mp.setProInYear(rs.getDate("pro_in_year"));
				mp.setProOutYear(rs.getDate("pro_out_year"));
				mp.setOngoing(rs.getInt("ongoing"));
				mp.setResultWeb(rs.getString("result_web"));
				mp.setResultApple(rs.getString("result_apple"));
				mp.setResultGoggle(rs.getString("result_goggle"));
				mp.setTeam(rs.getString("team"));
				mp.setAbout(rs.getString("about"));
				list.add(mp);
				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if(con != null) try{con.close();}catch(SQLException ex){}
		}
	
		return list;
	} // END GetProject


	public void updateProject(MemberProject mp){
	
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {
	
			con = getConnection();
	
			sql = "update  member_project set project_name=?,ownership=?,pro_in_year=?,pro_out_year=?,ongoing=?, result_web=?, result_apple=?, result_goggle=?,team=?, about=? where id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mp.getProjectName());
			pstmt.setString(2, mp.getOwnership());
			pstmt.setDate(3, mp.getProInYear());
			pstmt.setDate(4, mp.getProOutYear());
			pstmt.setInt(5, mp.getOngoing());
			pstmt.setString(6, mp.getResultWeb());
			pstmt.setString(7, mp.getResultApple());
			pstmt.setString(8, mp.getResultGoggle());
			pstmt.setString(9, mp.getTeam());
			pstmt.setString(10, mp.getAbout());
			pstmt.setInt(11, mp.getId());
			pstmt.executeUpdate();
				
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	
	} // END Update Project


	public void deleteProject(int id) { // Delete project
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {
	
			con = getConnection();
	
			sql = "delete from member_project where id=?";
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
	
	
	} // END Delete





}
