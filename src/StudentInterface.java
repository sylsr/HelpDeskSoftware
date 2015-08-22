/**
*Interface for students accounts to be used in helpdesk software
*@author Tyler Manning
**/
public interface StudentInterface{
	/**
	 * Gets the first name of the student.
	 * @return the first name of the student
	 */
	public String getFirstName();
	
	/**
	 * Sets the first name of the student object.
	 * @param setVal
	 * @return true if the value was set correctly false if 
	 * it was not
	 */
	public boolean setFirstName(String setVal);
	
	/**
	 * Gets the last name of the student.
	 * @return the last name of the student
	 */
	public String getLastName();
	
	/**
	 * Sets the last name of the student object.
	 * @param setVal
	 * @return true if the value was set correctly false if 
	 * it was not
	 */
	public boolean setLastName(String setVal);
	
	/**
	 * Gets the balance of the student.
	 * @return the balance of the student
	 */
	public double getBalance();
	
	/**
	 * Sets the balance of the student object.
	 * @param setVal
	 * @return true if the value was set correctly false if 
	 * it was not
	 */
	public boolean setBalance(double setVal);
	
	/**
	 * This method checks to see if the student is banned
	 * @return false if the student is not banned true if the student is banned
	 */
	public boolean isBanned();
	
	/**
	 * Sets the ban boolean for this student true
	 * If a subsequent call to isBanned() is made it will return true
	 * @return true if the value was set correct false otherwise.
	 */
	public boolean setBan(int banned);
	
	/**
	 * Gets the number of times a laptop has been rented by the student.
	 * @return the number of times a laptop has been rented by the student.
	 */
	public int getRentNum();
	
	
	/**
	 * Sets the number of rents for the student object.
	 * @param setVal
	 * @return true if the value was set correctly false if 
	 * it was not
	 */
	public boolean setRentNum(int setVal);
	
	/**
	 * Returns a String that highlights all of the information about 
	 * the student
	 */
	public String toString();
	
}