package example.day01.consoleMvc;

import java.util.List;
import java.util.Scanner;

public class ConsoleStart {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while( true ){
            doGet();
            doPost();
        }
    }// m end
    public static void doGet(){
        ConsoleController controller = new ConsoleController();
        List<ConsoleDto> result = controller.doGet();
        System.out.println( result );
    }
    public static void doPost(){
        System.out.print("title : ");String title = scanner.next();
        ConsoleController controller = new ConsoleController();
        boolean result = controller.doPost( title );
        System.out.println( result );
    }
}
