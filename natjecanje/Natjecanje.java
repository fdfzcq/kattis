import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;

public class Natjecanje {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
        	String[] a = sc.nextLine().split(" ");
        	String[] b = sc.nextLine().split(" ");
            String[] c = sc.nextLine().split(" ");

            int no_of_teams = Integer.parseInt(a[0]);
            int n_damaged = Integer.parseInt(a[1]);
            int n_reserve = Integer.parseInt(a[2]);

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            int[] damaged_teams = new int[n_damaged];

            int res = 0;

            for(int i=0; i < n_reserve; i++) {
            	int reserve = Integer.parseInt(c[i]);
            	if(reserve > 1) {
            		int value = map.containsKey(reserve-1) ? map.get(reserve-1) + 1 : 1;
            		map.put(reserve-1, value);
            	}
            	if(reserve < no_of_teams) {
            		int value = map.containsKey(reserve+1) ? map.get(reserve+1) + 1 : 1;
            		map.put(reserve+1, value);
            	}
            }

            for(int i=0; i < n_damaged; i++) {
            	int damaged = Integer.parseInt(b[i]);
            	damaged_teams[i] = damaged;
            }

            Arrays.sort(damaged_teams);

            for(int i=0; i < n_damaged; i++) {
                int damaged = damaged_teams[i];
                if(map.containsKey(damaged) && map.get(damaged) > 0) {
                    int value = map.get(damaged) - 1;
                    map.put(damaged, value);
                    if(map.containsKey(damaged-2) && map.get(damaged-2) > 0) {
                        value = map.get(damaged-2) - 1;
                        map.put(damaged-2, value);
                    } else if(map.containsKey(damaged+2) && map.get(damaged+2) > 0) {
                        value = map.get(damaged+2) - 1;
                        map.put(damaged+2, value);
                    }
                } else {
                    res ++;
                }
            }

            System.out.println(res);

            // if(n_damaged <= n_reserve){
            // 	System.out.println(0);
            // } else {
            // 	System.out.println(n_damaged - n_reserve);
            // }
            // sc.nextLine();
            // sc.nextLine();
            // int[] damaged_teams = new Int(n_damaged)
            // int[] reserve_teams = new Int(n_reserve)

            // System.out.println(a[0]);
        }
    }

}