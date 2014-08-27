package strategy;

public class StartTheRace
{
	public static void main( String[] args) {
        StreetRacer streetracer = new StreetRacer();
        RollRoyce rollRoyce = new RollRoyce();
        Helicopter helicopter = new Helicopter();
        Jet jet = new Jet();
        streetracer.go();
        rollRoyce.go();
        helicopter.go();
        jet.go();
	}
}
