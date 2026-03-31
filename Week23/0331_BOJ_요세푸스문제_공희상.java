import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            numbers.add(i);
        }

        List<Integer> removes = new ArrayList<>();
        StringBuffer sb = new StringBuffer("<");
        int next = K - 1;
        while (!numbers.isEmpty()) {
            Integer number = numbers.remove(next);
            if (!numbers.isEmpty()) {
                next = (next + K - 1) % numbers.size();
                sb.append(number).append(", ");
            } else {
                sb.append(number).append(">");
            }
        }
        System.out.println(sb);
    }
}
