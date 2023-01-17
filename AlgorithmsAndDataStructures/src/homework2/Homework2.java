package homework2;

public class Homework2 {

	public static void main(String[] args) {
		int n = 1000000;
		int trials = 10000;
		long totalTime = 0;
		//runs for number of trials
		for (int z = 0; z < trials; z++) {
			//creates the random array
			int nums[] = new int[n];
			/* FOR RANDOM NUMS
			for (int i = 0; i < n; i++) {
				int temp = (int)(Math.random() * n);
				nums[i] = temp;
			}*/
			// for in order nums
			for (int i = 0; i < n; i++) {
				nums[i] = i;
			}
			//does time stuff
			long startTime = System.currentTimeMillis();
			int out[] = InsertionSort(nums);
			long endTime = System.currentTimeMillis();
			totalTime += (endTime - startTime);
		}
		System.out.println(totalTime);
		System.out.println("time: " + totalTime / (double) trials);
	}
	
	public static int[] InsertionSort(int array[]) {
		int n = array.length;
		for (int i = 1; i < n; i++) {
			int key = array[i];
			int j = i - 1;
			
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
		}
		return array;
	}
	
	public static int[] MergeSort(int array[], int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			MergeSort(array, p, q);
			MergeSort(array, q + 1, r);
			Merge(array, p, q, r);
		}
	}
	
	public static int[] Merge(int array[], int p, int q, int r) {
		
	}

}
