import java.util.Iterator;
import java.util.NoSuchElementException;

<<<<<<< HEAD
//DLinkedList implementation from my CS2114 class 
=======
//DLinkedList implementation from my CS2114 class
>>>>>>> e1691c01ab99fff9b3337240c2f6aea1634893b4
public class DLinkedList<T> {

	private static class Node<T> {
		private Node<T> nextNode;
		private Node<T> previousNode;
		private T data;

		/**
		 * Node constructor to initialize data field
		 * 
		 * @param data the data to put inside the node
		 */
		public Node(T data) {
			this.data = data;
		}

		/**
		 * setNext method to set the next field to the passed Node<T> object
		 *
		 * @param next the node after this one
		 */
		public void setNextNode(Node<T> next) {
			nextNode = next;
		}

		/**
		 * setPrevious method to set the previous field to the passed Node<T> object
		 *
		 * @param previous the node before this one
		 */
		public void setPreviousNode(Node<T> previous) {
			previousNode = previous;
		}

		/**
		 * getNextNode method to return the field named next
		 * 
		 * @return the next node
		 */
		public Node<T> getNextNode() {
			return nextNode;
		}

		/**
		 * getPreviousNode method to return the field names next
		 * 
		 * @return the node before this one
		 */
		public Node<T> getPreviousNode() {
			return previousNode;
		}

		/**
		 * Gets the data in the node
		 *
		 * @return the data in the node
		 */
		public T getData() {
			return data;
		}
	}

	/**
	 * How many nodes are in the list
	 */
	private int size;

	/**
	 * The first node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT HOLD
	 * ANY DATA.
	 */
	private Node<T> firstNode;

	/**
	 * The last node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT HOLD
	 * ANY DATA.
	 */
	private Node<T> lastNode;

	/**
	 * DLinkedList constructor to call the initiallizeDataFields method
	 */
	public DLinkedList() {
		initializeDataFields();
	}

	/**
	 * initializeDataFields method to initialize the values of the fields
	 */
	private void initializeDataFields() {
		firstNode = new DLinkedList.Node<T>(null);
		lastNode = new DLinkedList.Node<T>(null);
		firstNode.setNextNode(lastNode);
		lastNode.setPreviousNode(firstNode);
		size = 0;
	}

	/**
	 * isEmpty method to check the size of the DLinkedList
	 *
	 * @return true if the array is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * getSize method to return the field, size
	 * 
	 * @return the number of elements
	 */
	public int getSize() {
		return size;
	}

	/**
	 * clear method remove all elements in a DLinkedList
	 */
	public void clear() {
		initializeDataFields();
	}

	/**
	 * contains method to check if the DLinkedList as the passed element
	 *
	 * @param obj the object to check for
	 * @return true if it contains the object
	 */
	public boolean contains(T obj) {
		return lastIndexOf(obj) != -1;
	}

	/**
	 * getEntry method to return an entry at the passed index
	 * 
	 * @param index where the object is located
	 * @return The object at the given position
	 * @throws IndexOutOfBoundsException if there no node at the given index
	 */
	public T getEntry(int index) {
		return getNodeAtIndex(index).getData();
	}

	/**
	 * add method to add an element to the DLinkedList
	 *
	 * @param newEntry the element to add to the end
	 */
	public void add(T newEntry) {
		add(getSize(), newEntry);
	}

	/**
	 * add method to add an element in th DLinkedList at a passed index
	 *
	 * @param index where to add the object
	 * @param obj   the object to add
	 * @throws IndexOutOfBoundsException if index is less than zero or greater than
	 *                                   size
	 * @throws IllegalArgumentException  if obj is null
	 */
	public void add(int index, T obj) {
		if (index < 0 || size < index) {
			throw new IndexOutOfBoundsException();
		}

		if (obj == null) {
			throw new IllegalArgumentException("Cannot add null " + "objects to a list");
		}

		Node<T> nodeAfter;

		if (index == size) {
			nodeAfter = lastNode;
		} else {
			nodeAfter = getNodeAtIndex(index);
		}

		Node<T> addition = new Node<T>(obj);
		addition.setPreviousNode(nodeAfter.getPreviousNode());
		addition.setNextNode(nodeAfter);
		nodeAfter.getPreviousNode().setNextNode(addition);
		nodeAfter.setPreviousNode(addition);
		size++;
	}

