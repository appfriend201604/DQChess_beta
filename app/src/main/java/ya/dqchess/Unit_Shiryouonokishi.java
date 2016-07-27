package ya.dqchess;

public class Unit_Shiryouonokishi extends Unit{

    static int shiryouonokishi_count = 0;

    public Unit_Shiryouonokishi(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        shiryouonokishi_count++;
        super.instanseName = super.getInstanceName(shiryouonokishi_count);
    }
}
