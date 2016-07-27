package ya.dqchess;

public class Unit_Tororu extends Unit{

    static int tororu_count = 0;

    public Unit_Tororu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        tororu_count++;
        super.instanseName = super.getInstanceName(tororu_count);
    }
}
