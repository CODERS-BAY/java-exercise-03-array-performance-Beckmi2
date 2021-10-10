import java.util.Arrays;
import java.util.Random;

public class Sorting {
	//static int[] arr = {1, 51, 49, 96, 83, 35, 59, 64, 62, 87, 65, 61, 98, 77, 7, 66, 59, 36, 45, 95, 85, 33, 86, 19, 13, 69, 74, 11, 5, 50, 42, 2, 64, 12, 73, 16, 37, 76, 96, 82, 34, 10, 57, 12, 57, 7, 26, 52, 58, 87, 95, 87, 7, 35, 47, 62, 11, 28, 2, 87, 15, 95, 85, 44, 7, 73, 60, 57, 86, 66, 88, 22, 21, 58, 42, 13, 93, 26, 16, 25, 82, 28, 38, 27, 60, 42, 63, 28, 38, 30, 41, 1, 49, 38, 19, 92, 73, 57, 81, 18, 7, 53, 79, 55, 1, 57, 76, 69, 67, 81, 26, 65, 43, 66, 96, 16, 53, 94, 8, 74, 45, 67, 93, 66, 86, 45, 7, 59, 98, 24, 36, 67, 37, 61, 59, 99, 29, 64, 82, 72, 71, 30, 71, 54, 30, 41, 41, 92, 62, 16, 20, 49, 63, 45, 31, 65, 39, 7, 62, 89, 55, 55, 55, 44, 39, 28, 29, 36, 11, 65, 85, 4, 77, 87, 93, 36, 64, 69, 97, 6, 20, 28, 94, 91, 65, 21, 3, 2, 75, 16, 70, 21, 13, 55, 22, 23, 78, 98, 99, 53, 0, 63, 18, 84, 85, 78, 78, 27, 41, 70, 78, 88, 57, 27, 70, 79, 31, 2, 76, 61, 2, 28, 39, 55, 96, 20, 98, 25, 20, 23, 2, 33, 79, 41, 62, 56, 69, 71, 85, 70, 5, 82, 50, 74, 20, 28, 95, 18, 37, 91, 97, 52, 83, 1, 19, 95, 12, 99, 2, 39, 17, 9, 78, 72, 4, 11, 47, 7, 84, 55, 47, 65, 46, 71, 35, 15, 30, 64, 50, 96, 78, 0, 5, 83, 67, 19, 82, 88, 38, 65, 38, 96, 91, 67, 59, 62, 60, 39, 84, 59};
	
	static final int SIZE = 100;
	static int[] arr = generateArray(SIZE);
	static int[] unsorted = copyArray(arr);
	
    public static void main(String[] args) {
    	/*
    	 * 					100 Elemente	200 Elemente	300 Elemente	1000 Elemente	10000 Elemente		100000 Elemente
    	 * quicksort		82.700			189.700			293.800			758.700		3.952.100			17.991.300
    	 * selection sort	244.300			1.020.400		1.333.900		758.700		102.345.400			11.587.047.200
    	 */
    	long startTime;
    	long endTime;
    	
    	//System.out.println("unsorted: " + Arrays.toString(unsorted));

    	System.out.println("====================================");
    	System.out.println("Selection Sort");
    	
    	startTime = System.nanoTime();
    	int[] sortedSelection = selectionsort(arr);
    	//System.out.println("sortierter Array: " + Arrays.toString(sortedSelection));
    	endTime = System.nanoTime();
    	System.out.println("time: " + (endTime - startTime)); 
    	
    	
    	
    	System.out.println("====================================");
    	System.out.println("Quick Sort");
    	arr = copyArray(unsorted);  //Array auf unsortierten Array zurücksetzen
    	
    	startTime = System.nanoTime();
    	int[] sortedQuicksort = quicksort(arr);
    	//System.out.println("sortierter Array: " + Arrays.toString(sortedQuicksort));
    	endTime = System.nanoTime();
    	System.out.println("time: " + (endTime - startTime)); 
		
    	
    }
    
    
    /**
     * sortiert den übergebenen Array mit Hilfe des Bubble Sort-Algorithmus
     * @param arr
     * @return
     */
    public static int[] bubbleSort (int[] arr) {

    	return null;
    }
    

