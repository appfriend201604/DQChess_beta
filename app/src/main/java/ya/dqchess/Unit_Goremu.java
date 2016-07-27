package ya.dqchess;

public class Unit_Goremu extends Unit{

    static int goremu_count = 0;

    public Unit_Goremu(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        goremu_count++;
        super.instanseName = super.getInstanceName(goremu_count);
    }
}
