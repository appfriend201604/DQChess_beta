package ya.dqchess;

public class Unit_Haguremetaru extends Unit{

    static int haguremetaru_count = 0;

    public Unit_Haguremetaru(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        haguremetaru_count++;
        super.instanseName = super.getInstanceName(haguremetaru_count);
    }
}
