package spring.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SpringTest {
	public static void main(String[] args) {
		List<String> lst1 = new ArrayList<String>();
		lst1.add("aaaa");
		lst1.add("bbbb");
		lst1.add("cccc");
		lst1.add("dddd");
		lst1.add("cccc");
		lst1.add("cccc");
		lst1.add("dddd");
		System.out.println(lst1);
		
		/*List<String> lst2 = Collections.singletonList("cccc");
		System.out.println(lst2.size());
		lst2.remove(0);
		System.out.println(lst2);*/
		
		//Collections.fill(lst1, "cccc");
//		System.out.println(Collections.frequency(lst1, "cccc"));
//		Collections.sort(lst1);
//		System.out.println(lst1);		
//		Collections.reverse(lst1);
//		System.out.println(lst1);
		
		Collections.singletonList(lst1);
		
		
	}
}
