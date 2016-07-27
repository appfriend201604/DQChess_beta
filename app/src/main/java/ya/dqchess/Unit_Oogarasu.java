package ya.dqchess;

public class Unit_Oogarasu extends Unit{

    static int oogarasu_count = 0;

    public Unit_Oogarasu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        oogarasu_count++;
        super.instanseName = super.getInstanceName(oogarasu_count);
    }
}
