/**
*Interface for laptop cart.
*@author Tyler Manning
**/
public interface LaptopCartInterface{
	/**
	 * Gets the current location of both carts, their ID's and any extra information
	 * This method should be used to output a small paragraph that describes everything
	 * anyone could ever want to know about the carts.
	 * @return A double linked list with the objects of
	 * all of the carts.
	 */
	public DoubleLinkedList<LaptopCart> location();
	
	/**
	 * Returns the next pickup period for the cart with the specified cart ID
	 * @param cartID
	 * @return The next period that the car needs to be picked up and where.
	 */
	public String pickupPeriod(int cartID);
	
}