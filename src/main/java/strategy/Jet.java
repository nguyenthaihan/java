package strategy;

public class Jet extends Vehicle
{

	public Jet () {
		setGoAlgorithm(new GoByFlyingFast());
	}
}
