package ya.dqchess;

public class Unit_Suraimu extends Unit{

    static int suraimu_count = 0;

    public Unit_Suraimu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        suraimu_count++;
        super.instanseName = super.getInstanceName(suraimu_count);
    }
}
