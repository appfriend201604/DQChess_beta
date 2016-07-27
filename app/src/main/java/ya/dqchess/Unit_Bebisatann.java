package ya.dqchess;

public class Unit_Bebisatann extends Unit{

    static int bebisatann_count = 0;

    public Unit_Bebisatann(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        bebisatann_count++;
        super.instanseName = super.getInstanceName(bebisatann_count);
    }
}
