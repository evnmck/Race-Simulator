public class Player {
	private String name;
	private int time;

	public Player(String name) {
		this.name = name;
		this.time = 0;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return this.time;
	}

	public boolean isLessThan(Player other) {
		return this.time < other.time;
	}

	public boolean isTie(Player other) {
		double diff = Math.abs(this.time - other.time);
		return (diff == 0.0);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() == obj.getClass()) {
			Player other = (Player) obj;
			return (this.name.equals(other.name) && this.time == other.time);

		}
		return false;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Name: " + name + " Time: " + time + "s");
		return str.toString();
	}

}