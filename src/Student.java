/**
*Objects for students
*@author Tyler Manning
**/
public class Student implements StudentInterface{
	private String firstName;
	private String lastName;
	private double balance;
	private int numOfRents;
	private int banned;
	
	public Student(String firstName, String lastName, double balance, int numOfRents, int banned){
		this.firstName=firstName;
		this.lastName=lastName;
		this.balance=balance;
		this.numOfRents=numOfRents;
		this.banned=banned;
	}
	
	public String getFirstName(){
		String retVal=firstName;
		return retVal;
	}
	

	public boolean setFirstName(String setVal){
		this.firstName=setVal;
		if(this.firstName==setVal){
			return true;
		}
		return false;
	}
	

	public String getLastName(){
		String retVal=lastName;
		return retVal;
	}
	

	public boolean setLastName(String setVal){
		this.lastName=setVal;
		if(this.lastName==setVal){
			return true;
		}
		return false;
	}	

	public double getBalance(){
		return balance;
	}
	

	public boolean setBalance(double setVal){
		this.balance=setVal;
		if(this.balance==setVal){
			return true;
		}
		return false;
	}
	

	public int getRentNum(){
		return numOfRents;
	}
	

	public boolean setRentNum(int setVal){
		this.numOfRents=setVal;
		if(this.numOfRents==setVal){
			return true;
		}
		return false;
	}
	
	public boolean isBanned(){
		if(banned==1){
			return true;
		}
		return false;
	}

	public boolean setBan(int banned){
		this.banned =banned;
		return true;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("First Name: "+firstName+" ");
		str.append("Last Name: "+lastName+" ");
		str.append("Balance: "+balance+" ");
		str.append("Number of rents: "+numOfRents+" ");
		if(banned ==0){
			str.append("Is banned: FALSE");
		}else{
			str.append("Is banned: TRUE");
		}
		return str.toString();
	}
}