    /**
     * sortiert den übergebenen Array mit Hilfe des Selection Sort-Algorithmus
     * @param arr
     * @return
     */
    public static int[] selectionsort (int[] arr) {
    	
		for (int i = 0; i < arr.length; i++) {
			
			// i ist jene Stelle, dessen Element evtl. vertauscht wird, falls das
			// Vergleichselement an der Stelle j kleiner ist
			for (int j = i + 1; j < arr.length; j++) {
				changeElements(arr, i, j);
			}
		}
				
		return arr;
    }
    
	public static void changeElements(int[] arr, int pos1, int pos2) {
		int tmp;

		if (arr[pos2] < arr[pos1]) {
			tmp = arr[pos1];
			arr[pos1] = arr[pos2];
			arr[pos2] = tmp;
		}
	}
    
    
    /**
     * erzeugt einen Integer-Array. Die Größe des Arrays wird als Parameter übergeben
     * @param size Größe des Arrays
     */
    public static int[] generateArray (int size) {
    	int[] arr = new int[size];
    	
    	//new Random().nextInt(ELEMENTS)
    	
    	for (int i = 0; i < size; i++) {
			arr[i] = new Random().nextInt(100000);
		}
    	
    	return arr;
    }
    
    public static int[] copyArray (int[] arr) {
    	int[] copy = new int[arr.length];
    	
    	for (int i = 0; i < copy.length; i++) {
			copy[i] = arr[i];
		}
    	
    	return copy;
    }
    
    /******************************************************************************/
    /***                          Quick Sort                                    ***/
    /******************************************************************************/
	/**
	 * wenn das Array-Element an pos1 größer ist wie das Element an pos2 werden die
	 * beiden Elemente im Array vertauscht. das so veränderte Array ist als globales
	 * Array arr in der main-Methode definiert
	 * 
	 * @param pos1 Array-Position des ersten zu vergleichenden Elementes
	 * @param pos2 Array-Postition des zweiten zu vergleichenden Elementes
	 * @return ist true, wenn die Elemente vertauscht wurden und false, wenn kein
	 *         Tausch passiert ist.
	 */
	public static boolean swappedIfNecessary(int pos1, int pos2) {
		int tmp;

		if (arr[pos1] > arr[pos2]) {
			tmp = arr[pos1];
			arr[pos1] = arr[pos2];
			arr[pos2] = tmp;
			return true;
		}

		return false;
	}

	/**
	 * gibt die Anzahl an Elementen in der übergebenen Partition zurück
	 * 
	 * @param partition
	 * @return Anzahl an Elementen in der übergebenen Partition
	 */
	public static int nrOfElementsInPartition(int[] partition) {
		return partition[1] - partition[0] + 1;
	}

	/**
	 * findet für die übergebene Pivotposition und den Startwert i das erste
	 * Element, das größer ist wie das Pivotelement.
	 * Wenn es kein solches Element
	 * gibt, dann wird wird die Pivot-Position zurückgegeben
	 * 
	 * @param i
	 * @param pivotPosition
	 * @return findet die Position jenes Elementes auf der linken Seite, das größer
	 *         ist wie das Pivot-Element. Wenn es keines gibt. wird die
	 *         Pivotposition zurückgegeben.
	 */
	public static int find_i(int i, int pivotPosition) {
		boolean found_i = false;
		int pos_i = i;

		while ((!found_i) && (i < arr.length)) {
			if (arr[i] >= arr[pivotPosition]) {
				found_i = true;
				pos_i = i;
			}
			i++;
		}
		return pos_i;
	}

