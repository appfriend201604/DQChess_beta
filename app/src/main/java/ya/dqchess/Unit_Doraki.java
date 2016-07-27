package ya.dqchess;

public class Unit_Doraki extends Unit{

    static int doraki_count = 0;

    public Unit_Doraki(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        doraki_count++;
        super.instanseName = super.getInstanceName(doraki_count);
    }
}
