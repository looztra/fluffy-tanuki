package net.devlab722.ft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class FluffyTanuki {

    private final long endOfLineWaitTimeMs;
    private final long endOfCharMaxWaitTimeMs;
    private final long endOfCharMinWaitTimeMs;

    public FluffyTanuki(long endOfLineWaitTimeMs, long endOfCharMinWaitTimeMs, long endOfCharMaxWaitTimeMs) {
        this.endOfLineWaitTimeMs = endOfLineWaitTimeMs;
        this.endOfCharMinWaitTimeMs = endOfCharMinWaitTimeMs;
        this.endOfCharMaxWaitTimeMs = endOfCharMaxWaitTimeMs;
    }


    void readInputStream(InputStream inputStream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        readLines(r);
    }

    private void readLines(BufferedReader r) throws IOException {
        String line;
        while ((line = r.readLine()) != null) {
            readAndPrintLine(line);
            waitForDuration(endOfLineWaitTimeMs);
        }
    }

    private void readAndPrintLine(String line) {
        printChars(line);
        System.out.println();
    }

    private void printChars(String word) {
        word.chars().forEach(c -> {
            System.out.print((char) c);
            waitForDuration(getRandomWaitTimeMsBetweenChars());
        });
    }

    private void waitForDuration(long durationInMillis) {
        try {
            Thread.sleep(durationInMillis);
        } catch (InterruptedException shouldNeverHappen) {
            shouldNeverHappen.printStackTrace();
        }
    }

    private long getRandomWaitTimeMsBetweenChars() {
        return ThreadLocalRandom.current().nextLong(endOfCharMinWaitTimeMs, endOfCharMaxWaitTimeMs + 1);
    }

    public static void main(String[] args) throws IOException {
        FluffyTanuki ft = new FluffyTanuki(400L, 60L, 70L);
        ft.readInputStream(new FileInputStream(new File("mail-presentation-draft.txt")));
    }
}