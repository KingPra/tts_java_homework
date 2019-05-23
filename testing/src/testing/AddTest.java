package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AddTest {

	@Test
	void testAdd() {
		assertEquals(5, add(2, 3));
	}

	@Test
	void testArray() {
		ArrayList<String> testArr = new ArrayList<>();
		testArr.add("Phillida");
		testArr.add("King");
		testArr.add("Asahi");
		testArr.toArray();
		assertEquals(testArr, matchMeArr());
		// assertArrayEquals("this is an array test", testArr, matchMeArr().toArray());
	}

	private int add(int a, int b) {
		return a + b;
	}

	public ArrayList<String> matchMeArr() {
		ArrayList<String> nameArr = new ArrayList<>();
		nameArr.add("Phillida");
		nameArr.add("King");
		nameArr.add("Asahi");
		nameArr.toArray();
		return nameArr;
	}

}
