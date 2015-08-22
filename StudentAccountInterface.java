import java.sql.SQLException;

/**
*Interface for students accounts to be used in helpdesk software
*@author Tyler Manning
**/
public interface StudentAccountInterface{
	/**
	 * Adds a student to the database with all of the students.
	 * This method should open a database and cleanly add a new student
	 * with no existing laptop rentals and a balance of $0.0
	 * @param firstName
	 * @param middleInitial
	 * @param lastName
	 * @return true if the student was added sucessfully false
	 * if any errors were encountered and the student was not added
	 * sucessfully.
	 * @throws SQLException 
	 */
	public boolean addStudent(String firstName, String lastName)throws SQLException;
	
	/**
	 * Calls up a student account and gives accesss to a student object.
	 * @param firstName
	 * @param lastName
	 * @param middleInitial
	 * @return the student account tied to the specified first and last name.
	 * @throws SQLException 
	 * @throws NoSuchStudentException if the student is not in the database. If
	 * the student was not found then the student will have to be added into the
	 * database before they can be found.
	 */
	public Student getName(String firstName, String lastName) throws SQLException,NoSuchStudentException;
	
	/**
	 * Sees how many times a student has rented a laptop.
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @return the number of times that a student has rented a laptop
	 * @throws SQLException 
	 * @throws NoSuchStudentException if the student is not in the database. If
	 * the student was not found then the student will have to be added into the
	 * database before they can be found.
	 * @throws BannedStudentException 
	 */
	public int getRentNum(String firstName, String lastName) throws SQLException,NoSuchStudentException, BannedStudentException;
	
	/**
	 * Automatically increments the number of times a student has rented a laptop
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @return true if the number of times a student has rented a laptop was sucessfully 
	 * incremented false if there was an error.
	 * @throws SQLException 
	 * @throws NoSuchStudentException if the student is not in the database. If
	 * the student was not found then the student will have to be added into the
	 * database before they can be found.
	 * @throws BannedStudentException 
	 */
	public boolean incrementRentNum(String firstName, String lastName) throws SQLException,NoSuchStudentException, BannedStudentException;

	/**
	 * Automatically decrements the number of times a student has rented a laptop.
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @return true if the number of times a student has rented a laptop was sucessfully 
	 * decremented false if there was an error.
	 * @throws NoSuchStudentException when a student is not found
	 * @throws SQLException when a SQL error occurs.
	 * @throws BannedStudentException 
	 */
	public boolean decrementRentNum(String firstName, String lastName) throws SQLException,NoSuchStudentException, BannedStudentException;
	
	/**
	 * Sets the number of times a student has rented a laptop.
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @param setNum
	 * @return if the number was correctly set then this will return true
	 * if there was an error it will return false.
	 * @throws SQLException 
	 * @throws NoSuchStudentException if the student is not in the database. If
	 * the student was not found then the student will have to be added into the
	 * database before they can be found.
	 * @throws BannedStudentException 
	 */
	public boolean setRentNum(String firstName, String lastName, int setNum) throws SQLException,NoSuchStudentException, BannedStudentException;
	
	/**
	 * Gets the balance a specified student.
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @return the balance of the student account that was requested.
	 * @throws NoSuchStudentException when a student is not found
	 * @throws SQLException when a SQL error occurs.
	 * @throws BannedStudentException 
	 */
	public double getBalance(String firstName, String lastName) throws SQLException, NoSuchStudentException, BannedStudentException;
	
	/**
	 * Sets the balance of the specified student account.
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @param setVal
	 * @return true if the balance was correctly set, false if there was an error
	 * @throws SQLException 
	 * @throws NoSuchStudentException if the student is not in the database. If
	 * the student was not found then the student will have to be added into the
	 * database before they can be found.
	 * @throws BannedStudentException 
	 */
	public boolean setBalance(String firstName, String lastName, double setVal) throws SQLException,NoSuchStudentException, BannedStudentException;
	
	/**
	 * Adds the specified value to the student account. This does
	 * not override any existing money that the student had on this account
	 * it only increments the value by this much.
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @param addVal
	 * @return true if the value was successfully added to the account. 
	 * False if there was some error.
	 * @throws SQLException 
	 * @throws NoSuchStudentException if the student is not in the database. If
	 * the student was not found then the student will have to be added into the
	 * database before they can be found.
	 * @throws BannedStudentException 
	 */
	public boolean addToBalance(String firstName, String lastName, double addVal) throws SQLException, NoSuchStudentException, BannedStudentException;
	
	/*re
	 * Charges a specified user account the specified amount. This will
	 * subtract the specified amount from the students current balance
	 * Method will not run if isBanned() returns true.
	 * @param firstName
	 * @param lastName
	 * @param chargeVal
	 * @return The students new account balance will be returned.
	 * @throws SQLException 
	 * @throws NoSuchStudentException if the student is not in the database. If
	 * the student was not found then the student will have to be added into the
	 * database before they can be found.
	 * @throws NegativeMoneyException this is thrown if the student account does not
	 * have enough money for this transaction.
	 * @throws BannedStudentException 
	 */
	public double chargeAccount(String firstName, String lastName, double chargeVal) throws SQLException, NoSuchStudentException, NegativeMoneyException, BannedStudentException;
	
	/**
	 * This method will search for the specified student in the database and return
	 * true if the student is banned and false if the student is not.
	 * @param firstName
	 * @param lastName
	 * @return True if the student is banned false if the student is not banned.
	 * @throws SQLException
	 * @throws NoSuchStudentException
	 */
	public boolean isBanned(String firstName, String lastName) throws SQLException, NoSuchStudentException;
}