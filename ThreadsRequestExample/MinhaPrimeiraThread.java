package ThreadsRequestExample;

public class MinhaPrimeiraThread {

    public static void main(String[] args) {

        new SaoPaulino("Kaue").start();
        new SaoPaulino("Rafael").start();
        new SaoPaulino("Yan").start();

        System.out.println("chamei duas threads acima");
        try {
            Thread.sleep(2345);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("acabou o soninho");
    }

}
