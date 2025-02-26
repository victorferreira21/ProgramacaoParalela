package ThreadsRequestExample;

public class SaoPaulino extends Thread{

    String nomeTorcedor;

    public SaoPaulino(String nome){
        this.nomeTorcedor = nome;
    }

    public void run(){
        System.out.println(this.nomeTorcedor + " - "+ Thread.currentThread().getId());
    }

}
