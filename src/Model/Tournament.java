package Model;

public class Tournament {
	private Integer id;
	private String name;
	private Boolean isOfficial;

	public Tournament(Integer id, String name, Boolean isOfficial) {
		this.id = id;
		this.name = name;
		this.isOfficial = isOfficial;
	}
	public Tournament(Integer id, String name) {
		this(id, name, null);
	}
	public Tournament(Integer id) {
		this(id, null);
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName() + " (#" + getId() + ")";
	}
}
