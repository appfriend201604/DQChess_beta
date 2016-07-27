package ya.dqchess;

public class Unit_Baramosu extends Unit{

    static int baramosu_count = 0;

    public Unit_Baramosu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        baramosu_count++;
        super.instanseName = super.getInstanceName(baramosu_count);
    }
}
