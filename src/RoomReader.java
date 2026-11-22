import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class RoomReader {
    public RoomReader() {

    }

    public static int[][] ReadRoom(int RoomNumber) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/Rooms/room"+RoomNumber+".txt"));
        int[][] arr = new int[17][29];
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 29; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        return arr;
    }

}
