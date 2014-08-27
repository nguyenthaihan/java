package strategy;

public class StreetRacer extends Vehicle
{
	public StreetRacer() {
		setGoAlgorithm(new GoByDrivingAlgorithm());
	}
}
