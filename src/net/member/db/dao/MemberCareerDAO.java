package net.member.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commonDAO.DbConnector;
import net.member.db.dto.MemberCareer;

public class MemberCareerDAO extends DbConnector{
	
	public void insertCareer(MemberCareer mc) { // Insert Career
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {

			con = getConnection();

			sql = "insert into member_career(member_id, company, role, com_in_date, com_out_date, in_company, about) values(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mc.getMemberId());
			pstmt.setString(2, mc.getCompany());
			pstmt.setString(3, mc.getRole());
			pstmt.setDate(4, mc.getComInDate());
			pstmt.setDate(5, mc.getComOutDate());
			pstmt.setInt(6, mc.getInCompany());
			pstmt.setString(7, mc.getAbout());
			pstmt.executeUpdate();
	

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

	}// END Insert Career
	
	public List<MemberCareer> getCareer(int id) { // Get Career
		
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberCareer> list = new ArrayList<MemberCareer>();
		
		try {
			con = getConnection();

			sql = "select * from member_career where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberCareer mc = new MemberCareer();
				mc.setId(rs.getInt("id"));
				mc.setMemberId(rs.getInt("member_id"));
				mc.setCompany(rs.getString("company"));
				mc.setRole(rs.getString("role"));
				mc.setComInDate(rs.getDate("com_in_date"));
				mc.setComOutDate(rs.getDate("com_out_date"));
				mc.setInCompany(rs.getInt("in_company"));
				mc.setAbout(rs.getString("about"));
				list.add(mc);
			}

		} catch (Exception e) {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return list;
	} // END Get MemberAward
	
	
	public void updateCareer(MemberCareer mc) { // Update Career

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {

			con = getConnection();

			sql = "update member_award set company=?,role=?,com_in_date=?,com_out_date=?,in_company=?,about=? where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mc.getCompany());
			pstmt.setString(2, mc.getRole());
			pstmt.setDate(3, mc.getComInDate());
			pstmt.setDate(4, mc.getComOutDate());
			pstmt.setInt(5, mc.getInCompany());
			pstmt.setString(6, mc.getAbout());
			pstmt.setInt(7, mc.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

	} // END Update Career
	
	public void deleteCareer(int id) { // Delete Career

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {

			con = getConnection();

			sql = "delete from member_career where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			// Action
			pstmt.executeUpdate();

					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

	} // END Delete Career
	
}
