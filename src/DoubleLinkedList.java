import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * @author tmanning
 *
 * @param <T>
 */
public class DoubleLinkedList<T> implements DoubleLinkedListADT<T> {
	private LinearNode<T> head;
	private LinearNode<T> tail;
	/**
	 * Number of modifications in the double linked list, used to tell if the iterator is still 'trust-worthy'
	 */
	private long modCount;
	/**
	 * Number of nodes in the double linked list.
	 */
	private int count;
	public DoubleLinkedList(){
		count = 0;
		head = tail = null;
		modCount = 0;
	}

	public void addToFront(T element) {
		ListIterator<T> itr = listIterator();
		itr.add(element);
	}

	public void addToRear(T element) {
		ListIterator<T> itr = listIterator(size());
		itr.add(element);
	}

	public void addAfter(T element, T target) {
		ListIterator<T> itr = listIterator();
		boolean found=false;
		while(!found && itr.hasNext()){
			if(itr.next()==target){
				found=true;
			}
		}
		if(!found){
			throw new ElementNotFoundException("DoubleLinkedList");
		}
		itr.add(element);
	}
	public T removeFirst() {
		if(isEmpty()){
			throw new EmptyCollectionException("DoubleLinkedList");
		}
		ListIterator<T> itr = listIterator();
		T result = itr.next();
		itr.remove();
		return result;
	}
	public T removeLast() {
		if(isEmpty()){
			throw new EmptyCollectionException("DoubleLinkedList");
		}
		ListIterator<T> itr = listIterator();
		T result;
		for(int i=0; i<size()-1; i++){
			itr.next();
		}
		result=itr.next();
		itr.remove();
		return result;
	}
	public T remove(T element) {
		ListIterator<T> itr = listIterator();
		boolean found=false;
		T result=null;
		while(!found && itr.hasNext()){
			result=itr.next();
			if(result==element){
				found=true;
			}
		}
		if(!found){
			throw new ElementNotFoundException("DoubleLinkedList");
		}
		itr.remove();
		return result;
	}
	public T first() {
		if(isEmpty()){
			throw new EmptyCollectionException("DoubleLinkedList");
		}
		return head.getElement();
	}
	public T last() {
		if(isEmpty()){
			throw new EmptyCollectionException("DoubleLinkedList");
		}
		return tail.getElement();
	}
	public boolean contains(T target) {
		ListIterator<T> itr = listIterator();
		while(itr.hasNext()){
			if(itr.next()==target){
				return true;
			}
		}
		return false;
	}
	public boolean isEmpty() {
		return head==null;
	}
	public int size() {
		return count;
	}
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	public void add(int index, T element) {
		if(index<0 || index>size()){
			throw new IndexOutOfBoundsException();
		}
		ListIterator<T> itr = listIterator();
		for(int i=0; i<index; i++){
			itr.next();
		}
		itr.add(element);
	}
	public void set(int index, T element) {
		if(index<0 || index>=size()){
			throw new IndexOutOfBoundsException();
		}
		ListIterator<T> itr = listIterator();
		for(int i=0; i<index; i++){
			itr.next();
		}
		itr.next();
		itr.set(element);
	}
	public void add(T element) {
		addToRear(element);
	}
	public T get(int index) {
		if(index<0 || index>=count){
			throw new IndexOutOfBoundsException();
		}
		LinearNode<T> result=head;
		for(int i=0; i<index; i++){
			result=result.getNext();
		}
		return result.getElement();
	}
	public int indexOf(T element) {
		ListIterator<T> itr = listIterator();
		int index=0;
		while(itr.hasNext()){
			if(itr.next()==element){
				return index;
			}
			index++;
		}
		return -1;
	}
	public T remove(int index) {
		if(index<0 || index>=size()){
			throw new IndexOutOfBoundsException();
		}
		T result=null;
		ListIterator<T> itr = listIterator();
		for(int i=0; i<index; i++){
			itr.next();
		}
		result=itr.next();
		itr.remove();
		return result;
	}
	public String toString(){
		StringBuilder str = new StringBuilder();
		LinearNode<T> current = head;
		str.append("[");
		for(int i=0; i<size();i++){
			str.append(current.getElement());
			str.append(", ");
			current=current.getNext();
		}
		if(!isEmpty()){
			str.delete(str.length()-2, str.length());
		}
		str.append("]");
		return str.toString();
	}
	public ListIterator<T> listIterator(){
		return new DoubleIterator();
	}
	public ListIterator<T> listIterator(int startingIndex) {
		if(startingIndex<0 || startingIndex> count){
			throw new IndexOutOfBoundsException();
		}
		return new DoubleIterator(startingIndex);
	}
	private class DoubleIterator implements ListIterator<T>{
		private long listIteratorModCount;
		private LinearNode<T> nextNode;
		private LinearNode<T> prevNode;
		private int currentIndex;
		private boolean canRemove=false;
		private boolean nextCalledLast=false;//true if next was called last false if previous was called last
		public DoubleIterator(){
			listIteratorModCount=modCount;
			nextNode=head;
			prevNode=null;
			currentIndex=0;
		}
		public DoubleIterator(int startingIndex){
			listIteratorModCount=modCount;
			nextNode=head;
			prevNode=null;
			currentIndex=0;
			for(int i=0; i<startingIndex; i++){
				nextNode=nextNode.getNext();
				currentIndex++;
			}
			if(nextNode!=null){
				prevNode=nextNode.getPrev();
			}else{
				prevNode=tail;
			}
		}
		public boolean hasNext() {
			if (listIteratorModCount != modCount) 
				throw new ConcurrentModificationException();

			return (nextNode != null);
		}
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T result = nextNode.getElement();
			prevNode=nextNode;
			nextNode=nextNode.getNext();
			currentIndex++;
			canRemove=true;
			nextCalledLast=true;
			return result;
		}
		public boolean hasPrevious() {
			if (listIteratorModCount != modCount) 
				throw new ConcurrentModificationException();

			return (prevNode != null);
		}
		public T previous() {
			if(!hasPrevious()){
				throw new NoSuchElementException();
			}
			T result = prevNode.getElement();
			nextNode=prevNode;
			prevNode=prevNode.getPrev();
			currentIndex--;
			canRemove=true;
			nextCalledLast=false;
			return result;
		}
		public int nextIndex() {
			if(listIteratorModCount!=modCount){
				throw new ConcurrentModificationException();
			}
			return currentIndex;
		}
		public int previousIndex() {
			if(listIteratorModCount!=modCount){
				throw new ConcurrentModificationException();
			}
			return (currentIndex-1);
		}
		
