package ya.dqchess;

public class Unit_Metarusuraimu extends Unit{

    static int metarusuraimu_count = 0;

    public Unit_Metarusuraimu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        metarusuraimu_count++;
        super.instanseName = super.getInstanceName(metarusuraimu_count);
    }
}
