package com.ryoma2pick.sandbox.io;

import java.io.*;

public class CopyLines {

    public static void main(String[] args) throws IOException {

        try (BufferedReader in = new BufferedReader(new FileReader("/Users/ryomat/workspace/sandbox/src/main/resources/xanadu.txt"));
             PrintWriter out = new PrintWriter(new FileWriter("/Users/ryomat/workspace/sandbox/src/main/resources/output.txt"));) {
            String line;
            while ((line = in.readLine()) != null) {
                out.write(line);
            }
        }
    }

}