		public void remove() {
			if(listIteratorModCount!=modCount){
				throw new ConcurrentModificationException();
			}
			if(!canRemove){
				throw new IllegalStateException();
			}
			if(nextCalledLast){
				LinearNode<T> removalNode=prevNode;
				if(prevNode.getPrev()!=null){
					prevNode=prevNode.getPrev();
					prevNode.setNext(nextNode);
					if(nextNode!=null){
						nextNode.setPrev(prevNode);
					}
					if(removalNode==tail){
						tail=prevNode;
					}
				}else{
					prevNode=null;
					if(nextNode!=null){
						head=nextNode;
						nextNode.setPrev(prevNode);
					}else{
						head=tail=null;
					}
				}
			}else{
				LinearNode<T> removalNode=nextNode;
				if(nextNode.getNext()!=null){
					nextNode=nextNode.getNext();
					nextNode.setPrev(prevNode);
					if(prevNode!=null){
						prevNode.setNext(nextNode);
					}
					if(removalNode==head){
						head=nextNode;
					}
				}else{
					nextNode=null;
					if(prevNode!=null){
						tail=prevNode;
						prevNode.setNext(null);
					}else{
						head=tail=null;
					}
				}
			}
			canRemove=false;
			listIteratorModCount++;
			modCount++;
			currentIndex--;
			count--;
		}
		public void set(T element) {
			if(listIteratorModCount!=modCount){
				throw new ConcurrentModificationException();
			}
			if(!canRemove){
				throw new IllegalStateException();
			}
			if(nextCalledLast){
				prevNode.setElement(element);
			}else{
				nextNode.setElement(element);
			}
			canRemove=false;
			listIteratorModCount++;
			modCount++;
		}

