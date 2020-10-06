import java.util.Arrays;

public class Main {
    private static final int size = 10_000_000;
    private static final int h = size / 2;
    private static float[] arr = new float[size];

    // в методе replacement, boolean True для одного потока (или для первого потока) False для второго потока
    public static void replacement(float[] arr, boolean x) {

        //y для второго потока так как size / 2 = 5_000_000
        int y;
        y = x ? 0 : h;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin( 0.2f + (i + y) / 5 )
                    * Math.cos( 0.2f + (i + y) / 5 ) * Math.cos( 0.4f + (i + y) / 2 ));
        }
    }

    public static void replacementSpeed() throws InterruptedException {

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy( arr, 0, a1, 0, h );
        System.arraycopy( arr, h, a2, 0, h );

        Thread thread1 = new Thread( () -> replacement( a1, true ) );
        Thread thread2 = new Thread( () -> replacement( a2, false ) );

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy( a1, 0, arr, 0, h );
        System.arraycopy( a2, 0, arr, h, h );
    }

    public static void main(String[] args) throws InterruptedException {

        Arrays.fill( arr, 1 );
        long a = System.currentTimeMillis();
        replacement( arr, true );
        System.out.println( "Один поток: " + (System.currentTimeMillis() - a) );

        Arrays.fill( arr, 1 );
        a = System.currentTimeMillis();
        replacementSpeed();
        System.out.println( "Многопоточность: " + (System.currentTimeMillis() - a) );
    }


}
