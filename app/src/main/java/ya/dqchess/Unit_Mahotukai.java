package ya.dqchess;

public class Unit_Mahotukai extends Unit {

    static int mahotukai_count = 0;

    public Unit_Mahotukai(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        mahotukai_count++;
        super.instanseName = super.getInstanceName(mahotukai_count);
    }
}
