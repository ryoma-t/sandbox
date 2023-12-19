package com.ryoma2pick.sandbox.io;

import java.io.*;

public class CopyBytes {

    public static void main(String[] args) throws IOException {

        try (FileReader in = new FileReader("/Users/ryomat/workspace/sandbox/src/main/resources/xanadu.txt");
             FileWriter out = new FileWriter("/Users/ryomat/workspace/sandbox/src/main/resources/output.txt");) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }
    }

}