package bbs3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bbs.Bbs;

import java.util.ArrayList;
public class BbsDAO3 {

	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO3() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
			String dbID="root";
			String dbPassword="Dnpqwpwkr628";
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	} // 이문장을 기입하므로써 데이터베이스 접속을 할수있다. 관리자 ID/PASS 확인
	
	public String getDate() {
		String SQL="SELECT NOW()";
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ""; //데이터 베이스 오류
	}
	public int getNext() {
		String SQL="SELECT bbsID FROM BBS3 ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1)+1;
			}
			return 1; //첫번째 게시물인 경우
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1; //데이터 베이스 오류
	}
	public int write(String bbsTitle, String userID, String bbsContent)
	{
		String SQL="INSERT INTO BBS3 VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext());
			pstmt.setString(2,bbsTitle);
			pstmt.setString(3,userID);
			pstmt.setString(4,getDate());
			pstmt.setString(5,bbsContent);
			pstmt.setInt(6,1);
			return pstmt.executeUpdate(); //첫번째 게시물인 경우
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1; //데이터 베이스 오류
	}
	public ArrayList<Bbs3> getList(int pageNumber){
		String SQL="SELECT * FROM BBS3 WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs3> list = new ArrayList<Bbs3>();
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext()- (pageNumber-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Bbs3 bbs =new Bbs3();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list; //데이터 베이스 오류
	}
	public boolean nextPage(int pageNumber)
	{
		String SQL="SELECT * FROM BBS3 WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext()- (pageNumber-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				return true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public Bbs3 getBbs3(int bbsID) {
		String SQL = "SELECT * FROM BBS3 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Bbs3 bbs = new Bbs3();
			bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int update(int bbsID, String bbsTItle, String bbsContent) {
		String SQL = "UPDATE BBS3 SET bbsTitle=?, bbsContent=? WHERE bbsID=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTItle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	//삭제 함수
	public int delete(int bbsID) {
		String SQL = "UPDATE BBS3 SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);   
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
}
