package models;

public class Contract extends Instead {
	public Contract() {
		super();
	}
	public Contract(String id, String name,Employ employ) {
		super();
		employ.setContract(this);
		this.employ = employ;
	}
}