package net.member.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import commonDAO.DbConnector;
import net.company.dto.CompanyBean;
import net.member.db.dto.MemberBean;
import net.member.db.dto.MemberFriend;

public class MemberFriendDAO extends DbConnector{


	@SuppressWarnings("resource")
	public int insertFriend(MemberFriend mf) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		int check = 0;
		try {
			con = getConnection();

			sql = "select * from member_friend where member_id1=? and member_id2=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mf.getMemberId1());
			pstmt.setInt(2, mf.getMemberId2());

			rs = pstmt.executeQuery();

			if (rs.next()==false) {
				sql = "insert into member_friend(member_id1,member_id2,relation) values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mf.getMemberId1());
				pstmt.setInt(2, mf.getMemberId2());
				pstmt.setString(3, mf.getRelation());
				pstmt.executeUpdate();
				check=1;
			} else{
				check=-1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return check;
	}

	//
	//


	@SuppressWarnings("resource")
	public int acceptFriend(MemberFriend mf) { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		int check = 0;
		
		try {

			con = getConnection();

			sql = "select * from member_friend where member_id2=? and member_id1=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mf.getMemberId1());
			pstmt.setInt(2, mf.getMemberId2());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Friend Exist

				sql = "update member_friend set relation=? where member_id2=? and member_id1=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mf.getRelation());
				pstmt.setInt(2, mf.getMemberId1());
				pstmt.setInt(3, mf.getMemberId2());
		
				pstmt.executeUpdate();

				check = 1;
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return check;
	} 

	//
	//


	@SuppressWarnings("resource")
	public int deleteFriend(MemberFriend mf) { 
		int check = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;

		try {

			con = getConnection();

			sql = "select * from member_friend where member_id1=? and member_id2=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mf.getMemberId1());
			pstmt.setInt(2, mf.getMemberId2());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Id Exist
				sql = "delete from member_friend where member_id1=? and member_id2=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mf.getMemberId1());
				pstmt.setInt(2, mf.getMemberId2());
				// Action
				pstmt.executeUpdate();

				check = 1;

			}

			sql = "select * from member_friend where member_id1=? and member_id2=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mf.getMemberId2());
			pstmt.setInt(2, mf.getMemberId1());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Id Exist
				sql = "delete from member_friend where member_id1=? and member_id2=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mf.getMemberId2());
				pstmt.setInt(2, mf.getMemberId1());
				// Action
				pstmt.executeUpdate();

				check = 1;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return check;

	} 

	//
	//

	
	//세션 아이디를 이용해 친구 목록을 불러온다
	public List<HashMap<String, Object>> getFriends(MemberFriend mf) {
		
		List<HashMap<String, Object>> list =new ArrayList<HashMap<String, Object>>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			
			//죽을뻔함....ㄹㅇ...
			sql = "select A.id, A.first_name_k, A.last_name_k, A.image_profile, A.c_role, B.c_nameK, B.c_nameE "
					+ "from member A "
					+ "inner join member_friend C "
					+ "on A.id = C.member_id1 or A.id = C.member_id2 "
					+ "left outer join company B "
					+ "on A.c_id = B.id "
					+ "where "
					+ "case "
					+ "when C.member_id1=A.id "
					+ "then C.member_id2=? "
					+ "when C.member_id2=A.id "
					+ "then C.member_id1=? "
					+ "end "
					+ "and C.relation=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mf.getMemberId1());
			pstmt.setInt(2, mf.getMemberId1());
			pstmt.setString(3, mf.getRelation());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HashMap<String, Object> mem = new HashMap<String, Object>();
				MemberBean mb = new MemberBean();
				
				mb.setId(rs.getInt("id"));
				mb.setFirstNameK(rs.getString("first_name_k"));
				mb.setLastNameK(rs.getString("last_name_k"));
				
				if(rs.getString("image_profile")==null){
					mb.setImageProfile("/imgs/def_pro.jpg");
				}else{
					mb.setImageProfile(rs.getString("image_profile"));
				}
				
				if(rs.getString("c_role")==null){
					mb.setCRole("");	
				}else{
					mb.setCRole(rs.getString("c_role"));
				}
				
				mem.put("mb", mb);// 맴버 정보 셋
				//
				
				CompanyBean cb = new CompanyBean();
				if(rs.getString("c_nameK")==null){
					cb.setCNamek("");	
				}else{
					cb.setCNamek(rs.getString("c_nameK"));
				}
				
				if(rs.getString("c_nameE")==null){
					cb.setCNamee("");	
				}else{
					cb.setCNamee(rs.getString("c_nameE"));
				}
				
				mem.put("cb", cb);// 회사정보 셋
				
				
				list.add(mem);
			}
					

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return list;
	} 

	//
}

