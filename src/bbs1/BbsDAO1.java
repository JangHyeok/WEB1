package bbs1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bbs.Bbs;

import java.util.ArrayList;
public class BbsDAO1 {

	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO1() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
			String dbID="root";
			String dbPassword="Dnpqwpwkr628";
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	} // �̹����� �����ϹǷν� �����ͺ��̽� ������ �Ҽ��ִ�. ������ ID/PASS Ȯ��
	
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
		return ""; //������ ���̽� ����
	}
	public int getNext() {
		String SQL="SELECT bbsID FROM BBS1 ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1)+1;
			}
			return 1; //ù��° �Խù��� ���
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1; //������ ���̽� ����
	}
	public int write(String bbsTitle, String userID, String bbsContent)
	{
		String SQL="INSERT INTO BBS1 VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext());
			pstmt.setString(2,bbsTitle);
			pstmt.setString(3,userID);
			pstmt.setString(4,getDate());
			pstmt.setString(5,bbsContent);
			pstmt.setInt(6,1);
			return pstmt.executeUpdate(); //ù��° �Խù��� ���
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1; //������ ���̽� ����
	}
	public ArrayList<Bbs1> getList(int pageNumber){
		String SQL="SELECT * FROM BBS1 WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs1> list = new ArrayList<Bbs1>();
		try {
			PreparedStatement pstmt =conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext()- (pageNumber-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Bbs1 bbs =new Bbs1();
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
		return list; //������ ���̽� ����
	}
	public boolean nextPage(int pageNumber)
	{
		String SQL="SELECT * FROM BBS1 WHERE bbsID < ? AND bbsAvailable = 1";
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
	public Bbs1 getBbs1(int bbsID) {
		String SQL = "SELECT * FROM BBS1 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Bbs1 bbs = new Bbs1();
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
		String SQL = "UPDATE BBS1 SET bbsTitle=?, bbsContent=? WHERE bbsID=?";
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
	//���� �Լ�
	public int delete(int bbsID) {
		String SQL = "UPDATE BBS1 SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);   
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // �����ͺ��̽� ����
	}
}