package ya.dqchess;

public class Unit_Yusha extends Unit {

    static int yusha_count = 0;

    public Unit_Yusha(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        yusha_count++;
        super.instanseName = super.getInstanceName(yusha_count);
    }
}
