package pj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class guestDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<guest> getAll() throws Exception {
		Connection conn = open();
		List<guest> guestList = new ArrayList<>();
		
		String sql = "select aid, user, email, FORMATDATETIME(date,'yyyy-MM-dd') as cdate, title from guest order by aid desc";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				guest n = new guest();
				n.setAid(rs.getInt("aid"));
				n.setUser(rs.getString("user"));
				n.setEmail(rs.getString("email"));
				n.setDate(rs.getString("cdate"));
				n.setTitle(rs.getString("title"));
				guestList.add(n);
			}
			return guestList;
		}
	}
	
	public guest getGuest(int aid) throws SQLException {
		Connection conn = open();
		guest n = new guest();
		String sql = "select aid, user, email, title, pw,FORMATDATETIME(date,'yyyy-MM-dd') as cdate, content from guest where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		try(conn; pstmt; rs){
			n.setAid(rs.getInt("aid"));
			n.setUser(rs.getString("user"));
			n.setEmail(rs.getString("email"));
			n.setTitle(rs.getString("title"));
			n.setPw(rs.getString("pw"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			pstmt.executeQuery();
			return n;
		}
	}
	
	public void addGuest(guest n) throws Exception {
		Connection conn = open();
		String sql = "insert into guest(user, email, title, pw, date, content) values(?,?,?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, n.getUser());
			pstmt.setString(2, n.getEmail());
			pstmt.setString(3, n.getTitle());
			pstmt.setString(4, n.getPw());
			pstmt.setString(5, n.getContent());
			
			pstmt.executeUpdate();
		}
	}
	
	//update
	public void updateGuest(guest n) throws Exception {
		Connection conn = open();
		String sql = "Update guest set user=?, email=?, title=?, pw=?, content=? Where aid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, n.getUser());
			pstmt.setString(2, n.getEmail());
			pstmt.setString(3, n.getTitle());
			pstmt.setString(4, n.getPw());
			pstmt.setString(5, n.getContent());
			pstmt.setInt(6, n.getAid());
			
			pstmt.executeUpdate();
		}
	}
	
	public void delGuest(int aid) throws SQLException {
		Connection conn = open();
		String sql =  "delete from guest where aid =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
			pstmt.setInt(1, aid);
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
}
