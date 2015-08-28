package tool;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0;i<15;i++){
			a.add(i);
		}
		System.out.println(a.get(a.size()-1));

	}

}
