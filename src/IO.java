import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;

public class IO {
    
    //Custom Tuple
    public class Pair<one, two>{
        public one lines;
        public two lineCount;
    }

    public IO() {

    }

    public char[][] readFileToMatrix(String path){
        Pair<List<String>, Integer> pair = null;

        if(path.isBlank())
            path = getInput("Default is fileTest.txt in current directory\nPath of input file:");

        try{
            pair = readFile(path);
        } catch(Exception e){
            IO.print(e.getMessage());
            return new char[0][0];
        }
        int lineCount = pair.lineCount;
        int longestLine = 0;
        List<String> lines = pair.lines;

        //Gets longest line
        for(String line : lines){
            int curLength = line.length();
            if(curLength > longestLine)
                longestLine = curLength;
        }

        char[][] fileContent = new char[lineCount][longestLine];

        int i = 0;
        for(String line : lines){
            char[] shortArray = line.toCharArray();
            char[] longArray = new char[longestLine];

            //Copy short array into one with the highest lenght
            int j = 0;
            for(char letter : shortArray){
                longArray[j] = letter;
                j++;
            }
            fileContent[i] = longArray;
            i++;
        }
        return fileContent;
    }

    public String readFileToString(String path) throws IOException{
        Pair<List<String>, Integer> pair = readFile(path);
        List<String> lines = pair.lines;

        String fileContent = "";
        for(String line : lines){
            fileContent += line + "\n";
        }
        return fileContent;
    }

    private Pair<List<String>, Integer> readFile(String path) throws IOException{
        String file = path==null || path.isBlank() ? "fileTest.txt" : path;
        Scanner scanner = new Scanner(new File(file));
        List<String> lines = new ArrayList<String>();

        int lineCount = 0;
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            lines.add(line);
            lineCount++;
        }
        scanner.close();

        Pair<List<String>, Integer> ergebnis = new Pair<List<String>, Integer>();
        ergebnis.lines = lines;
        ergebnis.lineCount = lineCount;

        return ergebnis;
    }

    public static void writeCoordinatesToFile(String filePath, List<Coordinate> shortestPath){
        char[][] matrix = GraphConverter.pathToMatrix(shortestPath);
        String content = "";

        if(filePath.isBlank())
            filePath = IO.getInput("Default is output.txt in current directory\nPath of output file:");

        for(char[] vector : matrix){
            for(char letter : vector){
                content += letter;
            }
            content += "\n";
        }
        try{
            writeFile(filePath, content);
        } catch(Exception e){
            print(e.getMessage());
        }
    }

    public static void writeFile(String path, String content){
        String filePath = path==null || path.isBlank() ? "output.txt" : path;
        File file = new File(filePath);
        try{
            if (file.createNewFile()) {
                print("File created: " + file.getName());
                try {
                    FileWriter writer = new FileWriter(filePath);
                    writer.write(content);
                    writer.close();
                } catch (IOException e) {
                    print("An error writing to the file occurred.");
                    print(e.getMessage());
                }
            } else {
            System.out.println("File already exists at location \"" + filePath + "\".\nPls delete it... I am too scared to write to it");
            }
        } catch(Exception e){
            print("Something went wrong while writing the file. Can't tell you what but it went wrong for sure!");
            print(e.getMessage());
        }
        
    }

    public static void print(String ausgabe){
        System.out.println(ausgabe);
    }

    public static String getInput(String message){
        Console console = System.console();
         
        if(console == null) {
            print("Console is not available to current JVM process");
            return "";
        }
 
        Reader consoleReader = console.reader();
        Scanner scanner = new Scanner(consoleReader);
 
        if(message != null){
            if(!message.isBlank())
                print(message);
        }
        String input = scanner.nextLine();
 
        scanner.close();

        return input;
    }
}
