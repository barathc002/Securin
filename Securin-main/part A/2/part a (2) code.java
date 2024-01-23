public class Main{
         public static void main(String args[]){
                    int[][] d_m_c = new int[6][6];
                     for(int i=1;i<=6;i++){ 
                        for(int j=1;j<=6;j++){ 
                              int sum = i+j;
                             d_m_c[i-1][j-1] = sum; 
  } 
} 
System.out.println("Possible combinations of Distribution:") ;
for(int[] r : d_m_c){ 
  for (int i : r){ 
             System.out.print(i+" ");
}
System.out.println(); 
}
}
}
