package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DB.DBClose;
import DB.DBConnection;
import dto.empDto;

public class empDao {
	Scanner sc = new Scanner(System.in);
	private static empDao dao = new empDao();
	
	private empDao() {
		DBConnection.initConnection();
	}
	
	public static empDao getInstance() {
		return dao;
	}
	
	public boolean addMember() {
		String sql = " INSERT INTO MYEMPLOYEES "
				+ " (SEQ, NAME, PHONENUM, EMAIL, SAL) "
				+ " VALUES( SEQ_MYEMPLOYEES.NEXTVAL, ?, ?, ?, ?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		System.out.println("이름을 입력하세요: ");
		String name = sc.next();
		System.out.println("연락처를 입력하세요: ");
		String phonenum = sc.next();
		System.out.println("이메일을 입력하세요: ");
		String email = sc.next();
		System.out.println("급여를 입력하세요: ");
		String sal = sc.next();
		
		conn = DBConnection.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, phonenum);
			psmt.setString(3, email);
			psmt.setString(4, sal);
			
			count = psmt.executeUpdate();
			System.out.println("사원추가 성공");
		} catch (SQLException e) {
			System.out.println("addMember fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);	
			
		}
		
		return count>0?true:false;
		
	}
	
	
	 public boolean deleteMember() {
		 String sql = " DELETE FROM MYEMPLOYEES "
					+ " WHERE SEQ=? ";
		 
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		System.out.println("삭제할 사원의 번호를 입력하세요: ");
		int seq = sc.nextInt();
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);

			count = psmt.executeUpdate();
			
			System.out.println("사원정보를 삭제하였습니다.");
		} catch (SQLException e) {
			System.out.println("deleteMember fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
		
	 }
	 public String detailInfo() {
		 
			
		 System.out.println("검색할 사원의 이름: ");
		 String name = sc.next();
		 
		 String sql = " SELECT SEQ, NAME, PHONENUM, EMAIL, SAL "
			 		+ " FROM MYEMPLOYEES "
			 		+ " WHERE NAME = ? ";
		 
		 Connection conn = null;
		  PreparedStatement psmt = null;
		  ResultSet rs = null;
		  
		  empDto dto = null;
		  
		  try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				 psmt.setString(1, name);
				 rs= psmt.executeQuery();

				 while (rs.next()) {
					 dto = new empDto(
							 			rs.getInt(1),
							 			rs.getString(2),
							 			rs.getString(3),
							 			rs.getString(4),
							 			rs.getString(5)
							 		
							 ); 
					 System.out.println(dto.toString());
				 }

			} catch (SQLException e) {
				 System.out.println("getMemberDetail fail"); 
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			  return dto.toString();
		 
	 }
	
	 public boolean updateMember() {
		 String sql = " UPDATE MYEMPLOYEES "
		 		+ " SET NAME=?, PHONENUM=?, EMAIL=?, SAL=? "
		 		+ " WHERE SEQ=? ";
		 
		 System.out.println("정보를 수정할 사원번호: ");
		 int seq = sc.nextInt();
		 System.out.println("수정할 이름: ");
		 String name = sc.next();
		 System.out.println("수정할 연락처: ");
		 String phonenum = sc.next();
		 System.out.println("수정할 이메일: ");
		 String email = sc.next();
		 System.out.println("수정할 급여: ");
		 String sal = sc.next();
		 
		 Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
	
			try {
				conn = DBConnection.getConnection();
		
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, name);
				psmt.setString(2, phonenum);
				psmt.setString(3, email);
				psmt.setString(4, sal);
				psmt.setInt(5, seq);
	
				count = psmt.executeUpdate();
				System.out.println("사원정보를 수정하였습니다.");
			} catch (SQLException e) {
				System.out.println("updateMember fail");

				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);
			}
			return count>0?true:false; 
		 
	 }
	
	public String getAllMemberList() {
		 String sql = " SELECT SEQ, NAME, PHONENUM, EMAIL, SAL "
			  		+ " FROM MYEMPLOYEES ";
		 List<empDto>list = new ArrayList<empDto>();
		 	Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();

				while (rs.next()) {
					empDto dto = new empDto(
												rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5)
												);
					list.add(dto);
					
				}
				for (empDto empDto : list) {
					System.out.println(empDto.toString());
				}
			
			} catch (SQLException e) {
				System.out.println("getAllMemberList fail");

				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			return list.toString();
		 
		 
	}
	
	
	
}
