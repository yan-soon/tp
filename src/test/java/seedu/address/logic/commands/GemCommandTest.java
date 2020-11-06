package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.commons.core.Messages.LINE;
import static seedu.address.commons.core.Messages.MESSAGE_GEM_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.GemCommandStorage;
import seedu.address.storage.JsonGradPadStorage;

public class GemCommandTest {

    public static final Path GEH_PATH_1 = Paths.get("src/test/data/GemCommandTest"
        + "/sem1GEHmodules.json");
    public static final Path GET_PATH_1 = Paths.get("src/test/data/GemCommandTest"
        + "/sem1GETmodules.json");
    public static final Path GES_PATH_1 = Paths.get("src/test/data/GemCommandTest"
        + "/sem1GESmodules.json");
    public static final Path GEQ_PATH_1 = Paths.get("src/test/data/GemCommandTest"
        + "/sem1GEQmodules.json");
    public static final Path GER_PATH_1 = Paths.get("src/test/data/GemCommandTest"
        + "/sem1GERmodules.json");

    public static final Path GEH_PATH_2 = Paths.get("src/test/data/GemCommandTest"
        + "/sem2GEHmodules.json");
    public static final Path GET_PATH_2 = Paths.get("src/test/data/GemCommandTest"
        + "/sem2GETmodules.json");
    public static final Path GES_PATH_2 = Paths.get("src/test/data/GemCommandTest"
        + "/sem2GESmodules.json");
    public static final Path GEQ_PATH_2 = Paths.get("src/test/data/GemCommandTest"
        + "/sem2GEQmodules.json");
    public static final Path GER_PATH_2 = Paths.get("src/test/data/GemCommandTest"
        + "/sem2GERmodules.json");

    public static final Path TEST_GEH_SEM1_PATH = Paths.get("src/main/resources/data/GEM/GEHs1.json");
    public static final Path TEST_GEH_SEM2_PATH = Paths.get("src/main/resources/data/GEM/GEHsem2.json");
    private Model model;
    private GemCommand gemCommand = new GemCommand();
    private ObservableList<Module> testModules;
    private ObservableList<Module> gehTestModules;
    private ObservableList<Module> getTestModules;
    private ObservableList<Module> gesTestModules;
    private ObservableList<Module> geqTestModules;
    private ObservableList<Module> gerTestModules;

    public void setUpTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        testModules = gradPad.getModuleList();
    }

    public void setUpGehTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        gehTestModules = gradPad.getModuleList();
    }

    public void setUpGetTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        getTestModules = gradPad.getModuleList();
    }

    public void setUpGesTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        gesTestModules = gradPad.getModuleList();
    }

    public void setUpGeqTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        geqTestModules = gradPad.getModuleList();
    }

    public void setUpGerTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        gerTestModules = gradPad.getModuleList();
    }

    @Test
    public void getSem1Storage_validTest() {
        GemCommandStorage actual = gemCommand.getSem1Storage();
        assertNull(actual);
    }
    @Test
    public void getSem2Storage_validTest() {
        GemCommandStorage actual = gemCommand.getSem2Storage();
        assertNull(actual);
    }
    @Test
    public void setSem1Storage_validTest() throws IOException, DataConversionException, IllegalValueException {
        setUpTestModules(TEST_GEH_SEM1_PATH);
        gemCommand.setSem1Storage();
        GemCommandStorage storage = gemCommand.getSem1Storage();
        ObservableList<Module> actual = storage.getGehModules();
        assertEquals(testModules, actual);
    }
    @Test
    public void setSem2Storage_validTest() throws IOException, DataConversionException, IllegalValueException {
        setUpTestModules(TEST_GEH_SEM2_PATH);
        gemCommand.setSem2Storage();
        GemCommandStorage storage = gemCommand.getSem2Storage();
        ObservableList<Module> actual = storage.getGehModules();
        assertEquals(testModules, actual);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gemCommand.execute(model));
    }
    @Test
    public void execute_validTest() throws IOException, DataConversionException {
        model = new ModelManager();
        GemCommandStorage storage = new GemCommandStorage();

        setUpGehTestModules(GEH_PATH_1);
        setUpGetTestModules(GET_PATH_1);
        setUpGesTestModules(GES_PATH_1);
        setUpGeqTestModules(GEQ_PATH_1);
        setUpGerTestModules(GER_PATH_1);

        String expectedMessage = MESSAGE_GEM_SUCCESS + "\n\n" + "Semester 1:" + "\n\n";
        expectedMessage += "Human Cultures\n" + storage.moduleExtractor(gehTestModules, model);
        expectedMessage += "\n\nThinking and Expression\n" + storage.moduleExtractor(getTestModules, model);
        expectedMessage += "\n\nSingapore Studies\n" + storage.moduleExtractor(gesTestModules, model);
        expectedMessage += "\n\nAsking Questions\n" + storage.moduleExtractor(geqTestModules, model);
        expectedMessage += "\n\nQuantitative Reasoning\n" + storage.moduleExtractor(gerTestModules, model);

        setUpGehTestModules(GEH_PATH_2);
        setUpGetTestModules(GET_PATH_2);
        setUpGesTestModules(GES_PATH_2);
        setUpGeqTestModules(GEQ_PATH_2);
        setUpGerTestModules(GER_PATH_2);

        expectedMessage += "\n\n" + LINE + "Semester 2:";
        expectedMessage += "\n\nHuman Cultures\n" + storage.moduleExtractor(gehTestModules, model);
        expectedMessage += "\n\nThinking and Expression\n" + storage.moduleExtractor(getTestModules, model);
        expectedMessage += "\n\nSingapore Studies\n" + storage.moduleExtractor(gesTestModules, model);
        expectedMessage += "\n\nAsking Questions\n" + storage.moduleExtractor(geqTestModules, model);
        expectedMessage += "\n\nQuantitative Reasoning\n" + storage.moduleExtractor(gerTestModules, model);

        CommandResult expected = new CommandResult(expectedMessage);
        CommandResult actual = gemCommand.execute(model);
        assertEquals(expected, actual);
    }
}
