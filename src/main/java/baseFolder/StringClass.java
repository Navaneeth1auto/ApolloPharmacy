package baseFolder;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StringClass {

	public static void main(String[] args) {
		String str="MRP:(?556)?450";
		System.out.println(str);
		String[] values=str.split("\\?");
		System.out.println(Arrays.toString(values)+" SIZE IS :"+values.length);
		System.out.println(values[values.length-1]);

	}

}
