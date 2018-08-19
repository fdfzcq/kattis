import java.util.*;
class Incognito{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int noOfTestCases = sc.nextInt();
    for(int i=0;i<noOfTestCases;i++){
      incognito(sc);
    }
    sc.close();
  }
  public static void incognito(Scanner sc){
    int noOfLines = sc.nextInt();
    HashMap<String, HashMap<String, Integer>> hm =
      new HashMap<String, HashMap<String, Integer>>();
    for(int i=0;i<noOfLines;i++){
      String description = sc.next();
      String category = sc.next();
      HashMap<String, Integer> dis = hm.containsKey(category) ?
        hm.get(category) : new HashMap<String, Integer>();
      dis.put(description, 1);
      hm.put(category, dis);
    }
    Stack<Integer> sizeStack = new Stack<Integer>();
    int i = 0;
    for(String key:hm.keySet()){
      sizeStack.push(hm.get(key).size());
      i++;
    }
    System.out.println(getCombination(sizeStack));
  }
  // [2,1]
  public static int getCombination(Stack<Integer> sizeStack){
    if(sizeStack.size() == 0){return 0;}
    int value = sizeStack.peek();
    sizeStack.pop();
    int next = getCombination(sizeStack);
    return value * (1 + next) + next;
  }
}
