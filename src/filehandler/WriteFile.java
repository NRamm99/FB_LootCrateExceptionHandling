package filehandler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
  public static void main(String[] args) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("example.txt"))) {
    bw.write("Hej Verden!");
    bw.newLine();
    bw.write("Dette er en test.");
  } catch (IOException e) {
    System.out.println("Error writing to file: " + e.getMessage());
  }
 }
}