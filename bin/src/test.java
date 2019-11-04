import java.sql.*;

public class test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		UsersFacade u = new UsersFacade();
		Users nu = new Users(3, "F", "1234567", "FF", 1);
		Users[] uA = new Users[100];
		uA = u.getUsers();
		System.out.println(uA[0].toString());
		System.out.println(uA[1].toString());
		System.out.println(uA[2].toString());
		}
		
	
	}

