
import ore.MapGrid;
import ore.OreSim;
import ore.PropertiesLoader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class OreSimOriginalTest {
    static String[]logLines;
    @BeforeClass
    public static void setupGame() {
        String propertiesPath = "properties/test1.properties";
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);

        int model = Integer.parseInt(properties.getProperty("map"));
        MapGrid grid = new MapGrid(model);

        String logResult = new OreSim(properties, grid).runApp(true);

        logLines = logResult.split("\n");
    }

    @Test(timeout = 20000)
    public void testOreEnd() {
        // Test for the end result
        int END_LINE_NUMBER = 64;
        String endLine = logLines[END_LINE_NUMBER - 1];
        OreTestData oreTestData = new OreTestData(END_LINE_NUMBER, endLine);
        assertEquals(0, oreTestData.actorLocations.get(OreSim.ElementType.TARGET).size());
        assertEquals(1, oreTestData.actorLocations.get(OreSim.ElementType.PUSHER).size());
        assertEquals("16-7", oreTestData.actorLocations.get(OreSim.ElementType.PUSHER).stream().findFirst().get());
        assertEquals(5, oreTestData.actorLocations.get(OreSim.ElementType.CLAY).size());
        Set<String> clayLocations = new HashSet<>(Arrays.asList("7-5", "7-6", "6-8", "7-8", "8-8"));
        assertEquals(clayLocations, oreTestData.actorLocations.get(OreSim.ElementType.CLAY));
    }

    @Test(timeout = 20000)
    public void testOreBeforePush() {
        // Test for pusher changes the ore to a different target
        int BEFORE_PUSHING_LINE_NUMBER = 26;
        String beforePushingLine = logLines[BEFORE_PUSHING_LINE_NUMBER - 1];
        OreTestData oreTestData = new OreTestData(BEFORE_PUSHING_LINE_NUMBER, beforePushingLine);
        System.out.println("oreTestData = " + oreTestData.actorLocations);
        assertEquals(1, oreTestData.actorLocations.get(OreSim.ElementType.TARGET).size());
        assertEquals(1, oreTestData.actorLocations.get(OreSim.ElementType.PUSHER).size());
        assertEquals("17-6", oreTestData.actorLocations.get(OreSim.ElementType.PUSHER).stream().findFirst().get());
        assertEquals("17-8", oreTestData.actorLocations.get(OreSim.ElementType.TARGET).stream().findFirst().get());
        assertEquals(5, oreTestData.actorLocations.get(OreSim.ElementType.CLAY).size());
        Set<String> clayLocations = new HashSet<>(Arrays.asList("7-5", "7-6", "6-8", "7-8", "8-8"));
        assertEquals(clayLocations, oreTestData.actorLocations.get(OreSim.ElementType.CLAY));
    }

    @Test
    public void testOreAfterPush() {
        int AFTER_PUSHING_LINE_NUMBER = 27;

        String afterPushingLine = logLines[AFTER_PUSHING_LINE_NUMBER - 1];
        OreTestData oreTestData = new OreTestData(AFTER_PUSHING_LINE_NUMBER, afterPushingLine);
        System.out.println("oreTestData = " + oreTestData.actorLocations);
        assertEquals(1, oreTestData.actorLocations.get(OreSim.ElementType.TARGET).size());
        assertEquals(1, oreTestData.actorLocations.get(OreSim.ElementType.PUSHER).size());
        assertEquals("17-7", oreTestData.actorLocations.get(OreSim.ElementType.PUSHER).stream().findFirst().get());
        assertEquals("17-7", oreTestData.actorLocations.get(OreSim.ElementType.TARGET).stream().findFirst().get());
        assertEquals(5, oreTestData.actorLocations.get(OreSim.ElementType.CLAY).size());
        Set<String> clayLocations = new HashSet<>(Arrays.asList("7-5", "7-6", "6-8", "7-8", "8-8"));
        assertEquals(clayLocations, oreTestData.actorLocations.get(OreSim.ElementType.CLAY));
    }
}
