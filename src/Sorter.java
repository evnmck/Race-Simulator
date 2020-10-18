public class Sorter {

	private DLinkedList<Player> Players;

	/**
	 * Sorter Constructor to initialize the fields
	 * 
	 * @param Players List of Players that needs to be sorted
	 */
	public Sorter(DLinkedList<Player> Players) {
		this.Players = Players;
	}

	/**
	 * getPlayers method to return the inputReader object's field named Players
	 * 
	 * @return DLinkedList<Players> a list of Player objects
	 */
	public DLinkedList<Player> getPlayers() {
		return Players;
	}

	/**
	 * sortByArtist method to sort the linked list of Players by their artist(s)'s
	 * name
	 * 
	 * @return DLinkedList<Players> a sorted list of Player objects
	 */
	public DLinkedList<Player> sortByTime() {
		TimeComparator comparer = new TimeComparator();

		for (int i = 1; i < Players.getSize(); i++) {
			Player key = Players.getEntry(i);
			int j = i - 1;

			while (j >= 0 && comparer.compare(Players.getEntry(j), key) > 0) {
				Players.add(j + 2, Players.getEntry(j));
				Players.remove(j);
				j--;
			}
		}

		return Players;
	}
}