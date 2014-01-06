package List3.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by bresiu on 03.01.14.
 */
public class Export {

    public static String toString(List<Stats> stats) {
        String statsString = "";
        for (Stats i : stats) {
            statsString += i.getN() + " " + i.getT() + "\n";
        }
        //System.out.println(statsString);
        return statsString;
    }

    public static void toFile(String stats, String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename + ".dat"));
            writer.write(stats);

        } catch (IOException ignored) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ignored) {
            }
        }
    }
}

