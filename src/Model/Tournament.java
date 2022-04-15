package Model;

import java.io.Serializable;

public class Tournament implements Serializable {
	private Integer id;
	private String name;
	private Boolean isOfficial;

	public Tournament(Integer id, String name, Boolean isOfficial) {
		this.id = id;
		this.name = name;
		this.isOfficial = isOfficial;
	}
	public Tournament(String name) {
		this(null, name, null);
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
