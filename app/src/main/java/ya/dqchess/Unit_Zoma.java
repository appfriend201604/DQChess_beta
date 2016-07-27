package ya.dqchess;

public class Unit_Zoma extends Unit{

    static int zoma_count = 0;

    public Unit_Zoma(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        zoma_count++;
        super.instanseName = super.getInstanceName(zoma_count);
    }
}
