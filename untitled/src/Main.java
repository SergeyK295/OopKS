import java.io.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;

        try (PrintWriter writer = new PrintWriter("table.html")) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\КС\\Desktop\\asd2.csv"), "windows-1251"));
            writer.println("<meta charset=\"UTF-8\">");
            writer.println("<table>");

            boolean isCellOver = true;

            while (reader.ready()) {
                if (isCellOver) {
                    writer.println("<tr>");
                }

                String line = reader.readLine();
                String[] cells = Pattern.compile("(?<=;)")
                        .splitAsStream(line)
                        .filter(cell -> !cell.isEmpty())
                        .toArray(String[]::new);

                for (int i = 0; i < cells.length; i++) {
                    if (isCellOver) {
                        writer.println("<td>");
                    }

                    int doubleQuotesCount = cells[i].length() - cells[i].replace(String.valueOf('"'), "").length();

                    if (doubleQuotesCount % 2 == 1) {
                        isCellOver = !isCellOver;
                    }

                    if (cells[i].startsWith("\"") && !cells[i].endsWith("\"")) {
                        cells[i] = cells[i].substring(1);
                    }

                    if (isCellOver) {
                        if (i != cells.length - 1) {
                            cells[i] = cells[i].substring(0, cells[i].length() - 1);
                        }

                        if (cells[i].startsWith("\"") && cells[i].endsWith("\"")) {
                            cells[i] = cells[i].substring(1, cells[i].length() - 1);
                        } else if (cells[i].endsWith("\"")) {
                            cells[i] = cells[i].substring(0, cells[i].length() - 1);
                        } else if (cells[i].startsWith("\"")) {
                            cells[i] = cells[i].substring(1);
                        }
                    }

                    cells[i] = cells[i].replace("\"\"", "\"");
                    writer.println(cells[i]);

                    if (isCellOver) {
                        writer.println("</td>");
                    }
                }

                if (!isCellOver) {
                    writer.println("<br/>");
                }

                if (isCellOver) {
                    writer.println("</tr>");
                }
            }

            writer.println("</table>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
