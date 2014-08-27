package strategy;

public class RollRoyce extends Vehicle
{
	public RollRoyce() {
		setGoAlgorithm(new GoByDrivingAlgorithm());
	}
}
