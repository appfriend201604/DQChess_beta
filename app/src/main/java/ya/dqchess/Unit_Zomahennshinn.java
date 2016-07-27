package ya.dqchess;

public class Unit_Zomahennshinn extends Unit{

    static int zomahennshinn_count = 0;

    public Unit_Zomahennshinn(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        zomahennshinn_count++;
        super.instanseName = super.getInstanceName(zomahennshinn_count);
    }
}
