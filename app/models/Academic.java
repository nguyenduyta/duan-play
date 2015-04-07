package models;

import java.util.ArrayList;
import java.util.List;

public class Academic extends Instead {
	private static List<Academic> academics;
	static {
		academics = new ArrayList<Academic>();
//		academics.add(new Academic("A1", "CNTT"));
//		academics.add(new Academic("A2", "KHMT"));
//		academics.add(new Academic("A3", "DTVT"));
	}
	public Academic() {
		super();
	}
	public Academic(String id, String name,Employ employ) {
		super();
		employ.setAcademic(this);
		this.employ = employ;
	}
}