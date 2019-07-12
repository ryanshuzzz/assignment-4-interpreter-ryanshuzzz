package interpreter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ByteCodeLoader {
    private Scanner input;
    private HashMap<Integer, String[]> programMap = new HashMap<>();
    private int lineCount;

    public ByteCodeLoader(String codeFile) throws IOException {
        File inputFile = new File(codeFile);
        input = new Scanner(inputFile);
    }

    public Program loadCodes() {

        lineCount = 0;
        while(input.hasNext()) {
            lineCount++;
            String nextLine = input.nextLine();
            parseLine(nextLine);
        }
        Program createProgram = new Program();
        Program.program(programMap);
        return createProgram;
    }

    private void parseLine(String nextLine) {
        String input[];
        input = nextLine.split("\\s+");

        programMap.put(lineCount, input);

    }
}