		public void add(T element) {
			if(listIteratorModCount!=modCount){
				throw new ConcurrentModificationException();
			}
			LinearNode<T> newNode = new LinearNode<T>(element);
			newNode.setNext(nextNode);
			newNode.setPrev(prevNode);
			if(nextNode!=null){
				nextNode.setPrev(newNode);
			}else{
				tail=newNode;
			}
			if(prevNode!=null){
				prevNode.setNext(newNode);
			}else{
				head=newNode;
			}
			prevNode=newNode;
			canRemove=false;
			listIteratorModCount++;
			modCount++;
			count++;
			currentIndex++;
		}
	}
	private class LinkedListIterator implements Iterator<T>
	{
		private long iteratorModCount;  // the "version" of the list at the time the Iterator was created
		private DoubleIterator it;
		private LinearNode<T> nextNode;
		private LinearNode<T> prevNode;
		private boolean canRemove=false;
		private boolean nextCalledLast=true;
		/**
		 * Sets up this iterator using the specified items.
		 *
		 * @param collection  the collection the iterator will move over
		 * @param size        the integer size of the collection
		 */
		public LinkedListIterator()
		{
			iteratorModCount=modCount;
			nextNode=head;
			prevNode=null;
		}

		/**
		 * Returns true if this iterator has at least one more element
		 * to deliver in the iteration.
		 *
		 * @return  true if this iterator has at least one more element to deliver
		 *          in the iteration
		 * @throws  ConcurrentModificationException if the collection has changed
		 *          while the iterator is in use
		 */
		public boolean hasNext() throws ConcurrentModificationException
		{
			if (iteratorModCount != modCount) 
				throw new ConcurrentModificationException();

			return (nextNode != null);
		}

		/**
		 * Returns the next element in the iteration. If there are no
		 * more elements in this iteration, a NoSuchElementException is
		 * thrown.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iterator is empty
		 */
		public T next() throws ConcurrentModificationException
		{
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T result = nextNode.getElement();
			prevNode=nextNode;
			nextNode=nextNode.getNext();
			canRemove=true;
			nextCalledLast=true;
			return result;
		}

		/**
		 * The remove operation is not supported.
		 * 
		 * @throws IllegalStateException if next() has not been called previous
		 */
		public void remove() throws IllegalStateException
		{
			if(iteratorModCount!=modCount){
				throw new ConcurrentModificationException();
			}
			if(!canRemove){
				throw new IllegalStateException();
			}
			if(nextCalledLast){
				LinearNode<T> removalNode=prevNode;
				if(prevNode.getPrev()!=null){
					prevNode=prevNode.getPrev();
					prevNode.setNext(nextNode);
					if(nextNode!=null){
						nextNode.setPrev(prevNode);
					}
					if(removalNode==tail){
						tail=prevNode;
					}
				}else{
					prevNode=null;
					if(nextNode!=null){
						head=nextNode;
						nextNode.setPrev(prevNode);
					}else{
						head=tail=null;
					}
				}
			}else{
				LinearNode<T> removalNode=nextNode;
				if(nextNode.getNext()!=null){
					nextNode=nextNode.getNext();
					nextNode.setPrev(prevNode);
					if(prevNode!=null){
						prevNode.setNext(nextNode);
					}
					if(removalNode==head){
						head=nextNode;
					}
				}else{
					nextNode=null;
					if(prevNode!=null){
						tail=prevNode;
						prevNode.setNext(null);
					}else{
						head=tail=null;
					}
				}
			}
			canRemove=false;
			iteratorModCount++;
			modCount++;
			count--;
		}
	}

}
