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

public class MemberDAO extends DbConnector{

	public void insertMember(MemberBean mb) { // Insert Member
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {

			con = getConnection();

			sql = "insert into member(first_name_k,last_name_k,email,pwd,m_date) values(?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getFirstNameK());
			pstmt.setString(2, mb.getLastNameK());
			pstmt.setString(3, mb.getEmail());
			pstmt.setString(4, mb.getPwd());
			
			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	}// END Insert Member

	//
	//
	//
	//
	//
	//
	//회사의 구성원으로 등록하기
	public int memberJoinCompany(int m_id, int c_id){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int check = 0;
		
		try {

			con = getConnection();
			sql = "update member set c_id=? "
					+ "where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, c_id);
			pstmt.setInt(2, m_id);
			
			pstmt.executeUpdate();
			
			check = 1;

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		
		return check;
		
	}
	
	
	
	
	//단일 맴버 정보를 가져올때 세션 id를 가지고 대상 아이디와의 친구 관계를 판별해야 한다.
	
	//세션이 없을 경우 그냥 가져 가야함
	public HashMap<String, Object> getMember(int id) { // Get Member
		
		HashMap<String, Object> result = new HashMap<String, Object>();
				
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();

			sql = "select * "
					+ "from member A "
					+ "left outer join company B "
					+ "on A.c_id = B.id "
					+ "where A.id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//맴버 정보 입력
				MemberBean mb = new MemberBean();
				mb.setId(rs.getInt("id"));
				mb.setFirstNameK(rs.getString("first_name_k"));
				mb.setLastNameK(rs.getString("last_name_k"));
				mb.setEmail(rs.getString("email"));
				mb.setPwd(rs.getString("pwd"));
				mb.setExperise(rs.getString("experise"));
				mb.setLookForAJob(rs.getInt("look_for_a_job"));
				mb.setCId(rs.getInt("c_id"));
		
				if(rs.getString("image_cover")==null){
					mb.setImageCover("/imgs/def_cover.jpg");
				}else{
					mb.setImageCover(rs.getString("image_cover"));
				}
				
				if(rs.getString("image_profile")==null){
					mb.setImageProfile("/imgs/def_pro.jpg");
				}else{
					mb.setImageProfile(rs.getString("image_profile"));
				}
				
				if(rs.getString("self_intro")==null){
					mb.setSelfIntro("");
				}else{
					mb.setSelfIntro(rs.getString("self_intro"));
				}
				
				if(rs.getString("c_role")==null){
					mb.setCRole("");
				}else{
					mb.setCRole(rs.getString("c_role"));
				}
				
				result.put("mb", mb);
				
				//회사 정보 입력
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
				
				result.put("cb", cb);// 회사정보 셋
				
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return result;
	} // END Get Member
	
	
	//세션 아이디가 있을경우 친구관계 정보를 같이 조회해온다
	public HashMap<String, Object> getMember(int id, int sessionId) { // Get Member
		
		HashMap<String, Object> result = new HashMap<String, Object>();
				
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();

			sql = "select A.id, A.first_name_k, A.last_name_k, A.email, A.pwd, A.self_intro, A.image_cover, "
					+ "A.image_profile, A.experise, A.look_for_a_job, A.c_id, A.c_role, B.c_namek, B.c_nameE, "
					+ "(select relation from member_friend where member_id1=? and member_id2=A.id) from_to, "
					+ "(select relation from member_friend where member_id2=? and member_id1=A.id) to_from "
					+ "from member A "
					+ "left outer join company B "
					+ "on A.c_id = B.id "
					+ "where A.id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sessionId);
			pstmt.setInt(2, sessionId);
			pstmt.setInt(3, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//맴버 정보 입력
				MemberBean mb = new MemberBean();
				mb.setId(rs.getInt("id"));
				mb.setFirstNameK(rs.getString("first_name_k"));
				mb.setLastNameK(rs.getString("last_name_k"));
				mb.setEmail(rs.getString("email"));
				mb.setPwd(rs.getString("pwd"));
				mb.setExperise(rs.getString("experise"));
				mb.setLookForAJob(rs.getInt("look_for_a_job"));
				mb.setCId(rs.getInt("c_id"));
				
				if(rs.getString("image_cover")==null){
					mb.setImageCover("/imgs/def_cover.jpg");
				}else{
					mb.setImageCover(rs.getString("image_cover"));
				}
				
				if(rs.getString("image_profile")==null){
					mb.setImageProfile("/imgs/def_pro.jpg");
				}else{
					mb.setImageProfile(rs.getString("image_profile"));
				}
				
				if(rs.getString("self_intro")==null){
					mb.setSelfIntro("");
				}else{
					mb.setSelfIntro(rs.getString("self_intro"));
				}
				
				if(rs.getString("c_role")==null){
					mb.setCRole("");
				}else{
					mb.setCRole(rs.getString("c_role"));
				}
				
				result.put("mb", mb);
				
				//회사 정보 입력
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
				result.put("cb", cb);// 회사정보 셋
				
				Integer from_to;
				if(rs.getString("from_to")==null){
					from_to=-1;
				}else{
					from_to = Integer.parseInt(rs.getString("from_to"));
				}
				result.put("from_to", from_to);//친구정보 셋
				
				Integer to_from;
				if(rs.getString("to_from")==null){
					to_from=-1;
				}else{
					to_from = Integer.parseInt(rs.getString("to_from"));
				}
				
				result.put("to_from", to_from);//친구정보 셋
				
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return result;
	} // END Get Member
	
	
	
	
	//getMember 메소드를 오버로딩 하여 이메일로 멤버 정보를 가져옴 
	public MemberBean getMember(String email) { 
		MemberBean mb = new MemberBean();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = getConnection();

			sql = "select * from member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mb.setId(rs.getInt("id"));
				mb.setFirstNameK(rs.getString("first_name_k"));
				mb.setLastNameK(rs.getString("last_name_k"));
				mb.setImageProfile(rs.getString("image_profile"));
				mb.setCId(rs.getInt("c_id"));
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return mb;
	} // END Get Member

	public void updateMember(MemberBean mb) { // Update Member

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {

			con = getConnection();

			sql = "update member set first_name_k=?, last_name_k=?, pwd=?, "
					+ "self_intro=?, image_cover=?, image_profile=?, experise=?, look_for_a_job=?, c_role=? where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mb.getFirstNameK());
			pstmt.setString(2, mb.getLastNameK());
			pstmt.setString(3, mb.getPwd());
			pstmt.setString(4, mb.getSelfIntro());
			pstmt.setString(5, mb.getImageCover());
			pstmt.setString(6, mb.getImageProfile());
			pstmt.setString(7, mb.getExperise());
			pstmt.setInt(8, mb.getLookForAJob());
			pstmt.setString(9, mb.getCRole());
			pstmt.setInt(10, mb.getId());
			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

	} // END Update Member

	//
	//
	//
	//
	//
	//

	
	public int userCheck(String email, String pwd) { //User Check
		int check = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();
			
			sql = "select pwd from member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// Id Exist
				if (pwd.equals(rs.getString("pwd"))) {
					// Pwd Correct
					check = 1;
				} else {
					// Pwd Wrong
					check = 0;
				}
			} else {
				// Id Wrong
				check = -1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return check;
	}// END User Check
	//
	//
	//
	//
	
	//
	
	//기본적으로 멤버리스트는 세션 아이디를 파라미터로 받고 세션 id가 null일 경우 member테이블만 조회 조회
	// 세션id가 있을경우 member_friend 테이블을 조인해 친구정보를 같이 가져와야 한다.
	// 모든 멤버리스트 조회는 같은 방식으로
	
	//메인 화면 사람 리스트에 뿌려주기 위한 메소드 
	//기본적으로 가입일 순으로 20개의 멤버리스트를 가지고 온다.
	
	//세션이 있을경우 세션정보를 파라미터로 받는다
	public List<HashMap<String, Object>> getMemberList(int page, int numberOf, int sessionId) { 
		
		List<HashMap<String, Object>> list =new ArrayList<HashMap<String, Object>>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			sql = "select A.id, A.first_name_k, A.last_name_k, A.image_profile, A.c_role, B.c_nameK, B.c_nameE, "
					+ "(select relation from member_friend where member_id1=? and member_id2=A.id) from_to, "
					+ "(select relation from member_friend where member_id2=? and member_id1=A.id) to_from "
					+ "from "
					+ "member A left outer join company B "
					+ "on A.c_id = B.id "
					+ "order by A.m_date asc limit ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sessionId);
			pstmt.setInt(2, sessionId);
			pstmt.setInt(3, (page-1)*10);
			pstmt.setInt(4, numberOf);
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
				
				Integer from_to;
				if(rs.getString("from_to")==null){
					from_to=-1;
				}else{
					from_to = Integer.parseInt(rs.getString("from_to"));
				}
				mem.put("from_to", from_to);//친구정보 셋
				
				Integer to_from;
				if(rs.getString("to_from")==null){
					to_from=-1;
				}else{
					to_from = Integer.parseInt(rs.getString("to_from"));
				}
				
				mem.put("to_from", to_from);//친구정보 셋
				
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
	
	
	
	//세션정보가 없을경우는 그냥 가져옴
	public List<HashMap<String, Object>> getMemberList(int page, int numberOf) { 
		
		List<HashMap<String, Object>> list =new ArrayList<HashMap<String, Object>>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			sql = "select A.id, A.first_name_k, A.last_name_k, A.image_profile, A.c_role, B.c_nameK, B.c_nameE "
					+ "from member A left outer join company B "
					+ "on A.c_id = B.id "
					+ "order by A.m_date asc limit ?,?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*10);
			pstmt.setInt(2, numberOf);
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
	
	
	
	
	//-----------------------회사 정보내 맴버 리스트 조회---------------------------
	//세션 없을 경우
	public List<HashMap<String, Object>> getMemberListInCompany(int c_id) { 
		
		List<HashMap<String, Object>> list =new ArrayList<HashMap<String, Object>>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			sql = "select id, first_name_k, last_name_k, image_profile, c_role "
					+ "from member "
					+ "where c_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, c_id);
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
				mem.put("mb", mb);
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
	
	//세션이 있을경우 세션정보를 파라미터로 받는다
	public List<HashMap<String, Object>> getMemberListInCompany(int c_id, int sessionId) { 
		
		List<HashMap<String, Object>> list =new ArrayList<HashMap<String, Object>>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			sql = "select id, first_name_k, last_name_k, image_profile, c_role, "
					+ "(select relation from member_friend where member_id1=? and member_id2=id) from_to, "
					+ "(select relation from member_friend where member_id2=? and member_id1=id) to_from "
					+ "from member "
					+ "where c_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sessionId);
			pstmt.setInt(2, sessionId);
			pstmt.setInt(3, c_id);

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
				
				Integer from_to;
				if(rs.getString("from_to")==null){
					from_to=-1;
				}else{
					from_to = Integer.parseInt(rs.getString("from_to"));
				}
				mem.put("from_to", from_to);//친구정보 셋
				
				Integer to_from;
				if(rs.getString("to_from")==null){
					to_from=-1;
				}else{
					to_from = Integer.parseInt(rs.getString("to_from"));
				}
				
				mem.put("to_from", to_from);//친구정보 셋
				
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
	
	


	@SuppressWarnings("resource")
	public int deleteMember(Integer sessionId, String pwd) { // Delete Member
		int check = -1;

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;

		try {

			con = getConnection();

			sql = "select pwd from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sessionId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				if (pwd.equals(rs.getString("pwd"))) {

					sql = "delete from member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, sessionId);
		
					pstmt.executeUpdate();

					check = 1;
				} else {
				
					check = 0;
				}
			} else {
				// email Wrong
				check = -1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

		return check;

	} // END Delete Member
	

}// END MemberDAO
