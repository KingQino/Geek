package io.github.kingqino.week07.plan;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PlanB {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("/tmp/initData.txt");
            BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

            int i = 1_000_000;
            while (i-- > 0) {
                String line = (long) (Math.random() * 1_000_000_000) + "\t" + (long) (Math.random() * 1_000_000_000);
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
