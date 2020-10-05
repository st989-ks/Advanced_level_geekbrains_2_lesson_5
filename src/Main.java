import java.util.Arrays;

public class Main {
    private static final int size = 10000000;
    private static final int h = size / 2;
    private static float[] arr = new float[size];

    public static void replacement(float[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin( 0.2f + i / 5 )
                    * Math.cos( 0.2f + i / 5 ) * Math.cos( 0.4f + i / 2 ));
        }

    }

    public static void replacementSpeed() {

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy( arr, 0, a1, 0, h );
        System.arraycopy( arr, h, a2, 0, h );



        Thread thread1 = new Thread( new Runnable() {
            @Override
            public void run() {
                replacement( a1 );
            }
        });
        Thread thread2 = new Thread( new Runnable() {
            @Override
            public void run() {
                replacement( a2 );
            }
        });

        thread2.start();
        thread1.start();

//        new Thread( () -> replacement( a1 ) ).start();
//        new Thread( () -> replacement( a2 ) ).start();

        System.arraycopy( a1, 0, arr, 0, h );
        System.arraycopy( a2, 0, arr, h, h );

    }

    public static void main(String[] args) {

        Arrays.fill( arr, 1 );
        long a = System.currentTimeMillis();
        replacement( arr );                                     // один поток
        System.out.println( System.currentTimeMillis() - a );

//        Arrays.fill( arr, 1 );
//        a = System.currentTimeMillis();
//        new Thread( () -> replacement( arr ) ).start();
//        System.out.println( System.currentTimeMillis() - a );

//        Arrays.fill( arr, 1 );
//        a = System.currentTimeMillis();
//        replacementSpeed();
//        System.out.println( System.currentTimeMillis() - a );


    }


}
