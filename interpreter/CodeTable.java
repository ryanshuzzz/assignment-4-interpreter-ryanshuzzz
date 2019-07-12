package interpreter;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
public class CodeTable {
    private static HashMap<String, String> codeMap = new HashMap<>();
    private static String byteCodesTXT = "interpreter/byteCodes.txt";

    public static void init() {
        populateMap();
    }

    private static void populateMap(){

        try {
            File byteCodesFile = new File(byteCodesTXT);
            Scanner inputFile = new Scanner(byteCodesFile);
            while(inputFile.hasNext()){
                String nextLine = inputFile.nextLine();
                String[] readLine = nextLine.split(", ");
                codeMap.put(readLine[0], readLine[1]);

            }
            inputFile.close();
        }catch (FileNotFoundException e){
            System.err.println("FILE NOT FOUND: "+ byteCodesTXT);
        }
    }
    public static String getCode(String code){
        if (codeMap.containsKey(code)) {
            return codeMap.get(code);
        }else {
            System.err.println("Error finding ByteCode" + code);
            return null;
        }
    }

}
