package ya.dqchess;

public class Unit_Baramosuebiru extends Unit{

    static int baramosuebiru_count = 0;

    public Unit_Baramosuebiru(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        baramosuebiru_count++;
        super.instanseName = super.getInstanceName(baramosuebiru_count);
    }
}
