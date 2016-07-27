package ya.dqchess;

public class Unit_Ryuuou extends Unit{

    static int ryuuou_count = 0;

    public Unit_Ryuuou(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        ryuuou_count++;
        super.instanseName = super.getInstanceName(ryuuou_count);
    }
}
