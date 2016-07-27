package ya.dqchess;

public class Unit_Ryuuouhennshinn extends Unit{

    static int ryuuouhennshinn_count = 0;

    public Unit_Ryuuouhennshinn(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        ryuuouhennshinn_count++;
        super.instanseName = super.getInstanceName(ryuuouhennshinn_count);
    }
}
