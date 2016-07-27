package ya.dqchess;

public class Unit_Baramosuzonnbi extends Unit{

    static int baramosuzonnbi_count = 0;

    public Unit_Baramosuzonnbi(int unitNo,int unitInstanceNo, int positionX, int positionY) {
        super(unitNo, unitInstanceNo, positionX, positionY);
        baramosuzonnbi_count++;
        super.instanseName = super.getInstanceName(baramosuzonnbi_count);
    }
}
