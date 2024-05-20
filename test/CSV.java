package test;

import java.io.*;

public class CSV {
    public static void main(String[] args) {
        BufferedReader reader;

        try (PrintWriter writer = new PrintWriter("text.html")) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\КС\\Desktop\\asd2.csv"), "windows-1251"));

            writer.println(" <meta charset=\"UTF-8\">");
            writer.println("<table>");


            int k = 0;

            while (reader.ready()) {

                if (k % 2 == 0) {
                    writer.println("<tr>");
                }


                String line = reader.readLine();

                String[] s = line.split("");

                for (int i = 0; i < s.length; i++) {
                    if (k % 2 == 0) {
                        writer.println("<td>");
                    }

                    char ch = '"';
                    k += s[i].length() - s[i].replace(String.valueOf(ch), "").length();

                    if (s[i].startsWith("\"") && !s[i].endsWith("\"")) {
                        s[i] = s[i].substring(1);
                    }

                    if (k % 2 == 0) {
                        if (s[i].startsWith("\"") && s[i].endsWith("\"")) {
                            s[i] = s[i].substring(1, s[i].length() - 1);
                        } else if (s[i].endsWith("\"")) {
                            s[i] = s[i].substring(0, s[i].length() - 1);
                        } else if (s[i].startsWith("\"")) {
                            s[i] = s[i].substring(1);
                        }
                    }


                    s[i] = s[i].replace("\"\"", "\"");


                    writer.println(s[i]);


                    if (k % 2 != 0) {
                        writer.println("<br/>");
                    }


                    if (k % 2 == 0) {
                        writer.println("</td>");
                    }
                }

                if (k % 2 == 0) {
                    writer.println("</tr>");
                }

            }
            writer.println("</table>");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
