package com.sun.tools.visualvm.application.views;

import com.sun.tools.visualvm.application.views.monitor.LogTrigger;

import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {
    private static FileWriter fileWriter = null;
    private static String outputFileName = null;

    public FileReaderWriter() {
        initiateFileWriter();
    }

    public FileReaderWriter(String outputFileName) {
        this.outputFileName = outputFileName;
        initiateFileWriter();
    }

    private void initiateFileWriter() {
        try {
            if (outputFileName == null)
                outputFileName = "output.txt";
            boolean append = true;
            fileWriter = new FileWriter(LogTrigger.directory + outputFileName, append);
        } catch (IOException e) {
            close();
            assert false : "Some problem with output file: IOException.";
        }
    }

    public void close() {
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                assert false : "Trying to close FileWriter, but there is no FileWriter.";
            }
        }
    }

    public void appendToOutputFile(String outputString) {
        try {
            fileWriter.append(outputString);
        } catch (IOException e) {
            assert false : "Some problem with output file: IOException.";
        }
    }
}