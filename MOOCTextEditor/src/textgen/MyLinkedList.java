package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 *
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	int indexOfNode =  this.indexOf(this.tail);
	//this.indexOfNode =  this.indexOf(this.tail);

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.nextNode = tail;
		tail.prevNode = head;
		//this.head = new LLNode<E>(null);
		//this.tail = new LLNode<E>(null);
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element )
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException ("Warning! You've just tried to add null object");
		}
		this.tail = new LLNode<E>(element, tail);
		
		indexOfNode++;                                                                         System.out.println("indexOfNodeInside" + "\t" + this.indexOfNode);
		this.tail.indexNode = indexOfNode;
		this.tail.data = element;
		size++;
		return true;
	}

	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		// TODO: Implement this method.

		LLNode<E> searchNode = recFindIndex(index, head, tail);
		
		if (size == 0) {
			
			throw new IndexOutOfBoundsException ("Warning! You've just tried to get null object");
			
		}

		return searchNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element )
	{
		// TODO: Implement this method
		
	}


	/** Return the size of the list */
	public int size()
	{
		// TODO: Implement this method
		//return -1;
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 *
	 */
	public E remove(int index)
	{
		// TODO: Implement this method
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)
	{
		// TODO: Implement this method
		return null;
	}

	
	//helper method for searching by index - оепедекюрэ!!!!! рпх назейрю хлечр мскебни хмдейя - HEAD, HEAD.NEXTNODE х янаярбеммн мскебни сгек
	//хяйюрэ я йнмжю!!!
	//опнбепйс мю бшунд гю опедекш хмдейянб - оепемеярх б лернд ADD
	public LLNode<E> recFindIndex(int searchIndex, LLNode<E> headSearch, LLNode<E> tailSearch) {
		
		if (searchIndex < headSearch.indexNode || searchIndex > tailSearch.indexNode
				
				|| headSearch == null || tailSearch == null
				
				) {
			
			throw new IndexOutOfBoundsException ("Warning! You've just tried to find null object");
			
		} else if (searchIndex == tailSearch.indexNode) {
			
			return tailSearch;
			
		} else if (searchIndex == headSearch.indexNode) {
			
			return headSearch.nextNode;
			
		} else {
			
			return recFindIndex (searchIndex, headSearch.nextNode, tailSearch.prevNode);
			
		}

	}


}

////////////////////////////////////////////////////////////////////////////////

class LLNode<E>
{
	LLNode<E> prevNode;
	LLNode<E> nextNode;
	E data;
	int indexNode;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode()
	{
		this.prevNode = null;
		this.nextNode = null;
		
	}
	public LLNode(E e)
	{
		this.data = e;
		this.prevNode = null;
		this.nextNode = null;
	}
	//I don't know exactly why but...
	public LLNode(E e, LLNode<E> prevNode) {
		
		
		this(e);
		//for SingleLinkedList
		this.nextNode = prevNode.nextNode;
		prevNode.nextNode = this;
		//for DoublyLinkedList
		this.prevNode = prevNode;
		
	}
	/*
	//I don't know exactly why but...
	public LLNode(E e, LLNode<E> nextNode, LLNode<E> prevNode) {
		this.data = e;
		this.nextNode = nextNode;
		this.prevNode = prevNode;
	}
	*/
    // display ourself
	public void displayNode()
    {
    System.out.print("{" + "\t");
    System.out.print(data.toString() + "\t" + "at index" + indexNode);
    System.out.print("\t" + "}");
    }

}
