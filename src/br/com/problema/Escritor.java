package br.com.problema;

class Escritor extends Thread {
	 int C_Leitor = 0;
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * 1000));
                Leitores_Escritores.mutex.acquire();
                // escritoresEsperando++;

                System.out.println("Algum escritor irá escrever");
                
                if(Leitores_Escritores.escritoresEscrevendo == 0 ){
                	Leitores_Escritores.escritoresEscrevendo++;
                	C_Leitor++;
                	Leitores_Escritores.db.acquire();
                    Thread.sleep((int) (Math.random() * 1000));

                    Leitores_Escritores.number++;
                    Leitores_Escritores.setEspaco(Leitores_Escritores.number);
                    System.out.println("Leitor "+ C_Leitor +" leu : " + Leitores_Escritores.number);


                    Leitores_Escritores.db.release();
                    Leitores_Escritores.escritoresEscrevendo--;
                    System.out.println("Escritor Terminou de escrever" );
                }
                Leitores_Escritores.Leia.release();
                Leitores_Escritores.mutex.release();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}