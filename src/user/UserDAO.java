package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
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
	public int login(String userID,String userPassword)
	{
		String SQL="SELECT userPassword FROM USER WHERE userID=?";
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setNString(1, userID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getNString(1).equals(userPassword)) 
					return 1;
				else
					return 0;//비밀번호 불일치 
			}
			return -1;//아이디가 없음.
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터 베이스 오류를 의미함.
	}
	
	public int join(User user) {
		String SQL="INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,user.getUserID());
			pstmt.setString(2,user.getUserPassword());
			pstmt.setString(3,user.getUserName());
			pstmt.setString(4,user.getUserGender());
			pstmt.setString(5,user.getUserEmail());
			return pstmt.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류
	}
	
}
