package ya.dqchess;

public class Unit_Baramosuburosu extends Unit{

    static int baramosuburosu_count = 0;

    public Unit_Baramosuburosu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo,unitInstanceNo, positionX, positionY);
        baramosuburosu_count++;
        super.instanseName = super.getInstanceName(baramosuburosu_count);
    }
}
