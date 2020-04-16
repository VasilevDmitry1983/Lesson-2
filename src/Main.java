public class Main {

    public static void main(String[] args) {
        int s = 0;
        String[][] array = new String[5][4];
        array[0][0] = "1";
        array[0][1] = "1";
        array[0][2] = "1";
        array[0][3] = "1";
        array[1][0] = "1";
        array[1][1] = "1";
        array[1][2] = "1";
        array[1][3] = "1";
        array[2][0] = "1";
        array[2][1] = " ";
        array[2][2] = "1";
        array[2][3] = "1";
        array[3][0] = "1";
        array[3][1] = "1";
        array[3][2] = "1";
        array[3][3] = "1";


        s = sum(array);
        System.out.println(s);
    }

    public static int sum (String[][] arr) {
        try {
            if (arr[0].length != 4 || arr.length != 4){
                throw new MyArraySizeException();
            }
        }catch (MyArraySizeException exc){
            System.out.println(exc);
        }

        int sum = 0;

        for (int i = 0; i<4; i++){
            for (int j = 0; j<4; j++){
                try {
                    sum = sum + Integer.parseInt(arr[i][j]);
                }catch (NumberFormatException exc){
                    System.out.println("Ошибка: неверный символ arr[" + i + "][" + j +"]" );
                }

            }

        }

        return sum;
    }

}

