public class Application {
    public static void main(String[] args) {
        boolean result;
        do {
            result = SubSetSumPbDynProg.getNumbersAndSum();
        }while(!result);
            SubSetSumPbDynProg.fillDpTable();
            if(SubSetSumPbDynProg.hasSolution()) {
                System.out.println("There is at least one solution :");
                SubSetSumPbDynProg.printSolution();
            }
            else
                System.out.println("There is no feasible solution for sum = "+SubSetSumPbDynProg.sum);
    }
}
