
public class NegativeMoneyException extends Exception {
	public NegativeMoneyException(){
		super("This person does not have enough money to make this transaction.");
	}
}
