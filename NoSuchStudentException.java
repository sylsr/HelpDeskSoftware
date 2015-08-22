
public class NoSuchStudentException extends Exception {
	public NoSuchStudentException(){
		super("The student you search for was not found");
	}
}
