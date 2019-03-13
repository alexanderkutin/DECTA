
public class MyExceptionTest {

    public static void main(String[] args) {

        int i;
        long time;

        time = System.currentTimeMillis();
        for (i = 1; i < 50000000; i++) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {

            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Runtime exception took " + time + " ms");

        time = System.currentTimeMillis();
        for (i = 1; i < 50000000; i++) {
            try {
                throw new MyException();
            } catch (Exception e) {

            }
        }
        time = System.currentTimeMillis() - time;
        System.out.println("My exception took " + time + " ms");
    }
}
