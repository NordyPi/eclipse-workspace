package nordlab13;

public class Sorting {

	public static int selectionSort(int[] list) {
		int minI = 0;
		int min;
		int unsort = 0;
		for (int i = 0; i < list.length; i ++) {
			min = 10000000;
			for (int n = i; n < list.length; n ++) {
				if (list[n] < min) {
					minI = n;
					min = list[n];
				}
			}
			unsort += (minI - i);
			list[minI] = list[i];
			list[i] = min;
			
			for (int n = 0; n < list.length; n ++){
				System.out.print(list[n] + " ");
			}
			System.out.println();
		}
		return unsort;
	}

}