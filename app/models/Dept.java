package models;

public class Dept extends Instead {

	
	public Dept() {
		super();
	}
	public Dept(String id, String name, Employ employ) {
		super();
		employ.setDept(this);
		this.employ = employ;
	}
}