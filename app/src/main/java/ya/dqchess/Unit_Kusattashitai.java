package ya.dqchess;

public class Unit_Kusattashitai extends Unit{

    static int kusattashitai_count = 0;

    public Unit_Kusattashitai(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        kusattashitai_count++;
        super.instanseName = super.getInstanceName(kusattashitai_count);
    }
}
