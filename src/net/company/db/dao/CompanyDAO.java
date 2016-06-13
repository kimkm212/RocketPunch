package net.company.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import commonDAO.DbConnector;
import net.company.dto.CompanyBean;


public class CompanyDAO extends DbConnector{
	
	//기업등록
		@SuppressWarnings("resource")
		public int insertCompany(CompanyBean cb, Integer sessionId) { // Insert company
			Connection con = null;
			PreparedStatement pstmt = null;
		
			String sql = "";
			ResultSet rs=null;

			int return_c_id = 0;

			try {
				con = getConnection();
				
				sql = "insert into company(c_nameK, c_nameE, c_email, c_date, c_part, c_role, "
						+ "c_members, c_address, c_homepage, c_tel, c_intro, c_introduce, c_birthday, c_logo, c_cover) "
						+ "values(?, ?, ?, now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, cb.getCNamek());
				pstmt.setString(2, cb.getCNamee());
				pstmt.setString(3, cb.getCEmail());
				pstmt.setString(4, cb.getCPart());
				pstmt.setString(5, cb.getCRole());
				pstmt.setInt(6, cb.getCMembers());
				pstmt.setString(7, cb.getCAddress());
				pstmt.setString(8, cb.getCHomepage());
				pstmt.setString(9, cb.getCTel());
				pstmt.setString(10, cb.getCIntro());
				pstmt.setString(11, cb.getCIntroduce());
				pstmt.setDate(12, cb.getCBirthday());
				pstmt.setString(13, cb.getCLogo());
				pstmt.setString(14, cb.getCCover());
			
				pstmt.executeUpdate();
				
				//AutoInc로 입력된 company_id값을 가져옴
				rs = pstmt.getGeneratedKeys();  
				rs.next();
				return_c_id = rs.getInt(1);
	
				//세션 맴버를 업데이트 함
				sql="update member set c_id=? where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, return_c_id);
				pstmt.setInt(2, sessionId);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			
			return return_c_id;
		}//기업등록 끝 insertCompany end

		
		//기업정보보기
		public CompanyBean getCompany(int id){
			
			CompanyBean cb = new CompanyBean();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "";
			ResultSet rs=null;
			
			try{
				con = getConnection();
		
				sql = "select * from company where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();

				if(rs.next()){
					String logo = rs.getString("c_logo");
					if(logo==null){
						logo="/imgs/1.jpg";
					}
					String cover = rs.getString("c_cover");
					if(cover==null){
						cover="/imgs/comBackground.jpg";
					}
					
					cb.setId(rs.getInt("id"));
					cb.setCNamek(rs.getString("c_nameK"));
					cb.setCNamee(rs.getString("c_nameE"));
					cb.setCTel(rs.getString("c_tel"));
					cb.setCAddress(rs.getString("c_address"));
					cb.setCHomepage(rs.getString("c_homepage"));
					cb.setCEmail(rs.getString("c_email"));
					cb.setCBirthday(rs.getDate("c_birthday"));
					cb.setCPart(rs.getString("c_part"));
					cb.setCLogo(logo);
					cb.setCCover(cover);
					cb.setCRole(rs.getString("c_role"));
					cb.setCMembers(rs.getInt("c_members"));
					cb.setCIntro(rs.getString("c_intro"));
					cb.setCIntroduce(rs.getString("c_introduce"));
					cb.setCDate(rs.getTimestamp("c_date"));
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			return cb;
		}
		
		
		//기업리스트
		public List<CompanyBean> getCompanyList(int startRow, int pageSize){
			
			List<CompanyBean> companyList = new ArrayList<CompanyBean>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "";
			ResultSet rs = null;
			
			try{
				con = getConnection();

				sql = "select * from company order by id limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1); 
				pstmt.setInt(2, pageSize);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					CompanyBean cb = new CompanyBean();
					
					cb.setId(rs.getInt("id"));
					cb.setCNamek(rs.getString("c_nameK"));
					cb.setCNamee(rs.getString("c_nameE"));
					cb.setCTel(rs.getString("c_tel"));
					cb.setCAddress(rs.getString("c_address"));
					cb.setCHomepage(rs.getString("c_homepage"));
					cb.setCEmail(rs.getString("c_email"));
					cb.setCBirthday(rs.getDate("c_birthday"));
					cb.setCPart(rs.getString("c_part"));
					cb.setCLogo(rs.getString("c_logo"));
					cb.setCCover(rs.getString("c_cover"));
					cb.setCRole(rs.getString("c_role"));
					cb.setCMembers(rs.getInt("c_members"));
					cb.setCIntro(rs.getString("c_intro"));
					cb.setCIntroduce(rs.getString("c_introduce"));
					cb.setCDate(rs.getTimestamp("c_date"));
					
					companyList.add(cb);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(con!=null) try{con.close();}catch(SQLException ex){}
			}
			
			return companyList;
			
		}
		
		
		
	//등록한 기업 수정
	public void updateCompany(CompanyBean cb) { 
	
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();
			
			sql = "update company set "
					+ "c_nameK=?, c_nameE=?, c_email=?, c_part=?, c_members=?, "
					+ "c_address=?, c_homepage=?, c_tel=?, c_intro=?, c_introduce=?, "
					+ "c_birthday=?, c_logo=?, c_cover=? "
					+ "where id=?";
				
			pstmt = con.prepareStatement(sql);
	
			pstmt.setString(1, cb.getCNamek());
			pstmt.setString(2, cb.getCNamee());
			pstmt.setString(3, cb.getCEmail());
			pstmt.setString(4, cb.getCPart());
			pstmt.setInt(5, cb.getCMembers());
			pstmt.setString(6, cb.getCAddress());
			pstmt.setString(7, cb.getCHomepage());
			pstmt.setString(8, cb.getCTel());
			pstmt.setString(9, cb.getCIntro());
			pstmt.setString(10, cb.getCIntroduce());
			pstmt.setDate(11, cb.getCBirthday());
			pstmt.setString(12, cb.getCLogo());
			pstmt.setString(13, cb.getCCover());
			pstmt.setInt(14, cb.getId());
	
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}

	}//등록한 기업 수정 끝 updateCompany end

	//기업삭제
	public void deleteCompany(int id) { 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();

			sql = "delete from company where id=?";
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
		
	}//기업삭제 

}
