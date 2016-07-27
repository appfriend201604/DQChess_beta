package ya.dqchess;

public class Unit_Samayouyoroi extends Unit{

    static int samayouyoroi_count = 0;

    public Unit_Samayouyoroi(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        samayouyoroi_count++;
        super.instanseName = super.getInstanceName(samayouyoroi_count);
    }
}
