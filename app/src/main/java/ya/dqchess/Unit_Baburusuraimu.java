package ya.dqchess;

public class Unit_Baburusuraimu extends Unit{

    static int baburusuraimu_count = 0;

    public Unit_Baburusuraimu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        baburusuraimu_count++;
        super.instanseName = super.getInstanceName(baburusuraimu_count);
    }
}
