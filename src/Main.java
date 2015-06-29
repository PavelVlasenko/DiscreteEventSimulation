import dag.Dag;

public class Main {

    public static void main(String[] args) {

        DagGenerator generator = new DagGenerator();
        Dag dag1 = generator.createDag(30);
        dag1.showDag();

        System.out.println("Calculate EST");

        DagProcessor processor = new DagProcessor(dag1);
        processor.calculateEST();

        System.out.println("Calculate LST");
        processor.calculateLST();

        dag1.showDag();

        System.out.println("Create taskList");
        processor.calculateTaskList();
        dag1.showTaskList();

        System.out.println("Calculate CCR");
        processor.calculateCCR();

        System.out.println("Calculate JobRank");
        processor.calculateJobRank();
        dag1.showDag();
        System.out.println(dag1);

        System.out.println("Hello World!");
    }
}