	/**
	 * findet für die übergebene Pivotposition und den Startwert i das erste
	 * Element, das größert ist wie das Pivotelement wenn es kein solches Element
	 * gibt, das wird wird die Pivot-Position zurückgegeben
	 * 
	 * @param i
	 * @param pivotPosition
	 * @return findet die Position jenes Elementes auf der linken Seite, das größer
	 *         ist wie das Pivot-Element. Wenn es keines gibt. wird die
	 *         Pivotposition zurückgegeben.
	 */
	public static int find_j(int j, int pivotPosition) {
		boolean found_j = false;
		int pos_j = j;

		while ((!found_j) && (j > pivotPosition)) {
			if (arr[j] < arr[pivotPosition]) {
				found_j = true;
				pos_j = j;
			}
			j--;
		}
		return pos_j;
	}
	
	
	/**
	 * sortiert die Elemente der übergebenen Partition und übergibt
	 * die Endposition des Pivot-Elementes
	 * @param partition
	 * @return
	 */
	public static int sortAndFindTheRightPlaceForPivot (int[] partition) {
		int pivotPosition = partition[0];
		
		int i = partition[0] + 1;
		int j = partition[1];
		int found_i = i;
		int found_j = j;
		
		while (i <= j) {
			found_i = find_i(i, pivotPosition);
			found_j = find_j(j, pivotPosition);
			
			//wenn i j hier überlappt, dann muss
			if (found_i > found_j) {
				swappedIfNecessary(found_j, found_i);
			} else {
				swappedIfNecessary(found_i, found_j);
			}
			
			
			i = found_i + 1;
			j = found_j -1;
		}
		
		//nach der while-Schleife haben sich i und j gekreuzt.
		//danach muss das KLEINERE Element (found_i oder found_j) mit dem Pivot-Element verglichen und
		//evtl. geswapped werden.
		//Danach steht das Pivot-Element an der richtigen Stelle
		//und diese Position wird zurückgegeben.		
		
		int positionOfSmallerElement = positionOfSmallerElement(found_i, found_j);
		if (swappedIfNecessary(pivotPosition, positionOfSmallerElement(found_i, found_j))) {
			return positionOfSmallerElement;		//es wurde geswapped und das Pivot-Elemnt hat Platz gewechselt
		} else {
			return pivotPosition;  //das Pivot-Element bleibt an der alten Stelle.
		}
		
	}
		
	/**
	 * sortiert die übergebene Partition bis in die unterste Ebene durch rekursiven Aufruf
	 * @param partition		gibt die zu behandelnde Partition eines Arrays anhand von Anfangs- und Endposition an
	 */
	public static void treatPartition (int[] partition) {
		int nrOfElementsInPartition = nrOfElementsInPartition(partition);
		int newPivotPosition;
		
		if (nrOfElementsInPartition == 2) {
			swappedIfNecessary(partition[0], partition[1]);
		}
		
		if (nrOfElementsInPartition > 2) {
			newPivotPosition = sortAndFindTheRightPlaceForPivot(partition);
			int[] leftPartition = {partition[0], newPivotPosition - 1};
			int[] rightPartition = {newPivotPosition + 1, partition[1]};
			
			if (nrOfElementsInPartition(leftPartition) >= 2) {
				treatPartition(leftPartition);
			}
			if (nrOfElementsInPartition(rightPartition) >= 2) {
				treatPartition(rightPartition);
			}
		}
	}

	/**
	 * ermittelt, welcher Wert der beiden übergebenen Array-Positionen der kleinere ist
	 * und gibt seine Position im Array zurück
	 * @param posElement1	Position des ersten Vergleichselementes
	 * @param posElement2	Position des zweiten Vergleichselementes
	 * @return
	 */
	public static int positionOfSmallerElement(int posElement1, int posElement2) {
		if (arr[posElement1] < arr[posElement2]) {
			return posElement1;
		} else {
			return posElement2;
		}
	}
	
	/**
	 * sortiert den Übergebenen Array mit quicksort
	 * @param arr
	 */
	public static int[] quicksort (int[] arr) {
		int[] partition = { 0, arr.length - 1 };
		treatPartition(partition);
		return arr;
	}

    
    
    
}
