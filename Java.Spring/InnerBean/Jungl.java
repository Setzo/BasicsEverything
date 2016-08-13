import java.util.HashMap;
import java.util.Map;

public class Jungl {

	private Map<String, String> map = new HashMap<String, String>();

	private Map<String, Animal> amap = new HashMap<String, Animal>();

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, Animal> getAmap() {
		return amap;
	}

	public void setAmap(Map<String, Animal> amap) {
		this.amap = amap;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, String> entry : map.entrySet()) {

			sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
		}

		for (Map.Entry<String, Animal> entry : amap.entrySet()) {

			sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
		}

		return sb.toString();
	}
}
