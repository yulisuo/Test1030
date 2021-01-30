import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test1102 {

    public static void main(String[] args) {
        new Test1102().intersection4(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
    }

    public int[] intersection(int[] nums1, int[] nums2) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : nums1){
            boolean exist = false;
            for (int j : nums2) {
                if (i == j) {
                    exist = true;
                    break;
                }
            }
            if (exist && !arrayList.contains(i)) {
                arrayList.add(i);
            }
        }
        int[] ret = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            ret[i] = arrayList.get(i);
        }
        return ret;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        Set<Integer> set = new HashSet<>(nums1.length);
        for (int i : nums1){
            set.add(i);
        }
        for (int i : nums2){
            if (set.contains(i) && !arrayList.contains(i)) {
                arrayList.add(i);
            }
        }
        int[] ret = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            ret[i] = arrayList.get(i);
        }
        return ret;
    }

    public int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }

    // 第一个参数的size小于第二个参数的size
    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            // 第一个参数的size小于第二个参数的size
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<Integer>();
        // 遍历size小的
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        return intersection;
    }

    public int[] intersection4(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        Set<Integer> result = new HashSet<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }
        }

        int[] r = new int[result.size()];
        int index = 0;
        for (int resultInt: result){
            r[index] = resultInt;
            index++;
        }
        return r;
    }
}
