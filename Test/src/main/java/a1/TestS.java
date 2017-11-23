package a1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
<<<<<<< HEAD
=======
import java.util.Vector;
>>>>>>> branch 'master' of https://github.com/huluobo11/cxftest.git

import org.junit.Test;

/**
 * @date 2017年9月29日下午3:56:21
 * @Description:
 * @authorAdministrator
 */
public class TestS {
	@Test
	public void test() {
		Scanner scanner = new Scanner(System.in);
		int nextInt = scanner.nextInt();
		String nextLine = scanner.nextLine();
		System.out.println(nextInt);
		System.out.println(nextLine);
		scanner.close();
	}

	@Test
	public void testa() {
		/*
		 * int x,a=4,b=2,c=5; x=c=a=b++; System.out.println(x);
		 */
		int i = 0;
		for (;;) {
			i++;
			System.out.println("---");
			if (i > 10)
				break;
		}
	}

	public List<String> moveCard(List<String> src) {
		if (src == null)
			return null;
		List<String> res = new LinkedList<String>();
		for (;;) {
			if (src.size() == 0)
				break;
			src.add(src.remove(0));
			res.add(src.remove(0));
		}
		return res;
	}

	@Test
	public void main() {
		List<String> a = new LinkedList<String>();
		a.addAll(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));

		System.out.println(moveCard(a));
	}
}
