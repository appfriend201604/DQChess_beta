package ya.dqchess;

public class Unit_Souryo extends Unit {

    static int souryo_count = 0;

    public Unit_Souryo(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        souryo_count++;
        super.instanseName = super.getInstanceName(souryo_count);
    }
}
