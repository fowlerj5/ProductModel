import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ArrayList<String> ID = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> Description = new ArrayList<>();
        ArrayList<Double> Cost = new ArrayList<>();
        boolean yn = true;
        ArrayList <String>recs = new ArrayList<>();
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.csv");

        do{
            ID.add(SafeInput.getNonZeroLenString(in, "ID"));
            Name.add(SafeInput.getNonZeroLenString(in, "Name"));
            Description.add(SafeInput.getNonZeroLenString(in, "Description"));
            Cost.add(SafeInput.getDouble(in, "Cost"));
            yn = SafeInput.getYNConfirm(in, "Are there more records to enter?");
        }while(yn);
        for(int n = 0; n < ID.size(); n++){
            recs.add(ID.get(n)+", "+Name.get(n)+", "+Description.get(n)+", "+Cost.get(n));
        }
        try{
            OutputStream out =
                new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(out));
            for(String rec : recs){
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}