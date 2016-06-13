
package net.member.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commonDAO.DbConnector;
import net.member.db.dto.MemberAward;

public class MemberAwardDAO extends DbConnector{
	
	public void insertAward(MemberAward ma) { // Insert Award
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {
			con = getConnection();
			sql = "insert into member_award(member_id, award_type, award_name, award_agency, award_date) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ma.getMemberId());
			pstmt.setString(2, ma.getAwardType());
			pstmt.setString(3, ma.getAwardName());
			pstmt.setString(4, ma.getAwardAgency());
			pstmt.setDate(5, ma.getAwardDate());
	
			pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	
	}// END Insert award
	
	
	public List<MemberAward> getAward(int id) { // Get Award
		List<MemberAward> list = new ArrayList<MemberAward>();

		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();
			sql = "select * from member_award where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberAward ma = new MemberAward();
				ma.setId(rs.getInt("id"));
				ma.setMemberId(rs.getInt("member_id"));
				ma.setAwardType(rs.getString("award_type"));
				ma.setAwardName(rs.getString("award_name"));
				ma.setAwardAgency(rs.getString("award_agency"));
				ma.setAwardDate(rs.getDate("award_date"));
				list.add(ma);
			}

		} catch (Exception e) {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return list;
	} // END Get Award
	
	public void updateAward(MemberAward ma) { // Update Award

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {

			con = getConnection();

			sql = "update member_award set award_type=?,award_name=?,award_agency=?,award_date=? where id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ma.getAwardType());
			pstmt.setString(2, ma.getAwardName());
			pstmt.setString(3, ma.getAwardAgency());
			pstmt.setDate(4, ma.getAwardDate());
			pstmt.setInt(5, ma.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

	} // END Update award
	
	public void deleteAward(int id) { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;

		try {
			con = getConnection();
			sql = "delete from member_award where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
	
			// Action
			pstmt.executeUpdate();

				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}


	} // END Delete award
	
	
	
}
	

