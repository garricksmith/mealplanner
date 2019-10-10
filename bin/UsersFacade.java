import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.*;
import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersFacade {
	private static UsersFacade singleton;
	
	private DataAccess dao;
	
	private UsersFacade() throws NamingException, SQLException {
		this.dao = DataAccess.getInstance();
	}
	
	public static UsersFacade getInstance() throws NamingException, SQLException{
		if(singleton == null)
			singleton = new UsersFacade();
		return singleton;
	}
	
	public Users[] getUsersById(int theId)throws SQLException, ClassNotFoundException{
		Connection con = dao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, username, password, name, securityLevel from users WHERE id =?");
		stmt.setInt(1,  theId);
		ResultSet rs = stmt.executeQuery();
		Users[] ingArray = new Users[100];
		int count = 0;
		while(rs.next()) {
			int theId2 = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String name = rs.getString("name");
			int securityLevel = rs.getInt("securityLevel");
			Users ing = new Users(theId2, username, password, name, securityLevel);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}

	
	public Users[] getUsersByUsername(String theUsername)throws SQLException, ClassNotFoundException{
		Connection con = dao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, username, password, name, securityLevel from users WHERE name=?");
		stmt.setString(1,  theUsername);
		ResultSet rs = stmt.executeQuery();
		Users[] ingArray = new Users[100];
		int count = 0;
		while(rs.next()) {
			int theId = rs.getInt("id");
			String theUsername2 = rs.getString("username");
			String password = rs.getString("password");
			String name = rs.getString("name");
			int securityLevel = rs.getInt("securityLevel");
			Users ing = new Users(theId, theUsername2, password, name, securityLevel);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}
	
	
	public Users[] getUsersByPassword(String thePassword)throws SQLException, ClassNotFoundException{
		Connection con = dao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, username, password, name, securityLevel from users WHERE password=?");
		stmt.setString(1,  thePassword);
		ResultSet rs = stmt.executeQuery();
		Users[] ingArray = new Users[100];
		int count = 0;
		while(rs.next()) {
			int theId = rs.getInt("id");
			String username = rs.getString("username");
			String thePassword2 = rs.getString("password");
			String name = rs.getString("name");
			int securityLevel = rs.getInt("securityLevel");
			Users ing = new Users(theId, username, thePassword2, name, securityLevel);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}
	

	public Users[] getUsersByName(String theName)throws SQLException, ClassNotFoundException{
		Connection con = dao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, username, password, name, securityLevel from users WHERE name=?");
		stmt.setString(1,  theName);
		ResultSet rs = stmt.executeQuery();
		Users[] ingArray = new Users[100];
		int count = 0;
		while(rs.next()) {
			int theId = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String theName2 = rs.getString("name");
			int securityLevel = rs.getInt("securityLevel");
			Users ing = new Users(theId, username, password, theName2, securityLevel);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}


	public Users[] getUsersBySecurityLevel(int theSecurityLevel)throws SQLException, ClassNotFoundException{
		Connection con = dao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, username, password, name, securityLevel from users WHERE securityLevel=?");
		stmt.setInt(1,  theSecurityLevel);
		ResultSet rs = stmt.executeQuery();
		Users[] ingArray = new Users[100];
		int count = 0;
		while(rs.next()) {
			int theId = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String name = rs.getString("name");
			int theSecurityLevel2 = rs.getInt("securityLevel");
			Users ing = new Users(theId, username, password, name, theSecurityLevel2);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}

	
	public Users[] getUsers() throws SQLException, ClassNotFoundException{
		Connection con = dao.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT id, username, password, name, securityLevel from users WHERE securityLevel=?");
		ResultSet rs = stmt.executeQuery();
		Users[] ingArray = new Users[100];
		int count = 0;
		while(rs.next()) {
			int theId = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String name = rs.getString("name");
			int securityLevel = rs.getInt("securityLevel");
			Users ing = new Users(theId, username, password, name, securityLevel);
			ingArray[count]  = ing;
			count++;
		}
		if(count>0) {
			ingArray = Arrays.copyOf(ingArray, count);
			return ingArray;
		}
		else
			return null;
		
	}
	
	
	public Users[] createUsers(Users theUserToAdd) throws SQLException, ClassNotFoundException{
		Connection con = dao.getConnection();
		PreparedStatement cstmt = con.prepareStatement("INSERT INTO users(id, username, password, name, securityLevel) VALUE (?,?,?,?,?)");
		cstmt.setInt(1, theUserToAdd.getId());
		cstmt.setString(2, theUserToAdd.getUsername());
		cstmt.setString(3, theUserToAdd.getPassword());
		cstmt.setString(4, theUserToAdd.getName());
		cstmt.setInt(5, theUserToAdd.getSecurity_level());
		int res = cstmt.executeUpdate();
		if(res==1) {
			PreparedStatement retrieveStmt = con.prepareStatement("Select * from Users WHERE id=?, username = ?, password = ?, name = ?, and securityLevel = ? ");
			retrieveStmt.setInt(1, theUserToAdd.getId());
			retrieveStmt.setString(2, theUserToAdd.getUsername());
			retrieveStmt.setString(3, theUserToAdd.getPassword());
			retrieveStmt.setString(4, theUserToAdd.getName());
			retrieveStmt.setInt(5, theUserToAdd.getSecurity_level());
			ResultSet rs = retrieveStmt.executeQuery();
			
			int count = 0;
			int MAX = 100;
			Users[] ingArray = new Users[MAX];
			
			while(rs.next()) {
				int theId = rs.getInt("id");
				String theUserName = rs.getString("userName");
				String thePassword = rs.getString("password");
				String theName = rs.getString("name");
				int theSecurityLevel = rs.getInt("securityLevel");
				
				Users ing = new Users(theId, theUserName, thePassword, theName, theSecurityLevel);
				
				ingArray[count] = ing;
				count++;
				
			}
			if(count>0) {
				ingArray = Arrays.copyOf(ingArray, count);
				return ingArray;
			}
			else
				return null;
		}
		else {
			Users[] blankIngArray = new Users[1];
			blankIngArray[0] = new Users();
			return blankIngArray;
		}
		
		
		
		
	}
}
