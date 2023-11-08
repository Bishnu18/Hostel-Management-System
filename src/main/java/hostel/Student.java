package hostel;

public class Student{
	
	
	private int id;
	private String name;
	private int age;
	private String dep;
	private int roomno;
	
	public Student(int id, String name, int age, String dep, int roomno) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.dep = dep;
		this.roomno = roomno;
	}
	
	public Student(String name, int age, String dep, int roomno) {
		super();
		this.name = name;
		this.age = age;
		this.dep = dep;
		this.roomno = roomno;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}

}
