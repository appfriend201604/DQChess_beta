package ya.dqchess;

public class Unit_Doragon extends Unit{

    static int doragon_count = 0;

    public Unit_Doragon(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        doragon_count++;
        super.instanseName = super.getInstanceName(doragon_count);
    }
}
