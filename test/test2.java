package test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class test2 {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\КС\\Desktop\\asd2.csv"), "windows-1251");


        try (PrintWriter writer = new PrintWriter("t.html")) {
            writer.println(" <meta charset=\"UTF-8\">");
            writer.println("<table>");

            scanner.useDelimiter(";");


            while (scanner.hasNext()) {
                writer.println(scanner.next() + " ");
            }





            writer.println("<tr> ");
            writer.println("<td>");


            writer.println("мир");


            writer.println("</td>");
            writer.println("</tr> ");


            writer.println("</table>");
        }


    }


}
