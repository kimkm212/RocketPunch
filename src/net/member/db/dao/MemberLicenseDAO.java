package net.member.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commonDAO.DbConnector;
import net.member.db.dto.MemberLicense;

public class MemberLicenseDAO extends DbConnector{

	public void insertLicense(MemberLicense ml) { 
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
	
		try {

			con = getConnection();

			sql = "insert into member_license(member_id, license_name, license_agency, lic_get_date ,about) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ml.getMemberId());
			pstmt.setString(2, ml.getLicenseName());
			pstmt.setString(3, ml.getLicenseAgency());
			pstmt.setDate(4, ml.getLicGetDate());
			pstmt.setString(5, ml.getAbout());
			pstmt.executeUpdate();
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	
	}
	
	
	public List<MemberLicense> getLicense(int id) {
		List<MemberLicense> list = new ArrayList<MemberLicense>();

		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();

			sql = "select * from member_license where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberLicense ml = new MemberLicense();
				ml.setId(rs.getInt("id"));
				ml.setMemberId(rs.getInt("member_id"));
				ml.setLicenseName(rs.getString("license_name"));
				ml.setLicenseAgency(rs.getString("license_agency"));
				ml.setLicGetDate(rs.getDate("lic_get_date"));
				ml.setAbout(rs.getString("about"));
				list.add(ml);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return list;
	} 
	
	
	public void updateLicense(MemberLicense ml) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {

			con = getConnection();

			sql = "update member_license set license_name=?, license_agency=?, lic_get_date=?, about=? where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, ml.getLicenseName());
			pstmt.setString(2, ml.getLicenseAgency());
			pstmt.setDate(3, ml.getLicGetDate());
			pstmt.setString(4, ml.getAbout());
			pstmt.setInt(5, ml.getId());
			pstmt.executeUpdate();

				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}


	} 
	
	public void deleteLicense(int id) { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			con = getConnection();
			sql = "delete from member_license where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}


	} 
	
	
	
	
}
