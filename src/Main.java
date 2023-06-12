public class Main {
    public static void main(String[] args) throws Exception {
        FileOperation fileOperation = new FileOperation("toys.txt");
        Actions actions = new Actions(fileOperation);
        View view = new View(actions);
        view.run();

    }
}