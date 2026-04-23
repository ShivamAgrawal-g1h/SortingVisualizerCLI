import java.util.Random;
import java.util.Scanner;

class SortingVisualizer_V3 {
    static Scanner sc = new Scanner(System.in);

    static long rec_start;

    static void main() {
        int[] arr;
        arr = ipArray(0);
        unknownAlgoCall(arr);
    }
    // Control flow system : whichAlgo(), ipArray(), further(), processAlgo(), spaceTimeComp()
    static int whichAlgo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Which algorithm will you prefer?");
        System.out.println("1. - Bubble Sort");
        System.out.println("2. - Selection Sort");
        System.out.println("3. - Insertion Sort");
        System.out.println("4. - Merge Sort");
        System.out.println("5. - Quick Sort");
        System.out.println("6. - Counting Sort");
        System.out.println("7. - Radix Sort");
        return sc.nextInt();
    }
    static int[] ipArray(int predefinedBehaviour){
        int[] arr = null;
        int n;
        int ip = 0;
        if (predefinedBehaviour == 0 ) {
            System.out.println("Press :");
            System.out.println("1 - Manual Input\n2 - Random Input");
            ip = sc.nextInt();
        }
        if(predefinedBehaviour == 1 || ip == 1) {
            System.out.print("Please enter number of elements to be sorted :");
            n = sc.nextInt();
            arr = new int[n];
            System.out.println("Please enter array elements (space separated integer values) :");
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
        }
        else if(predefinedBehaviour == 2 || ip == 2) {
            Random r = new Random();
            arr = new int[10];
            for(int i=0;i<10;i++) arr[i] = r.nextInt(1000);// Generates random numbers between 0 and 999
        }
        else{
            System.out.println("wrong predefined behaviour");
        }
        return arr;
    }
    static void further(int[] save, int algorithm)
    {
        System.out.println();
        System.out.println("Press :");
        System.out.println("1 - Try another algorithm with same manual input");
        System.out.println("2 - Try another algorithm with different manual input");
        System.out.println("3 - Try another algorithm with random input");
        System.out.println("4 - Try same algorithm with different manual input");
        System.out.println("5 - Try same algorithm with random input");
        System.out.println("6 - EXIT");
        int loop = sc.nextInt();
        if(loop == 1){
            unknownAlgoCall(save);
        }
        else if(loop == 2){
            unknownAlgoManual_IP_Call();
        }
        else if(loop == 3){
            unknownAlgoRandom_IP_Call();
        }
        else if(loop == 4){
            sameAlgoManual_IP_Call(algorithm);
        }
        else if(loop == 5){
            sameAlgoRandom_IP_Call(algorithm);
        }
        else{
            return;
        }
    }
    static void processAlgo(int[] arr, int algorithm){
        int type = 0;
        int n = arr.length;
        switch (algorithm) {
            case 1:
                bubbleSort(arr);
                break;
            case 2:
                System.out.println("1. - Min Based \n2. - Max Based");
                type = sc.nextInt();
                if (type == 1) selectionSort_min(arr);
                if (type == 2) selectionSort_max(arr);
                break;
            case 3:
                System.out.println("1. - Swap Based \n2. - Shifting Based");
                type = sc.nextInt();
                if (type == 1) insertionSort_swap(arr);
                if (type == 2) insertionSort_shift(arr);
                break;
            case 4:
                System.out.println("1. - Naive Auxiliary Array Based \n2. - Indices Based \n3. - Bottom Up Merge Sort");
                type = sc.nextInt();
                if (type == 1) {
                    System.out.println("It is recursion - DFS(depth first search), so all levels doesnt appears together");
                    rec_start = System.nanoTime();
                    mergeSort_AuxArray(arr, 1);
                    long end = System.nanoTime();
                    System.out.println("Time: " + (end - rec_start) / 1_000_000.0 + " ms\n");
                }
                if (type == 2) {
                    rec_start = System.nanoTime();
                    mergeSort_IndicesBased(arr, 0, n - 1, 1);
                    long end = System.nanoTime();
                    System.out.println("Time: " + (end - rec_start) / 1_000_000.0 + " ms\n");
                }
                if (type == 3) mergeSort_bottomUp(arr);
                break;
            case 5:
                System.out.println("1. - Naive Partition Algo \n2. - Lomuto Partition Based \n3. - Hoare Partition Based");
                type = sc.nextInt();
                if (type == 1) {
                    rec_start = System.nanoTime();
                    quickSort_Naive(arr, 0, n - 1, 1);
                    long end = System.nanoTime();
                    System.out.println("Time: " + (end - rec_start) / 1_000_000.0 + " ms\n");
                }
                if (type == 2) {
                    rec_start = System.nanoTime();
                    quickSort_Lomuto(arr, 0, n - 1, 1);
                    long end = System.nanoTime();
                    System.out.println("Time: " + (end - rec_start) / 1_000_000.0 + " ms\n");
                }
                if (type == 3) {
                    rec_start = System.nanoTime();
                    quickSort_Hoare(arr, 0, n - 1, 1);
                    long end = System.nanoTime();
                    System.out.println("Time: " + (end - rec_start) / 1_000_000.0 + " ms\n");
                }
                break;
            case 6:
                int min = arr[0], max = arr[0];
                for (int ele : arr) {
                    if (ele < min) min = ele;
                    if (ele > max) max = ele;
                }
                countingSort(arr, min, max);
                break;
            case 7:
                radixSort(arr);
                break;
            default:
                System.out.println("Invalid input, please give input in integer format 1 to 7");
        }
        spaceTimeComp(algorithm,type);
    }
    static void spaceTimeComp(int algorithm,int type){
        switch (algorithm) {
            case 1:
                System.out.println("Bubble Sort ::\nTime -> Best: \\u0398(n), Avg: O(n^2), Worst: \\u0398(n^2)" +
                        "\nSpace (all cases) : \\u0398(1) ");
                break;
            case 2:
                if (type == 1)  System.out.println("Min Selection Sort ::\nTime (all cases) : \\u0398(n^2)" +
                        "\nSpace (all cases) : \\u0398(1) ");
                if (type == 2)  System.out.println("Max Selection Sort ::\nTime (all cases) : \\u0398(n^2)" +
                        "\nSpace (all cases) : \\u0398(1) ");
                break;
            case 3:
                if (type == 1)  System.out.println("Swap Insertion Sort ::\nTime -> Best : \\u0398(n), Avg : O(n^2), Worst : \\u0398(n^2)" +
                        "\nSpace (all cases) : \\u0398(1) ");
                if (type == 2)  System.out.println("Shift Insertion Sort ::\nTime -> Best : \\u0398(n), Avg : O(n^2), Worst : \\u0398(n^2)" +
                        "\nSpace (all cases) : \\u0398(1) ");
                break;
            case 4:
                if (type == 1)
                    System.out.println("Naive Merge Sort ::\nTime (all cases) : \\u0398(nlogn)" +
                            "\nSpace (all cases) : O(nlogn) ");
                if (type == 2)
                    System.out.println("Indices Merge Sort ::\nTime (all cases) : \\u0398(nlogn)" +
                            "\nSpace (all cases) : \\u0398(n) ");
                if (type == 3)
                    System.out.println("Bottom Up Merge Sort ::\nTime (all cases) : \\u0398(nlogn)" +
                            "\nSpace (all cases) : O(n) ");
                break;
            case 5:
                if (type == 1)
                    System.out.println("Naive Quick Sort ::\nTime -> Best & avg. : \\u0398(nlogn), Worst : \\u0398(n^2)" +
                            "\nSpace (all cases) : \\u0398(n) ");
                if (type == 2)
                    System.out.println("Lomuto Quick Sort ::\nTime -> Best : \\u0398(nlogn), Avg : O(nlogn), Worst : \\u0398(n^2)" +
                            "\nSpace -> Best & avg. : \\u0398(logn), Worst : \\u0398(n)");
                if (type == 3)
                    System.out.println("Hoare Quick Sort ::\nTime -> Best : \\u0398(nlogn), Avg : O(nlogn), Worst : \\u0398(n^2)" +
                            "\nSpace -> Best & avg. : \\u0398(logn), Worst : \\u0398(n)");
                break;
            case 6:
                System.out.println("Counting Sort :: Time (all cases) : \\u0398(n + k)  Space (all cases) : \\u0398(n + k) \n,where k = range of input");
                break;
            case 7:
                System.out.println("Radix Sort :: Time (all cases) : \\u0398(n + d*(n+b) )  " +
                        "Space (all cases) : \\u0398(n + b) \n,where b = radix(10 for decimal)\nk = range of input\nd = logk to the base b\n");
                break;
        }
    }

    static void unknownAlgoCall(int[] arr){ // WE HAVE INPUT ARRAY
        int n = arr.length;
        int[] save = arr.clone();
        int algorithm = whichAlgo();

        processAlgo(arr,algorithm);

        System.out.println("Here is your sorted array :");
        printArray(arr);

        further(save,algorithm);
    }

    static void unknownAlgoManual_IP_Call(){ // HE DON'T HAVE INPUT ARRAY OR ALGO
        int[] arr = ipArray(1);
        int n = arr.length;
        int[] save = arr.clone();
        int algorithm = whichAlgo();

        processAlgo(arr,algorithm);

        System.out.println("Here is your sorted array :");
        printArray(arr);

        further(save,algorithm);
    }

    static void unknownAlgoRandom_IP_Call(){ // WE DON'T HAVE INPUT ARRAY OR ALGO
        int[] arr = ipArray(2);
        int n = arr.length;
        int[] save = arr.clone();
        int algorithm = whichAlgo();

        processAlgo(arr,algorithm);

        System.out.println("Here is your sorted array :");
        printArray(arr);

        further(save,algorithm);
    }

    static void sameAlgoManual_IP_Call(int algorithm){ // WE HAVE ALGORITHM
        Scanner sc = new Scanner(System.in);
        int[] arr = ipArray(1);
        int n = arr.length;
        int[] save = arr.clone();

        processAlgo(arr,algorithm);

        System.out.println("Here is your sorted array :");
        printArray(arr);

        further(save,algorithm);
    }

    static void sameAlgoRandom_IP_Call(int algorithm){ // WE HAVE ALGORITHM
        Scanner sc = new Scanner(System.in);
        int[] arr = ipArray(2);
        int n = arr.length;
        int[] save = arr.clone();

        processAlgo(arr,algorithm);

        System.out.println("Here is your sorted array :");
        printArray(arr);

        further(save,algorithm);
    }

    public static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }



    // SORTING ALGORITHMS BELOW!



    static void bubbleSort(int[] arr){
        long start = System.nanoTime();
        int step = 1;

        boolean sorted;
        for(int i=0; i<arr.length-1; i++)
        {
            sorted = true;
            for(int j=0; j<arr.length-1-i; j++)
            {
                if(arr[j] > arr[j+1])
                {
                    arr[j] = arr[j + 1] + (arr[j + 1] = arr[j]) - arr[j];
                    sorted = false;
                }
            }
            System.out.println("Step " + step++);
            printArray(arr);
            if(sorted) return;
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }


    // original selection sort - min based
    static void selectionSort_min(int[] arr) {
        long start = System.nanoTime();
        int step = 1;

        int n = arr.length;
        int minIdx;

        for(int i=0; i<n-1; i++)
        {
            minIdx = i;
            for(int j=i+1; j<n; j++)
            {
                if(arr[minIdx]>arr[j]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;

            System.out.println("Step " + step++);
            printArray(arr);
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }

    // original selection sort - max based
    static void selectionSort_max(int[] arr) {
        long start = System.nanoTime();
        int step = 1;

        int n = arr.length;
        int maxIdx;

        for(int i=0; i<n-1; i++)
        {
            maxIdx = n-1-i;
            for(int j=0; j<n-i; j++)
            {
                if(arr[maxIdx]<arr[j]) maxIdx = j;
            }
            int temp = arr[maxIdx];
            arr[maxIdx] = arr[i];
            arr[i] = temp;

            System.out.println("Step " + step++);
            printArray(arr);
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }


    // Insertion Sort - Swap based
    static void insertionSort_swap(int[] arr){
        long start = System.nanoTime();
        int step = 1;

        for(int i=0; i<arr.length; i++)
        {
            for(int j=i; j>0; j--){
                if(arr[j-1] > arr[j])
                    arr[j-1] = arr[j-1] + arr[j] - (arr[j] = arr[j-1]);
            }
            System.out.println("Step " + step++);
            printArray(arr);
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }
    // Insertion Sort - Shifting based
    static void insertionSort_shift(int[] arr){
        long start = System.nanoTime();
        int step = 1;

        for(int i=1; i<arr.length; i++)
        {
            int key = arr[i]; // value to be inserted into the sorted world

            int j = i;
            while(j>0)
            {
                if(arr[j-1] <= key) break;
                arr[j] = arr[j-1]; // right shift
                j--;
            }
            arr[j] = key;

            System.out.println("Step " + step++);
            printArray(arr);
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }


    // Naive Auxiliary Array Based Merge Sort
    static void mergeSort_AuxArray(int[] arr, int level){
        int n = arr.length;
        if(n == 1) return;
        int[] a = new int[n/2];
        int[] b = new int[n-n/2];

        int idx = 0;
        for(int i=0; i<a.length; i++) a[i] = arr[idx++];
        for(int i=0; i<b.length; i++) b[i] = arr[idx++];

        mergeSort_AuxArray(a,level+1);
        mergeSort_AuxArray(b,level+1);

        System.out.println("Entering Rec_Level " + level);
        printArray(arr);

        merge1(a,b,arr);

        System.out.println("Exiting Rec_Level " + level);
        printArray(arr);
    }
    static void merge1(int[] a, int[] b, int[] c){
        int i=0, j=0, k=0;
        while(i<a.length && j<b.length){
            if(a[i] < b[j]) c[k++] = a[i++];
            else c[k++] = b[j++];
        }
        while(i<a.length) c[k++] = a[i++];
        while(j<b.length) c[k++] = b[j++];
    }

    // Optimised Indices Based Merge Sort
    static void mergeSort_IndicesBased(int[] arr, int l, int r,int level){
        if(l >= r) return;
        int mid = l + (r-l)/2;
        mergeSort_IndicesBased(arr,l,mid,level+1);
        mergeSort_IndicesBased(arr,mid+1,r,level+1);

        System.out.println("Entering Rec_Level " + level);
        printArray(arr);

        merge2(arr,l,mid,r);

        System.out.println("Exiting Rec_Level " + level);
        printArray(arr);
    }
    static void merge2(int[] arr, int l, int mid, int r){
        int[] temp = new int[r-l+1];
        int i=l, j=mid+1, k = 0;
        while(i<=mid && j<=r){
            if(arr[i] < arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while(i<=mid) temp[k++] = arr[i++];
        while(j<=r) temp[k++] = arr[j++];
        for(int u=0; u< temp.length; u++){
            arr[l+u] = temp[u];
        }
    }

    // Bottom Up Merge Sort
    static void mergeSort_bottomUp(int[] arr){
        long start = System.nanoTime();
        int step = 1;

        int n = arr.length;
        for(int size = 1; size<n; size *= 2)
        {
            for(int left = 0; left < n-size; left += 2*size)
            {
                int mid = left+size-1;
                int right = Math.min(left+2*size-1, n-1);
                merge2(arr,left,mid,right);
            }

            System.out.println("Step " + step++);
            printArray(arr);

        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }


    // Naive Partition Based QuickSort
    static void quickSort_Naive(int[] arr, int low, int high,int level)
    {
        if(low >= high) return;

        System.out.println("Entering Rec_level " + level);
        printArray(arr);

        int p = partition(arr,low,high);
        System.out.println("Pivot index: " + p);

        System.out.println("Exiting Rec_level " + level);
        printArray(arr);

        quickSort_Naive(arr, low , p-1,level+1);
        quickSort_Naive(arr, p+1, high,level+1);
    }
    static int partition(int[] arr, int l, int h)
    {
        int p = h;
        int[] temp = new int[h-l+1];
        int idx = 0;
        for(int i=l; i<h+1; i++)
        {
            if(arr[i] <= arr[p] && i!=p)
                temp[idx++] = arr[i];
        }
        int store = idx+l;
        temp[idx++] = arr[p];
        for(int i=l; i<h+1; i++)
        {
            if(arr[i] > arr[p] && i!=p)
                temp[idx++] = arr[i];
        }
        idx = 0;
        for(int i=l; i<h+1; i++){
            arr[i] = temp[idx++];
        }
        return store;
    }

    // Lomuto Partition Based QuickSort
    static void quickSort_Lomuto(int[] arr, int low, int high, int level)
    {
        if(low >= high) return;

        System.out.println("Entering Rec_level " + level);
        printArray(arr);

        int p = lpartition(arr,low,high);
        System.out.println("Pivot index: " + p);

        System.out.println("Exiting Rec_level " + level);
        printArray(arr);

        quickSort_Lomuto(arr, low , p-1,level+1);
        quickSort_Lomuto(arr, p+1, high,level+1);
    }
    static int lpartition(int[] arr, int l, int h)
    {
        int p = h;
        int i = l-1;
        for(int j=l; j<h; j++){
            if(arr[j] < arr[p]){
                i++;
                arr[j] = arr[i] + arr[j] - (arr[i] = arr[j]);
            }
        }
        int temp = arr[p];
        arr[p] = arr[i+1];
        arr[i+1] = temp;
        return i+1;
    }

    // Lomuto Partition Based QuickSort
    static void quickSort_Hoare(int[] arr, int low, int high, int level)
    {
        if(low >= high) return;
        System.out.println("Entering Rec_level " + level);
        printArray(arr);

        int p = hpartition(arr,low,high);
        System.out.println("Pivot index: " + p);

        System.out.println("Exiting Rec_level " + level);
        printArray(arr);

        quickSort_Hoare(arr, low , p, level+1);
        quickSort_Hoare(arr, p+1, high, level+1);
    }
    static int hpartition(int[] arr, int l, int h)
    {
        int pivot = arr[l];
        int i = l-1;
        int j = h+1;
        while(true){
            do{ i++; } while(arr[i] < pivot);
            do{ j--; } while(arr[j] > pivot);
            if(i>=j) return j;
            arr[i] = arr[i] + arr[j] - (arr[j] = arr[i]);
        }
    }


    // Counting Sort
    static void countingSort(int[] arr, int min, int max){
        long start = System.nanoTime();
        int step = 1;
        int n = arr.length;
        if(min < 0){
            for(int i=0; i<n; i++) arr[i] = arr[i] - min;
        }
        int k = (max-min)+1;
        int[] count = new int[k]; // Java automatically initializes this to zeros

        for(int i=0; i<n; i++) count[arr[i]]++;

        for(int i=1; i<k; i++)
            count[i] += count[i-1];

        int[] op = new int[n];
        for(int i=n-1; i>=0; i--){
            System.out.println("Step " + step++);
            printArray(op);
            op[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
        for(int i=0; i<n; i++) arr[i] = op[i];

        if(min < 0){
            for(int i=0; i<n; i++) arr[i] = arr[i] + min;
        }

        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }


    // Radix Sort
    static void radixSort(int[] arr)
    {
        long start = System.nanoTime();
        int step = 1;

        int n = arr.length;
        int min = arr[0];
        for(int ele : arr){
            if(ele < min) min = ele;
        }
        if(min < 0) for(int i=0; i<n; i++) arr[i] = arr[i] - min;

        int max = arr[0];
        for(int ele : arr){
            if(ele > max) max = ele;
        }
        for(int exp = 1; max/exp > 0; exp *= 10){
            System.out.println("Step " + step++);
            printArray(arr);
            radixCountingSort(arr,exp);
        }
        if(min < 0) for(int i=0; i<n; i++) arr[i] = arr[i] + min;


        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms\n");
    }
    static void radixCountingSort(int[] arr, int exp){
        int n = arr.length;
        int[] count = new int[10];
        int[] temp = new int[n];

        for(int i=0; i<n; i++) count[(arr[i]/exp)%10]++;
        for(int i=1; i<10; i++) count[i] += count[i-1];

        for(int i=n-1; i>=0; i--){
            temp[count[(arr[i]/exp)%10]-1] = arr[i];
            count[(arr[i]/exp)%10]--;
        }

        for(int i=0; i<n; i++){
            arr[i] = temp[i];
        }
    }
}
