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

    public static final Path GEH_PATH_1 = Paths.get("src/main/resources/data/GEM/GEHsem1.json");
    public static final Path GET_PATH_1 = Paths.get("src/main/resources/data/GEM/GETsem1.json");
    public static final Path GES_PATH_1 = Paths.get("src/main/resources/data/GEM/GESsem1.json");

    public static final Path GEH_PATH_2 = Paths.get("src/main/resources/data/GEM/GEHsem2.json");
    public static final Path GET_PATH_2 = Paths.get("src/main/resources/data/GEM/GETsem2.json");
    public static final Path GES_PATH_2 = Paths.get("src/main/resources/data/GEM/GESsem2.json");

    public static final Path GEQ_PATH_1 = Paths.get("src/main/resources/data/GEM/GEQ.json");
    public static final Path GER_PATH_1 = Paths.get("src/main/resources/data/GEM/GER.json");

    public static final Path TEST_GEH_SEM1_PATH = Paths.get("src/main/resources/data/GEM/GEHsem1.json");
    public static final Path TEST_GEH_SEM2_PATH = Paths.get("src/main/resources/data/GEM/GEHsem2.json");
    private Model model;
    private GemCommand gemCommand = new GemCommand();
    private ObservableList<Module> testModules;

    public void setUpTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        testModules = gradPad.getModuleList();
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

        String expectedMessage = MESSAGE_GEM_SUCCESS + "\n\n" + "Semester 1:";
        setUpTestModules(GEH_PATH_1);
        expectedMessage += "\n\nHuman Cultures\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GET_PATH_1);
        expectedMessage += "\n\nThinking and Expression\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GES_PATH_1);
        expectedMessage += "\n\nSingapore Studies\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GEQ_PATH_1);
        expectedMessage += "\n\nAsking Questions\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GER_PATH_1);
        expectedMessage += "\n\nQuantitative Reasoning\n" + gemCommand.moduleExtractor(testModules, model);

        expectedMessage += "\n\n" + LINE + "Semester 2:";
        setUpTestModules(GEH_PATH_2);
        expectedMessage += "\n\nHuman Cultures\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GET_PATH_2);
        expectedMessage += "\n\nThinking and Expression\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GES_PATH_2);
        expectedMessage += "\n\nSingapore Studies\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GEQ_PATH_1);
        expectedMessage += "\n\nAsking Questions\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GER_PATH_1);
        expectedMessage += "\n\nQuantitative Reasoning\n" + gemCommand.moduleExtractor(testModules, model);


        CommandResult expected = new CommandResult(expectedMessage);
        CommandResult actual = gemCommand.execute(model);
        assertEquals(expected, actual);
    }
}
