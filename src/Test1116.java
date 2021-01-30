public class Test1116 {
    public static void main(String[] args) {
        int[] src = new int[] {0, 1, 2, 3 ,4, 5, 6, 7, 8, 9};
        // {0, 0, 0, 0, 0, 0, 0, 0}
        // -->
        // {0, 0, 0, 0, 2, 3 ,4, 0}
        int[] dest = new int[8];
        System.arraycopy(src, 2, dest,3, 4);

        for (int i = 0; i < dest.length; i++) {
            System.out.println(String.format("dest[%d] = %d", i, dest[i]));
        }
    }
}
