package fr.uge.poo.cmdline.ex1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CmdLineParserTest {

    @Test
    void registerNullTest() {
        var cmdParser = new CmdLineParser();

        assertAll(() -> {
            assertThrows(NullPointerException.class,() -> cmdParser.registerOption(null, () -> {}));
            assertThrows(NullPointerException.class,() -> cmdParser.registerOption("-test", null));
            assertThrows(NullPointerException.class,() -> cmdParser.registerOption(null, null));
        });
    }

    @Test
    void registerOneOptionTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.registerOption("-legacy", () -> obj.legacy = true);

        String[] arguments={"-legacy"};
        var files = cmdParser.process(arguments);

        assertEquals(List.of(), files);
        assertTrue(obj.legacy);
    }

    @Test
    void registerOptionAlreadySetTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.registerOption("-legacy", () -> obj.legacy = true);
        assertThrows(IllegalStateException.class, () -> cmdParser.registerOption("-legacy", () -> obj.legacy = false));
    }

    @Test
    void registerMultipleOptionsTest() {
        var obj = new Object() {
            boolean legacy = false;
            boolean help = false;
            boolean file = true;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.registerOption("-legacy", () -> obj.legacy = true);
        cmdParser.registerOption("-help", () -> obj.help = true);
        cmdParser.registerOption("-file", () -> obj.file = false);

        String[] arguments={"-legacy","-help"};
        var files = cmdParser.process(arguments);

        assertEquals(List.of(), files);
        assertTrue(obj.legacy);
        assertTrue(obj.help);
        assertTrue(obj.file);
    }

    @Test
    void processFileTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.registerOption("-legacy", () -> obj.legacy = true);

        String[] arguments={"file1","file2"};
        var files = cmdParser.process(arguments);

        assertEquals(List.of("file1", "file2"), files);
        assertFalse(obj.legacy);
    }

    @Test
    void processFileWithOptionTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.registerOption("-legacy", () -> obj.legacy = true);

        String[] arguments={"file1","file2","-legacy"};
        var files = cmdParser.process(arguments);

        assertEquals(List.of("file1", "file2"), files);
        assertTrue(obj.legacy);
    }
}
