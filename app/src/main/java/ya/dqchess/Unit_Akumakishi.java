package ya.dqchess;

public class Unit_Akumakishi extends Unit{

    static int akumakishi_count = 0;

    public Unit_Akumakishi(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        akumakishi_count++;
        super.instanseName = super.getInstanceName(akumakishi_count);
    }
}
