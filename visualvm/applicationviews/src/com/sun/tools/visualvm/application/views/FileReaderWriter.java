package com.sun.tools.visualvm.application.views;

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
                outputFileName = "outputArtem.txt";
            boolean append = true;
            fileWriter = new FileWriter(outputFileName, append);
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
        //TODO если открыть файл на запись и не закрыть до момента крэша, то сохранятся ли данные?
        try {
            fileWriter.append(outputString);
        } catch (IOException e) {
            assert false : "Some problem with output file: IOException.";
        }
    }
}