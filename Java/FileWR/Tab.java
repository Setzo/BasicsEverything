public class Tab {

	Tab() {

		ID = cnt;
		cnt++;
	}

	private int ID = 0;
	private static int cnt = 0;
	private short[] tab = new short[10000];

	public int getID() {
		return ID;
	}

	public static int getCnt() {
		return cnt;
	}

	public short[] getTab() {
		return tab;
	}

	public short getTabElement(int i) {
		return tab[i];
	}

	public void setTabElement(int i, short x) {
		tab[i] = x;
	}

	public void setTab(short[] tab) {
		this.tab = tab;
	}
	
	public void writeTabElement(int i) {
		System.out.print(tab[i]);
	}
	
	public boolean hasNxt(int i) {
		try{
			if(tab[i]==1) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//e.printStackTrace();
			return false;
		}
		return true;	
	}
	
	public void writeTab() {
		for(int i=0; i<10000; i++) {
			System.out.printf("Value : %6d",tab[i]);
		}
	}
}
