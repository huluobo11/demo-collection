package a1;

import org.junit.Test;

/**
 * @date 2017年9月30日上午10:20:21
 * @Description: 
 * @authorAdministrator
 */
public class TestRandom {
@Test
public void   getRandom(){
	int topicSize=10;
	int randomNum=5;
	int[]remembers=new int[topicSize];
	for (int i = 0; i < randomNum; i++) {
		int ranN=(int)(Math.random()*topicSize);
		if(remembers[ranN]==1){
			i--;
			continue;
		}
		System.out.println(ranN);
		remembers[ranN]=1;
	}
}
}
