import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p03_RecursiveDrawing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        drawing(n);
    }

    private static void drawing(int n) {
        if (n <= 0) {
            return;
        }
        System.out.println(new String(new char[n]).replace('\0', '*'));
        drawing(n - 1);
        System.out.println(new String(new char[n]).replace('\0', '#'));
    }
}
