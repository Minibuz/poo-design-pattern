package fr.uge.poo.cmdline.ex4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CmdLineParserTest {

    @Test
    void registerNullTest() {
        var cmdParser = new CmdLineParser();

        assertAll(() -> {
            assertThrows(NullPointerException.class,() -> cmdParser.addFlag(null, () -> {}));
            assertThrows(NullPointerException.class,() -> cmdParser.addFlag("-test", null));
            assertThrows(NullPointerException.class,() -> cmdParser.addFlag(null, null));
        });
    }

    @Test
    void registerOneOptionTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addFlag("-legacy", () -> obj.legacy = true);

        String[] arguments={"-legacy"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

        assertEquals(List.of(), files);
        assertTrue(obj.legacy);
    }

    @Test
    void registerOptionAlreadySetTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addFlag("-legacy", () -> obj.legacy = true);
        assertThrows(IllegalStateException.class, () -> cmdParser.addFlag("-legacy", () -> obj.legacy = false));
    }

    @Test
    void registerMultipleOptionsTest() {
        var obj = new Object() {
            boolean legacy = false;
            boolean help = false;
            boolean file = true;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addFlag("-legacy", () -> obj.legacy = true);
        cmdParser.addFlag("-help", () -> obj.help = true);
        cmdParser.addFlag("-file", () -> obj.file = false);

        String[] arguments={"-legacy","-help"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

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
        cmdParser.addFlag("-legacy", () -> obj.legacy = true);

        String[] arguments={"file1","file2"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

        assertEquals(List.of("file1", "file2"), files);
        assertFalse(obj.legacy);
    }

    @Test
    void processFileWithOptionTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addFlag("-legacy", () -> obj.legacy = true);

        String[] arguments={"file1","file2","-legacy"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

        assertEquals(List.of("file1", "file2"), files);
        assertTrue(obj.legacy);
    }

    @Test
    void processOptionWithOrderTest() {
        var obj = new Object() {
            boolean legacy = false;
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addFlag("-legacy", () -> obj.legacy = true);
        cmdParser.addFlag("-not-legacy", () -> obj.legacy = false);

        String[] arguments={"-legacy", "-not-legacy"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

        assertEquals(List.of(), files);
        assertFalse(obj.legacy);
    }

    @Test
    void registerOneOptionWithVariableTest() {
        var obj = new Object() {
            String msg = "";
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addOptionWithOneParameter("-lenght", str -> obj.msg = str);

        String[] arguments={"-lenght","yes"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

        assertEquals(List.of(), files);
        assertEquals("yes", obj.msg);
    }

    @Test
    void registerMutliplesOptionsWithVariableTest() {
        var obj = new Object() {
            String msg = "";
            String other = "";
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addOptionWithOneParameter("-lenght", str -> obj.msg = str);
        cmdParser.addOptionWithOneParameter("-other", str -> obj.other = str);

        String[] arguments={"-lenght","yes","-other","yes"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

        assertEquals(List.of(), files);
        assertEquals("yes", obj.msg);
        assertEquals("yes", obj.other);
    }

    @Test
    void registerOptionsWithVariableAndWithoutTest() {
        var obj = new Object() {
            boolean bool = false;
            String other = "";
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addFlag("-lenght", () -> obj.bool = true);
        cmdParser.addOptionWithOneParameter("-other", str -> obj.other = str);

        String[] arguments={"-lenght","-other","yes"};
        var files = cmdParser.process(Arrays.stream(arguments).toList());

        assertEquals(List.of(), files);
        assertTrue(obj.bool);
        assertEquals("yes", obj.other);
    }

    @Test
    void registerOptionWithArgumentFailureTest() {
        var obj = new Object() {
            String other = "";
        };

        var cmdParser = new CmdLineParser();
        cmdParser.addOptionWithOneParameter("-other", str -> obj.other = str);

        String[] arguments={"-other"};

        assertThrows(NoParameterGivenException.class, () -> cmdParser.process(Arrays.stream(arguments).toList()));
    }

    @Test
    void registerOptionWithArgumentOptionFailureTest() {
        var obj = new Object() {
            String other = "";
        };

        var cmdParser = new CmdLineParser();

        String[] arguments={"-other"};

        assertThrows(NoParameterGivenException.class, () -> cmdParser.process(Arrays.stream(arguments).toList()));
    }
}
