import java.sql.*;
import java.util.Random;
import java.util.ListIterator;
public class GetAllStudents{
	private DoubleLinkedList<Student> getConnectionData(){
		DoubleLinkedList<Student> studentList= new DoubleLinkedList<Student>();
		final String url = "jdbc:mysql://104.131.165.48:3306/HelpDeskSoftwareDATA";
		final String user= "studentAdminUser";
		final String password="GFH53JK48";
		Random rand=null;
		Statement execute=null;
		String firstName;
		String lastName;
		double balance;
		int numOfRents;
		int banned;
		try{
			rand = new Random();
			Connection con = DriverManager.getConnection(url, user, password);
			execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			ResultSet test = execute.executeQuery("SELECT * FROM students");
			Student tmpStudentObj=null;
			while(test.next()){
				firstName=test.getString("FirstName");
				lastName=test.getString("LastName");
				balance=test.getDouble("Balance");
				numOfRents=test.getInt("RentalNum");
				banned=test.getInt("Banned");
				tmpStudentObj= new Student(firstName, lastName, balance, numOfRents,banned);
				studentList.add(tmpStudentObj);
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		ListIterator itr = studentList.listIterator();
		while(itr.hasNext()){
			System.out.println(itr.next().toString());
		}
		return studentList;
	}
}