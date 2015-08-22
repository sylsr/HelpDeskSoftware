import java.sql.*;
/**
 * Manages student account information
 * @author Tyler
 *
 */
public class StudentAccount implements StudentAccountInterface
{
	final String url = "jdbc:mysql://104.131.165.48:443/HelpDeskSoftwareDATA";
	final String user= "studentAdminUser";
	final String password="GFH53JK48";
	public StudentAccount(){
		double tmp;
		try {
			tmp = chargeAccount("Tyler","Manning",5.25);
			System.out.println(tmp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BannedStudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NegativeMoneyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Student getName(String firstNameIn, String lastNameIn) throws SQLException, NoSuchStudentException{
		Statement execute=null;
		String firstName;
		String lastName;
		double balance;
		int numOfRents;
		int banned;
		Student retStudentObj=null;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		firstName=test.getString("FirstName");
		lastName=test.getString("LastName");
		balance=test.getDouble("Balance");
		numOfRents=test.getInt("RentalNum");
		banned=test.getInt("Banned");
		retStudentObj= new Student(firstName, lastName, balance, numOfRents,banned);
		con.close();
		return retStudentObj;
	}

	public boolean addStudent(String firstName, String lastName) throws SQLException{
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement insertStu=con.prepareStatement("INSERT INTO students VALUES ('"+firstName+"', '"+lastName+"', 0.0,0,0)");
		insertStu.execute();
		con.close();
		return true;
	}

	public int getRentNum(String firstNameIn, String lastNameIn) throws SQLException, NoSuchStudentException, BannedStudentException {
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		int numOfRents;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		numOfRents=test.getInt("RentalNum");
		con.close();
		return numOfRents;
	}

	public boolean incrementRentNum(String firstNameIn, String lastNameIn) throws SQLException, NoSuchStudentException, BannedStudentException{
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		int numOfRents;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		numOfRents=test.getInt("RentalNum");
		numOfRents=numOfRents+1;
		PreparedStatement updateRent=con.prepareStatement("UPDATE students SET RentalNum="+numOfRents+" WHERE FirstName='"+firstNameIn+"' AND LastName='"+lastNameIn+"'");
		updateRent.execute();
		con.close();
		return true;
	}

	public boolean decrementRentNum(String firstNameIn, String lastNameIn) throws SQLException, NoSuchStudentException, BannedStudentException{
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		int numOfRents;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		numOfRents=test.getInt("RentalNum");
		if(numOfRents>0){
			numOfRents=numOfRents-1;
		}
		PreparedStatement updateRent=con.prepareStatement("UPDATE students SET RentalNum="+numOfRents+" WHERE FirstName='"+firstNameIn+"' AND LastName='"+lastNameIn+"'");
		updateRent.execute();
		con.close();
		return true;
	}

	public boolean setRentNum(String firstNameIn, String lastNameIn, int setNum) throws BannedStudentException, SQLException, NoSuchStudentException{
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		if(setNum>=0){
			PreparedStatement updateRent=con.prepareStatement("UPDATE students SET RentalNum="+setNum+" WHERE FirstName='"+firstNameIn+"' AND LastName='"+lastNameIn+"'");
			updateRent.execute();
		}
		con.close();
		return true;
	}

	public double getBalance(String firstNameIn, String lastNameIn) throws SQLException, NoSuchStudentException, BannedStudentException{
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		double balance;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		balance=test.getDouble("Balance");
		con.close();
		return balance;
	}

	public boolean setBalance(String firstNameIn, String lastNameIn, double setVal) throws SQLException, NoSuchStudentException, BannedStudentException{
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		if(setVal>=0){
			PreparedStatement updateRent=con.prepareStatement("UPDATE students SET Balance="+setVal+" WHERE FirstName='"+firstNameIn+"' AND LastName='"+lastNameIn+"'");
			updateRent.execute();
		}
		con.close();
		return true;
	}

	public boolean addToBalance(String firstNameIn, String lastNameIn, double addVal) throws SQLException, NoSuchStudentException, BannedStudentException{
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		double currentBalance;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		if(addVal>=0){
			currentBalance=test.getDouble("Balance");
			double setVal=addVal+currentBalance;
			PreparedStatement updateRent=con.prepareStatement("UPDATE students SET Balance="+setVal+" WHERE FirstName='"+firstNameIn+"' AND LastName='"+lastNameIn+"'");
			updateRent.execute();
		}
		con.close();
		return true;
	}

	public double chargeAccount(String firstNameIn, String lastNameIn, double chargeVal) throws SQLException, NoSuchStudentException, BannedStudentException, NegativeMoneyException{
		if(isBanned(firstNameIn,lastNameIn)==true){
			throw new BannedStudentException();
		}
		Statement execute=null;
		double currentBalance;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		double setVal=0;
		if(tmpBool ==false) throw new NoSuchStudentException();
		if(chargeVal>=0){
			currentBalance=test.getDouble("Balance");
			setVal=currentBalance-chargeVal;
			if(setVal>=0){
				PreparedStatement updateRent=con.prepareStatement("UPDATE students SET Balance="+setVal+" WHERE FirstName='"+firstNameIn+"' AND LastName='"+lastNameIn+"'");
				updateRent.execute();
			}else{
				throw new NegativeMoneyException();
			}
		}
		con.close();
		return setVal;
	}

	public boolean isBanned(String firstNameIn, String lastNameIn) throws SQLException, NoSuchStudentException {
		Statement execute=null;
		int banned;
		Connection con = DriverManager.getConnection(url, user, password);
		execute= con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		ResultSet test = execute.executeQuery("SELECT * FROM students WHERE FirstName LIKE '"+firstNameIn+"' AND LastName LIKE '"+lastNameIn+"'" );
		boolean tmpBool=test.next();
		if(tmpBool ==false) throw new NoSuchStudentException();
		banned=test.getInt("Banned");
		if(banned==0){
			con.close();
			return false;
		}else{
			con.close();
			return true;
		}
	}
}