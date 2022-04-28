import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubSetSumPbDynProg {
    public static boolean[][] dpTable;
    public static List<Integer> numbers=new ArrayList<>();
    public static int sum;
    //this method read the sum value and numbers from keyboard
    //numbers are given as String then we use split to extract numbers
    public static boolean getNumbersAndSum()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter list of positive numbers");
        boolean result=true;
        try {
            //get the sequence of numbers as String separate by comma
            String expression=sc.next();
            //split the introduced string to obtain numbers
            String[] tabNumbers=expression.split(",");
            for(String x:tabNumbers) {
                //convert from string to Integer
                Integer val=Integer.valueOf(x);
                //or
                //int val=Integer.parseInt();
                if(val<0) //numbers must be positives
                    throw new Exception("All the set numbers must be  positives");
                numbers.add(val);
            }
            System.out.print("Give the sum value : ");
            sum=sc.nextInt();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            result=false;
        }
        return result;

    }
    public static void fillDpTable()
    {
        dpTable=new boolean[numbers.size()+1][sum+1];
        //The first line of the dpTable matrix is false except the first column
        //from an empty set we can not find sum>0
        for(int i=1;i<=sum;i++)
            dpTable[0][i]=false;
        //the fist column is True
        //zero is on all subsets
        for(int i=0;i<dpTable.length;i++)
            dpTable[i][0]=true;
        //fill the others cases in the dpTable matrix
        for(int i=1;i<dpTable.length;i++)
            for(int j=1;j<dpTable[i].length;j++)
            {
                if(j<numbers.get(i-1)) //we save the previous found sums
                    dpTable[i][j]=dpTable[i-1][j];
                else
                    if(dpTable[i-1][j])
                        dpTable[i][j]=true;
                    else
                        dpTable[i][j]=dpTable[i-1][j-numbers.get(i-1)];
            }
    }
    public static boolean hasSolution(){

        return dpTable[numbers.size()][sum];
    }
    public static void printSolution(){
        int i=numbers.size();
        int j=sum;
        while(i>0&&j>0)
        {
            if(dpTable[i][j]&&dpTable[i-1][j])//if the current row of the column j is true and the row above is also true
                //so we just go to the line above
                i--;
            else
            {
                System.out.println("include the number : "+ numbers.get(i-1));
                j-=numbers.get(i-1);
                i--;
            }
        }
    }
}
