package nordhw07;

public class Driver {

	public static void main(String[] args) {
		double[] array1 = {};
		double[] array2 = {0};
		double[] array3 = {0,0};
		double[] array4 = {0,1};
		double[] array5 = {2.3, 2.31, 2.31, 5};
		double[] array6 = {2,4,6,8,7,9,11,13};
		double[] array7 = {5, 4};
		double[] array8 = {1,2,3,0};
		double[] array9 = {5,1,2,3};

		System.out.println(isSorted(array1));
		System.out.println(isSorted(array2));
		System.out.println(isSorted(array3));
		System.out.println(isSorted(array4));
		System.out.println(isSorted(array5));
		System.out.println(isSorted(array6));
		System.out.println(isSorted(array7));
		System.out.println(isSorted(array8));
		System.out.println(isSorted(array9));
	}

	public static boolean isSorted(double[] array) {
		boolean sorted = true;
		double current = 0.0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < current) {
				sorted = false;
			}
			else {
				current = array[i];
			}
		}
		return sorted;
	}
}
