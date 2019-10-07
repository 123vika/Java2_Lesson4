public class ABC {

     Runnable a,b,c ;

     private volatile char currentLetter = 'A';

     void go () {
         Object lock = new Object();

         a = () -> {
             synchronized (lock){
                 for (int loop=0; loop<5; loop++) {


                     try {
                         Thread.sleep(1000);

                         while (currentLetter!='A') {
                             lock.wait();
                         }
                         System.out.print("A");

                         currentLetter = 'B';

                         lock.notifyAll();

                     }catch(InterruptedException e){
                         e.printStackTrace();
                     }
                 }
             }
         };

         b = () -> {
             synchronized (lock){
                 for (int loop=0; loop<5; loop++) {

                     try {
                         Thread.sleep(1000);

                         while (currentLetter!='B') {
                             lock.wait();
                         }
                         System.out.print("B");
                         currentLetter = 'C';
                         lock.notifyAll();

                     }catch(InterruptedException e){
                         e.printStackTrace();
                     }
                 }
             }
         };

         c = () -> {
             synchronized (lock){

                 for (int loop=0; loop<5; loop++) {
                     //   try {
                    //     lock.wait();
                  //   } catch (InterruptedException e) {
                   //      e.printStackTrace();
                   //  }

                     try {
                         Thread.sleep(1000);

                         while (currentLetter!='C') {
                             lock.wait();
                         }

                         System.out.println("C");

                         currentLetter = 'A';


                         lock.notifyAll();

                     }catch(InterruptedException e){
                         e.printStackTrace();
                     }
                 }
             }
         };


         new Thread (a).start();
         new Thread (b).start();
         new Thread (c).start();

     }

    public static void main(String[] args) {
        new ABC().go();
    }
     }

