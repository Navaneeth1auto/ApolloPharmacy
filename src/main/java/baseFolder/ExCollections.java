package baseFolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.midi.Soundbank;

public class ExCollections implements Comparable<ExCollections>{

	public static void main(String[] args) {
		List<Object> numbers=new ArrayList<Object>();
		numbers.add(5);
		numbers.add(100);
		numbers.add(1);
		numbers.add(20);
		System.out.println(numbers);
		//Collections.sort(numbers);
		System.out.println(numbers);

	}

	public int compareTo(ExCollections o) {
		return 0;
	}

	

}