package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.bean.MemberDTO;



public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	private static MemberDAO instance;
	
	public static MemberDAO getInstance() {
		if(instance == null) { 
			synchronized (MemberDAO.class) {
				instance = new MemberDAO(); 
			}
		}
		return instance;
	}
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");//Tomcat의 경우
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void memberWrite(MemberDTO memberDTO) {
		String sql ="insert into member(name,id,pwd,gender,email1,email2,tel1,tel2,tel3,zipcode,addr1,addr2,logtime) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberDTO.getName());
			pstmt.setString(2,memberDTO.getId());
			pstmt.setString(3,memberDTO.getPwd());
			pstmt.setString(4,memberDTO.getGender());
			pstmt.setString(5,memberDTO.getEmail1());
			pstmt.setString(6,memberDTO.getEmail2());
			pstmt.setString(7,memberDTO.getTel1());
			pstmt.setString(8,memberDTO.getTel2());
			pstmt.setString(9,memberDTO.getTel3());
			pstmt.setString(10,memberDTO.getZipcode());
			pstmt.setString(11,memberDTO.getAddr1());
			pstmt.setString(12,memberDTO.getAddr2());
			
			pstmt.executeUpdate();//실행
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
            	if (pstmt != null) pstmt.close();
            	if(conn != null) conn.close();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public boolean memberCheckLogin(String id, String pwd) {
		boolean check = false;
		String sql = "select * from member where id=? and pwd=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			} else {
				check = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
            	if(pstmt != null) pstmt.close();
            	if(conn != null) conn.close();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
		return check;
	}
	
	public boolean memberCheckId(String id) {
		boolean check = false;
		String sql = "select * from member where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			} else {
				check = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
            	if(pstmt != null) pstmt.close();
            	if(conn != null) conn.close();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
		return check;
	}
	
}
