import ore.MapGrid;
import ore.OreSim;
import ore.PropertiesLoader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class OreSimStatisticsTest {
    static String[]logLines;
    @BeforeClass
    public static void setupGame() {
        String propertiesPath = "properties/test2.properties";
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);

        int model = Integer.parseInt(properties.getProperty("map"));
        MapGrid grid = new MapGrid(model);

        String logResult = new OreSim(properties, grid).runApp(true);
        logLines = logResult.split("\n");
    }


    @Test(timeout = 20000)
    public void testStatistics() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("statistics.txt"));
            String pusherMoveStatistics = scanner.nextLine();
            assertEquals("Pusher-1 Moves: 35", pusherMoveStatistics);
            String excavatorMoveStatistics = scanner.nextLine();
            assertEquals("Excavator-1 Moves: 14", excavatorMoveStatistics);
            String excavatorRemoveStatistics = scanner.nextLine();
            assertEquals("Excavator-1 Rock removed: 10", excavatorRemoveStatistics);
            String bulldozerMoveStatistics = scanner.nextLine();
            assertEquals("Bulldozer-1 Moves: 6", bulldozerMoveStatistics);
            String bulldozerRemoveStatistics = scanner.nextLine();
            assertEquals("Bulldozer-1 Clay removed: 5", bulldozerRemoveStatistics);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
