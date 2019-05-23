package testing;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class MatchMe {

	public int add(int a, int b) {
		return a + b;
	}

	@Test
	public void addTest() {
		assertEquals(5, add(2, 3));
	}

//	public ArrayList<String> matchMeArray() {
//		ArrayList<String> nameArr = new ArrayList<>();
//		nameArr.add("Phillida");
//		nameArr.add("King");
//		nameArr.add("Asahi");
//		//nameArr.add(name);
//		return nameArr;
//	}
//
//	
//	@Test
//	public void matchMeTest() {
//		assertArrayEquals(String[] "Phillida", "King", "Asahi", String[] matchMeArray());
//	}

	public static void main(String[] arg) {

	}
}
