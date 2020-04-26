public class Main {

    public static void main(String[] args) {
	    method1();
        new Thread(new MyRunnableClass()).start();
        new Thread(new MyRunnableClass()).start();
    }

    public static void method1(){
        final int size = 10000000;
        float[] arr = new float[size];
        for (int i = 0; i<arr.length; i++){
            arr[i] = 1;

        }
        long a = System.currentTimeMillis();
        methodCalc(arr);
        System.out.println(System.currentTimeMillis() - a);
    }



    public static void method2(){
        final int size = 10000000;
        final int half = size / 2;
        float[] arr = new float[size];
        for (int i = 0; i<arr.length; i++){
            arr[i] = 1;
        }
        long b = System.currentTimeMillis();
        float[] arr1 = new float[half];
        float[] arr2 = new float[half];
        System.arraycopy(arr,0,arr1,0,half);
        System.arraycopy(arr,0,arr2,0,half);
        methodCalc(arr1);
        methodCalc(arr2);
        System.arraycopy(arr1,0,arr,0,half);
        System.arraycopy(arr2,0,arr,half,half);

        System.out.println(System.currentTimeMillis() - b);
    }


    public static float[] methodCalc(float arr[]){
        for (int i = 0; i<arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    static class MyRunnableClass implements Runnable {

        @Override
        public void run() {
                try {
                    Thread.sleep(0);
                    method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }


}
