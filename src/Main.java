import dag.Dag;

public class Main {

    public static void main(String[] args) {

        DagGenerator generator = new DagGenerator();
        Dag dag1 = generator.createDag(3);
        dag1.showDag();

        System.out.println("Calculate EST");

        DagProcessor processor = new DagProcessor(dag1);
        processor.calculateEST();

        dag1.showDag();

        System.out.println("Hello World!");
    }
}
