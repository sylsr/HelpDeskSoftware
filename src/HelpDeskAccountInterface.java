/**
*Interface for help desk accounts to be used in helpdesk software
*@author Tyler Manning
**/
public interface HelpDeskAccountInterface{
	/**
	 * This method will querry the database for a username with the input string.
	 * If a username matching the input string was found then the method will return true.
	 * If a username was not found that matches the input string then the method will
	 * return false.
	 * @usrName
	 * @return true if the user name was correct false if it was not.
	 * @throws SQLException
	 * @
	 */
	boolean enterUserName(String usrName) throws SQLException;
	
	/**
	 * This method should be called when help desk attendentds are first logging
	 * in and they enter their password This method will save their password
	 * and use it to authenticate them.
	 * @usrPasstouch 
	 * @return true if the password was correct false if it was not.
	 * @throws SQLException
	 */
	boolean enterUserPass(String usrPass) throws SQLException;
	
	/**
	 * This method will change the password of the person after they are already
	 * logged in.
	 * @param usrName
	 * @param usrPass
	 * @param usrPassNew
	 * @param usrPassNewConfirm
	 * @return true if the password was changed successfully false if there was an error.
	 * @throws SQLException
	 */
	boolean changePassword(String usrName, String usrPass, String usrPassNew, String usrPassConfirm) throws SQLException;	
}