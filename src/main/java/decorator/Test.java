package decorator;

public class Test
{
	public static void main(String[] args) {
		Computer computer = new Computer();
		computer = new Disk(computer);
		computer = new CD(computer);
		System.out.println(computer.description());
	}
}
