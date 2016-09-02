
public class Tab {

    Tab() {

        ID = cnt;
        cnt++;
    }

    private int ID = 0;
    private static int cnt = 0;
    private short[][] tab = new short[2][1000];

    public int getID() {
        return ID;
    }

    public static int getCnt() {
        return cnt;
    }

    public short[][] getTab() {
        return tab;
    }

    public short getTabElement(int i, int j) {
        return tab[i][j];
    }

    public void setTabElement(int i, int j, short x) {
        tab[i][j] = x;
    }

    public void setTab(short[][] tab) {
        this.tab = tab;
    }

    public void writeTabElement(int i, int j) {
        System.out.print(tab[i][j]);
    }

    public void writeTab() {
        int j = 0;
        for (int i = 0; i < 1000; i++) {
            System.out.printf("%4d  ID  %4d  Value\n", tab[j][i], tab[j][i]);
        }
    }
}
