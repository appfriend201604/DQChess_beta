package ya.dqchess;

public class Unit_Toruneko extends Unit {

    static int toruneko_count = 0;

    public Unit_Toruneko(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        toruneko_count++;
        super.instanseName = super.getInstanceName(toruneko_count);
    }
}
