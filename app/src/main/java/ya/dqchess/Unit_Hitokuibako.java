package ya.dqchess;

public class Unit_Hitokuibako extends Unit{

    static int hitokuibako_count = 0;

    public Unit_Hitokuibako(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        hitokuibako_count++;
        super.instanseName = super.getInstanceName(hitokuibako_count);
    }
}
