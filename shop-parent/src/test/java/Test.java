import java.util.Arrays;

public class Test {
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {

        String str1 = "CBA";
        String str2 = "CBBBAAADD";

        //分别计算出第一个,二个,三个字符在该字符串中出现的次数
        char[] s2s = str2.toCharArray();
        int[] arr = new int[str1.length()];
        for (int i = 0; i < str1.length(); i++) {
            for (char s2 : s2s) {
                if(s2==str1.charAt(i)){
                    arr[i] = arr[i]+1;
                }
            }
        }
        //判断数组中是否存在0,如果存在就直接返回没有,如果不存在就直接返回最小出现的次数的个数
        Arrays.sort(arr);
        if(arr[0]==0){
            System.out.println("此字符串组成不了");
        }else{
            System.out.println("此字符串可以拼接成:"+arr[0]+"个第一组");
        }

    }
}
