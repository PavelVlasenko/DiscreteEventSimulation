import dag.Dag;

public class Main {

    public static void main(String[] args) {

        DagGenerator generator = new DagGenerator();
        Dag dag1 = generator.createDag(20);
        dag1.showDag();

        System.out.println("Hello World!");
    }
}
