package ya.dqchess;

public class Unit_Hoimisuraimu extends Unit{

    static int hoimisuraimu_count = 0;

    public Unit_Hoimisuraimu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        hoimisuraimu_count++;
        super.instanseName = super.getInstanceName(hoimisuraimu_count);
    }
}
