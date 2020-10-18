
import java.util.Random;
import java.util.Scanner;

public class ProjectRunner {

	public static void main(String[] args) {

		DLinkedList<Player> list = new DLinkedList<Player>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter # of Players: ");
		int playNum = scan.nextInt();
		String name;
		for (int i = 0; i < playNum; i++) {
			System.out.println("Enter Player Name: ");
			name = scan.next();
			list.add(new Player(name));

		}
		scan.close();
		System.out.println();

		addTime(list);
		System.out.println("Players as Entered");
		System.out.println(list.toString());
		System.out.println();

		Sorter time = new Sorter(list);
		list = time.sortByTime();

		System.out.println("Players in Order of Time (Best -> Worst)");
		System.out.println(list.toString());
		System.out.println();

		if (list.getEntry(0).getTime() < list.getEntry(1).getTime()) {
			System.out.println("Winner is... " + list.getEntry(0).getName() + "!");
		} else {
			System.out.println("There was no Winner");
		}
		getTies(list, playNum);

	}

	static private void addTime(DLinkedList<Player> people) {
		for (int i = 0; i < people.getSize(); i++) {
			Random ran = new Random();
			int newTime = Math.abs(ran.nextInt());
			while (newTime > 100 || newTime < 0) {
				newTime = Math.abs(ran.nextInt());
			}
			people.getEntry(i).setTime(newTime);
		}
	}

	static private void getTies(DLinkedList<Player> list, int playNum) {

		int tieNum = 0;
		int tieAt = -1;
		int tieStart = 0;
		int tieEnd = 0;
		if (playNum > 1) {
			for (int j = 0; j < playNum - 1; j++) {
				if (list.getEntry(j).isTie(list.getEntry(j + 1))) {
					tieAt = j;
					tieStart = j;
					tieEnd = j + 1;
					tieNum++;
					while (tieAt >= 0 && tieAt < playNum - 1) {
						if (list.getEntry(tieAt).isTie(list.getEntry(tieAt + 1))) {
							tieAt++;
							tieEnd++;
						} else {
							tieAt = -1;
						}
					}
					int posNum = tieStart + 1;
					System.out.print("There was a Tie for #" + posNum + " between... ");
					for (int k = tieStart; k < tieEnd; k++) {
						System.out.print(list.getEntry(k).getName());
						if (k != tieEnd - 1) {
							System.out.print(" and ");
						}
					}
					System.out.println();
					j = j + tieEnd;
				}
			}
		}
		if (tieNum == 0 && playNum > 1) {
			System.out.println("There were no ties");
		}
	}

}
