package ya.dqchess;

public class Unit_Senshi extends Unit {

    static int senshi_count = 0;

    public Unit_Senshi(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        senshi_count++;
        super.instanseName = super.getInstanceName(senshi_count);
    }
}
