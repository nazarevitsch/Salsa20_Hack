import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    private static File file = new File("src/main/resources/text.txt");

    public static void main(String[] args) {
        List<String> lines = readFile();
        String line = "no traveller returns, puzzles the will";

        List<String> result =  xor(lines, 11, line);
        print(result);
    }


    public static List<String> xor(List<String> lines, int index, String line) {
        List<String> result = new ArrayList<>();

        byte[] a = DatatypeConverter.parseHexBinary(lines.get(index - 1));
        byte[] c = line.getBytes();

        for (String s : lines) {
            byte[] b = DatatypeConverter.parseHexBinary(s);
            byte[] res = new byte[line.length()];

            for (int l = 0; l < Math.min(Math.min(a.length, b.length), line.length()); l++) {
                res[l] = (byte) ((byte) (b[l] ^ a[l]) ^ c[l]);
            }
            result.add(new String(res, StandardCharsets.UTF_8));
        }
        return result;
    }

    public static void print(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) +  ")=> " + lines.get(i));
        }
    }

    public static List<String> readFile() {
        List<String> lines = new ArrayList<String>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("No such file...");
            System.exit(0);
        }
        return lines;
    }
}
