import java.util.Comparator;

public class TimeComparator implements Comparator<Player> {

	@Override
	public int compare(Player one, Player two) {
		return one.getTime() - two.getTime();
	}
}