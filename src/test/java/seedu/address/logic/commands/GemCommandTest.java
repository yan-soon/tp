package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_FOUNDATION;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_INTERN_1;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_INTERN_2;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SCIENCE;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_FOUNDATION;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_INTERN;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_ITPROF;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_MATHANDSCI;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_SCIENCE;
import static seedu.address.storage.RequiredCommandStorageTest.TEST_FOUNDATION_PATH;
import static seedu.address.storage.RequiredCommandStorageTest.TEST_SCIENCE_PATH;
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

class GemCommandTest {
    public static final Path COMPILED_PATH = Paths.get("src/test/data/RequiredCommandTest/compiledmodules.json");
    public static final Path INCOMPLETE_FOUNDATION_PATH =
            Paths.get("src/test/data/RequiredCommandTest/incompletefoundationmodules.json");
    public static final Path SINGLE_MODULE_PATH = Paths.get("src/test/data/RequiredCommandTest/singlemodule.json");
    public static final Path DOUBLE_MODULE_PATH = Paths.get("src/test/data/RequiredCommandTest/doublemodules.json");
    public static final String MISSING_MODULE_1 = "CS1101S (4 MCs)";
    public static final String MISSING_MODULE_2 = "CS1231S (4 MCs)";
    public static final String MESSAGE_INTERN_TEST = " You are currently at 4 MCs. ";
    public static final String TEST_GEH_SEM1_PATH = "src/main/resources/data/GEM/GEHs1.json";
    public static final String TEST_GEH_SEM2_PATH = "src/main/resources/data/GEM/GEHsem2.json";
    public static final String TEST_SCIENCE_PATH = "src/main/resources/data/GEM/GEHsem2.json";
    private Model model;
    private GemCommand gemCommand = new GemCommand();
    private ObservableList<Module> testModules;
    public void setUpTestModules1() throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(Paths.get(TEST_GEH_SEM1_PATH));
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        testModules = gradPad.getModuleList();
    }
    public void setUpTestModules2() throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(Paths.get(TEST_GEH_SEM2_PATH));
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        testModules = gradPad.getModuleList();
    }
    public void setUp() throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(COMPILED_PATH);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        model = new ModelManager();
        model.setGradPad(gradPad);
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
        setUpTestModules1();
        gemCommand.setSem1Storage();
        GemCommandStorage storage = gemCommand.getSem1Storage();
        ObservableList<Module> actual = storage.getGehModules();
        assertEquals(testModules, actual);
    }
    @Test
    public void setSem2Storage_validTest() throws IOException, DataConversionException, IllegalValueException {
        setUpTestModules2();
        gemCommand.setSem2Storage();
        GemCommandStorage storage = gemCommand.getSem2Storage();
        ObservableList<Module> actual = storage.getGehModules();
        assertEquals(testModules, actual);
    }
    @Test
    public void nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gemCommand.execute(model));
    }
    @Test
    public void execute_validTest() throws IOException, DataConversionException {
        setUp();
        String expectedMessage = "" + MESSAGE_SUCCESS_FOUNDATION + "\n" + "\n"
                + MESSAGE_SUCCESS_ITPROF + "\n" + "\n" + MESSAGE_SUCCESS_MATHANDSCI
                + "\n" + "\n" + MESSAGE_SUCCESS_SCIENCE + "\n" + "\n" + MESSAGE_SUCCESS_INTERN;
        CommandResult expected = new CommandResult(expectedMessage);
        CommandResult actual = gemCommand.execute(model);
        assertEquals(expected, actual);
    }
}
