import com.sun.org.apache.xml.internal.utils.SystemIDResolver;

public class Test1103 {

    public static void main(String[] args) {
//        boolean b = new Test1103().validMountainArray(new int[]{0,1,2,3,4,5,6,7,8,9});
        int[] r = new Test1103().runningSum(new int[]{3,1,2,10,1});
        for (int i : r) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3 || A[0] > A[1] || A[A.length - 2] < A[A.length - 1]) {
            return false;
        }
        int i = 0;      // 山顶数（最大数）的index
        while (i < A.length - 1) {
            if (A[i] < A[i + 1]) {
                i++;
            } else if (A[i] == A[i + 1]) {
                return false;
            } else {
                break;
            }
        }
        System.out.println("i = " + i);

        // 此时A[i] > A[i + 1]
        while (i < A.length - 1) {
            if (A[i] > A[i + 1]) {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        return nums;
    }
}