	/**
	 * getNodeAtIndex method to a node at a passed index
	 * 
	 * @param index
	 * @return node at index
	 */
	private Node<T> getNodeAtIndex(int index) {
		if (index < 0 || getSize() <= index) {
			throw new IndexOutOfBoundsException("No element exists at " + index);
		}

		Node<T> current = firstNode.getNextNode(); // as we have a sentinel node

		for (int i = 0; i < index; i++) {
			current = current.getNextNode();
		}

		return current;
	}

	/**
	 * lastIndexOf method to return the last index of a passed element
	 * 
	 * @param obj the object to look for
	 * @return the last position of it. -1 If it is not in the list
	 */
	public int lastIndexOf(T obj) {
		/*
		 * We should go from the end of the list as then we an stop once we find the
		 * first one
		 */
		Node<T> current = lastNode.getPreviousNode();

		for (int i = getSize() - 1; i >= 0; i--) {
			if (current.getData().equals(obj)) {
				return i;
			}

			current = current.getPreviousNode();
		}

		return -1; // if we do not find it
	}

	/**
	 * remove method to remove an element from the DLinkedList at a passed index
	 *
	 * @param index where the object is located
	 * @throws IndexOutOfBoundsException if there is not an element at the index
	 * @return true if successful
	 */
	public boolean remove(int index) {
		Node<T> nodeToBeRemoved = getNodeAtIndex(index);

		nodeToBeRemoved.getPreviousNode().setNextNode(nodeToBeRemoved.getNextNode());
		nodeToBeRemoved.getNextNode().setPreviousNode(nodeToBeRemoved.getPreviousNode());

		size--;

		return true;
	}

	/**
	 * remove method to remove a passed element from the DLinkedList
	 * 
	 * @param obj the object to remove
	 * @return true if the object was found and removed
	 */
	public boolean remove(T obj) {
		Node<T> current = firstNode.getNextNode();

		while (!current.equals(lastNode)) {
			if (current.getData().equals(obj)) {
				current.getPreviousNode().setNextNode(current.getNextNode());
				current.getNextNode().setPreviousNode(current.getPreviousNode());

				size--;

				return true;
			}

			current = current.getNextNode();
		}

		return false;
	}

	/**
	 * toString method to return the DLinkedList as a representational string
	 *
	 * @return a string representing the list
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (!isEmpty()) {
			Node<T> currNode = firstNode.getNextNode();

			while (currNode != lastNode) {
				if (currNode != lastNode.getPreviousNode()) {
					Player element = (Player) currNode.getData();

					builder.append(element);
					builder.append("   ");

					currNode = currNode.getNextNode();
				} else {
					Player element = (Player) currNode.getData();

					builder.append(element);
					currNode = currNode.getNextNode();
				}

			}
		}
		return builder.toString();
	}

	/**
	 * iterator method to create an Iterator object
	 *
	 * @return new Iterator object
	 */
	public Iterator<T> iterator() {
		return new DLinkedListIterator<T>();
	}

	/**
	 * DLinkedListIterator class to iterate over the DLinkedList for sorting
	 * purposes
	 * 
	 * @param <A> The data type within the Iterator
	 * 
	 * @author Evan McKnight (evnmck)
	 * @version 2019.11.18
	 */
	@SuppressWarnings("hiding")
	private class DLinkedListIterator<A> implements Iterator<T> {
		private Node<T> current;
		private boolean calledNext;
		private int elements;

		/**
		 * DLinkedListIterator Constructor for a new DLListIterator object
		 */
		public DLinkedListIterator() {
			current = firstNode.nextNode;
			elements = 0;
			calledNext = false;
		}

		/**
		 * hasNext method to check if the DLinkedList has another elemeent
		 *
		 * @return true if there are more elements in the list
		 */
		@Override
		public boolean hasNext() {
			return current.nextNode != null;
		}

		/**
		 * next method to move the iterator to the next element in the DLinkedList
		 *
		 * @return the value that is next
		 * @throws NoSuchElementException if there are no nodes left in the list
		 */
		@Override
		public T next() {
			if (hasNext()) {
				T temporary = current.data;
				current = current.nextNode;
				calledNext = true;

				elements++;

				return temporary;
			} else {
				throw new NoSuchElementException();
			}

		}

		/**
		 * remove method to remove an element from the DLinkedList as long as it is
		 * possible
		 *
		 * @throws IllegalStateException if next has not been called yet and if the
		 *                               element has already been removed
		 */
		@Override
		public void remove() {
			if (!calledNext) {
				throw new IllegalStateException("next() has not been called");
			} else {
				current.setPreviousNode(current.getPreviousNode().getPreviousNode());
				current.getPreviousNode().setNextNode(current);

				size--;

				calledNext = false;
			}
		}
	}
}
