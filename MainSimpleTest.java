import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainSimpleTest{
	public static void main(String[] args){
		StudentAccount tester = new StudentAccount();
//		MainSimpleTest tester =new MainSimpleTest();
//		tester.runAllTests();
	}

	private Connection con;
	private void runAllTests(){
		runTests_1();
	}
	
	private void clearAllEntries(){
		final String url = "jdbc:mysql://104.131.165.48:443/HelpDeskSoftwareDATA";
		final String user= "studentAdminUser";
		final String password="GFH53JK48";
		try{
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement insertStu=con.prepareStatement("DELTE FROM students WHERE Banned LIKE 0 OR Banned LIKE 1");
			insertStu.execute();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void prepareTests_1(){
		clearAllEntries();
		try {
			PreparedStatement insertStu= con.prepareStatement("INSERT INTO students VALUES ('Marcus','Israel', 0.0,0,0)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void runTests_1(){
		
	}
}