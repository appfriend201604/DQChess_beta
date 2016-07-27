package ya.dqchess;

public class Unit_Kimera extends Unit{

    static int kimera_count = 0;

    public Unit_Kimera(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        kimera_count++;
        super.instanseName = super.getInstanceName(kimera_count);
    }
}
