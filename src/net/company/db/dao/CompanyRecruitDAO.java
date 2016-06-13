package net.company.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import commonDAO.DbConnector;
import net.company.dto.CompanyApplyBean;
import net.company.dto.CompanyBean;
import net.company.dto.CompanyRecruitBean;
import net.member.db.dto.MemberBean;


public class CompanyRecruitDAO extends DbConnector{

	//채용정보등록
	public void insertRecruit(CompanyRecruitBean crb) { //insertJob
	
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs=null;

		try {
			con = getConnection();
			
			sql = "insert into company_recruit(company_id, c_title, c_job_date, c_job_date_start, c_job_date_end, "
					+ "c_field, c_career, c_task, c_locations, c_salary, c_tel_question, c_job_content) "
					+ "values(?,?,now(),?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, crb.getCompanyId());
			pstmt.setString(2, crb.getCTitle());
			pstmt.setDate(3, crb.getCJobDateStart());
			pstmt.setDate(4, crb.getCJobDateEnd());
			pstmt.setString(5, crb.getCField());
			pstmt.setString(6, crb.getCCareer());
			pstmt.setString(7, crb.getCTask());
			pstmt.setString(8, crb.getCLocations());
			pstmt.setString(9, crb.getCSalary());
			pstmt.setString(10, crb.getCTelQuestion());
			pstmt.setString(11, crb.getCJobContent());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	}//채용등록 끝 insertCompany end
	
		
	
	//채용리스트
	public List<HashMap<String, Object>> getRecruitList(int startpage, int numberOf) {
	
		List<HashMap<String, Object>> recruitList = new ArrayList<HashMap<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		try {
			con=getConnection();
			sql="select A.id, A.c_title, A.c_job_date_end, A.c_field, A.c_career, A.c_salary, "
					+ "B.c_nameK, B.c_nameE, B.c_intro, B.c_logo "
					+ "from company_recruit A "
					+ "left outer join company B "
					+ "on A.company_id = B.id "
					+ "order by A.id desc "
					+ "limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, (startpage-1)*10);
			pstmt.setInt(2, numberOf);

			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				HashMap<String, Object> mem = new HashMap<String, Object>();
				
				CompanyRecruitBean crb = new CompanyRecruitBean();
				crb.setId(rs.getInt("id"));
				crb.setCTitle(rs.getString("c_title"));
				crb.setCJobDateEnd(rs.getDate("c_job_date_end"));
				crb.setCField(rs.getString("c_field"));
				crb.setCCareer(rs.getString("c_career"));
				crb.setCSalary(rs.getString("c_salary"));
				mem.put("crb", crb);
				
				CompanyBean cb = new CompanyBean();
				cb.setCNamek(rs.getString("c_nameK"));
				cb.setCNamee(rs.getString("c_nameE"));
				cb.setCIntro(rs.getString("c_intro"));
				cb.setCLogo(rs.getString("c_logo"));
				mem.put("cb", cb);
				
				recruitList.add(mem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return recruitList;
	}
	
	
	//전체 내 회사 채용정보 관리 및 지원자 보기
	public List<HashMap<String, Object>> getRecruitListAdmin(int c_id) {
		
		List<HashMap<String, Object>> recruitList = new ArrayList<HashMap<String, Object>>();
		
		Connection con = null;
		
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql = "";
		String sql2 = "";
		try {
			
			con=getConnection();
			sql="select A.id, A.c_title, A.c_job_date_end, A.c_field, A.c_career, A.c_salary, "
					+ "B.id, B.c_nameK, B.c_nameE, B.c_intro, B.c_logo "
					+ "from company_recruit A "
					+ "left outer join company B "
					+ "on A.company_id = B.id "
					+ "where B.id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, c_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				//하나의 채용정보에 대한 모든 정보가 mem에 담김
				HashMap<String, Object> mem = new HashMap<String, Object>();
				
				CompanyRecruitBean crb = new CompanyRecruitBean();
				crb.setId(rs.getInt("id"));
				crb.setCTitle(rs.getString("c_title"));
				crb.setCJobDateEnd(rs.getDate("c_job_date_end"));
				crb.setCField(rs.getString("c_field"));
				crb.setCCareer(rs.getString("c_career"));
				crb.setCSalary(rs.getString("c_salary"));
				mem.put("crb", crb);
				
				CompanyBean cb = new CompanyBean();
				cb.setId(rs.getInt("B.id"));
				cb.setCNamek(rs.getString("c_nameK"));
				cb.setCNamee(rs.getString("c_nameE"));
				cb.setCIntro(rs.getString("c_intro"));
				cb.setCLogo(rs.getString("c_logo"));
				mem.put("cb", cb);
				
				//불러온 recruiId로 지원자 정보를 불러 온다.
				List<HashMap<String, Object>> applyers = new ArrayList<HashMap<String, Object>>();
				int recruit_id = rs.getInt("id");
				sql2 = "select A.id, A.first_name_k, A.last_name_k, A.image_profile, "
						+ "B.id, B.po_url, B.po_url_file, B.money, B.tel, B.content "
						+ "from member A "
						+ "inner join company_apply B "
						+ "on A.id=B.member_id "
						+ "where B.recruit_id=?";
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setInt(1, recruit_id);
				rs2=pstmt2.executeQuery();
				
				while(rs2.next()){
					HashMap<String, Object> applyer = new HashMap<String, Object>();
					MemberBean mb = new MemberBean();
					mb.setId(rs2.getInt("A.id"));
					mb.setFirstNameK(rs2.getString("first_name_k"));
					mb.setLastNameK(rs2.getString("last_name_k"));
					
					if(rs2.getString("image_profile")==null){
						mb.setImageProfile("/imgs/def_pro.jpg");
					}else{
						mb.setImageProfile(rs2.getString("image_profile"));
					}
				
					applyer.put("mb", mb);
					
					CompanyApplyBean cab = new CompanyApplyBean();
					cab.setId(rs2.getInt("B.id"));
					cab.setPoUrl(rs2.getString("po_url"));
					cab.setPoUrlFile(rs2.getString("po_url_file"));
					cab.setMoney(rs2.getString("money"));
					cab.setTel(rs2.getString("tel"));
					cab.setContent(rs2.getString("content"));
					applyer.put("cab", cab);
					
					applyers.add(applyer);
				}
				mem.put("applyers", applyers);
				
				recruitList.add(mem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(rs2!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(pstmt2!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return recruitList;
	}
	
	//회사정보 내 채용정보 뿌리기
	public List<CompanyRecruitBean> getRecruitListInCompany(int company_id) {
		
		List<CompanyRecruitBean> recruitList = new ArrayList<CompanyRecruitBean>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "";
		try {
			con=getConnection();
			sql="select id, c_title, c_job_date_end, c_field, c_career, c_salary "
					+ "from company_recruit "
					+ "where company_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, company_id);

			rs=pstmt.executeQuery();
			while(rs.next()){
							
				CompanyRecruitBean crb = new CompanyRecruitBean();
				crb.setId(rs.getInt("id"));
				crb.setCTitle(rs.getString("c_title"));
				crb.setCJobDateEnd(rs.getDate("c_job_date_end"));
				crb.setCField(rs.getString("c_field"));
				crb.setCCareer(rs.getString("c_career"));
				crb.setCSalary(rs.getString("c_salary"));
			
				recruitList.add(crb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return recruitList;
	}
	
	
	
	
	
	//채용공고상세보기
	public HashMap<String, Object> getRecruitContent(int id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {
			con=getConnection();
			sql="select * "
				+ "from company_recruit A "
				+ "join company B "
				+ "on A.company_id = B.id "
				+ "where A.id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				CompanyRecruitBean crb =new CompanyRecruitBean();
				crb.setId(rs.getInt("id"));
				crb.setCompanyId(rs.getInt("company_id"));
				crb.setCTitle(rs.getString("c_title"));
				crb.setCJobDate(rs.getTimestamp("c_job_date"));
				crb.setCJobDateStart(rs.getDate("c_job_date_start"));
				crb.setCJobDateEnd(rs.getDate("c_job_date_end"));
				crb.setCField(rs.getString("c_field"));
				crb.setCCareer(rs.getString("c_career"));
				crb.setCTask(rs.getString("c_task"));
				crb.setCLocations(rs.getString("c_locations"));
				crb.setCSalary(rs.getString("c_salary"));
				crb.setCTelQuestion(rs.getString("c_tel_question"));
				crb.setCJobContent(rs.getString("c_job_content"));
				result.put("crb", crb); //채용정보 셋
				
				CompanyBean cb = new CompanyBean();
				cb.setId(rs.getInt("B.id"));
				cb.setCNamek(rs.getString("c_nameK"));
				cb.setCNamee(rs.getString("c_nameE"));
				cb.setCLogo(rs.getString("c_logo"));
				cb.setCCover(rs.getString("c_cover"));
				cb.setCIntroduce(rs.getString("c_introduce"));
				result.put("cb", cb);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return result;
	}
	
	
	
	
	
	//등록한 기업 수정
	public void updateRecruit(CompanyRecruitBean crb) { 
	
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();

			sql = "update company_recruit set "  	
				+ "c_title=?, c_job_date_start=?, c_job_date_end=?, "
				+ "c_field=?, c_career=?, c_task=?, c_locations=?, c_salary=?, c_tel_question=?, c_job_content=? "
				+ "where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, crb.getCTitle());
			pstmt.setDate(2, crb.getCJobDateStart());
			pstmt.setDate(3, crb.getCJobDateEnd());
			pstmt.setString(4, crb.getCField());
			pstmt.setString(5, crb.getCCareer());
			pstmt.setString(6, crb.getCTask());
			pstmt.setString(7, crb.getCLocations());
			pstmt.setString(8, crb.getCSalary());
			pstmt.setString(9, crb.getCTelQuestion());
			pstmt.setString(10, crb.getCJobContent());
			pstmt.setInt(11, crb.getId());

			pstmt.executeUpdate();

		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

	}//등록한 기업 수정 끝 updateCompany end
	

	//채용공고삭제
	public void deleteRecruit(int id) { // Delete Member
	
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();
			sql = "delete from company_recruit where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
	
			pstmt.executeUpdate();

				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
	
	}//채용공고삭제  end
	
}
