public class Test11022 {
    public int[] shuffle(int[] nums, int n) {

        // n次循环
        int i = n, k = 1;
        while (i < nums.length) {
            // nums[i] ---> nums[k]
            int temp = nums[i];
            for (int j = i; j > k; j--) {
                nums[j] = nums[j - 1];
            }
            nums[k] = temp;
            i++;
            k += 2;
        }

        return nums;
    }

    public static void main(String[] args) {
        Test11022 t = new Test11022();
        int[] r = t.shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3);
        t.print("r : ", r);
    }

    private void print(String s, int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        System.out.println(s + sb.toString());
    }
}
