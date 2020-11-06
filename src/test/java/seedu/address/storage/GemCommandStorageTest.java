package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_GEM_SUCCESS;
import static seedu.address.logic.commands.GemCommandTest.GEH_PATH_1;
import static seedu.address.logic.commands.GemCommandTest.GEQ_PATH_1;
import static seedu.address.logic.commands.GemCommandTest.GER_PATH_1;
import static seedu.address.logic.commands.GemCommandTest.GES_PATH_1;
import static seedu.address.logic.commands.GemCommandTest.GET_PATH_1;
import static seedu.address.logic.commands.RequiredCommandTest.MISSING_MODULE_1;
import static seedu.address.logic.commands.RequiredCommandTest.SINGLE_MODULE_PATH;
import static seedu.address.logic.commands.ScienceCommandTest.INVALID_PATH;
import static seedu.address.storage.GemCommandPaths.GEH_SEM1_PATH;
import static seedu.address.storage.GemCommandPaths.GEQ_PATH;
import static seedu.address.storage.GemCommandPaths.GER_PATH;
import static seedu.address.storage.GemCommandPaths.GES_SEM1_PATH;
import static seedu.address.storage.GemCommandPaths.GET_SEM1_PATH;
import static seedu.address.storage.RequiredCommandMessages.FOUNDATION_PATH;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.GemCommandTest;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;

public class GemCommandStorageTest {
    public static final String TEST_FOUNDATION_PATH = "src/main/resources/data/foundationmodules.json";
    private GemCommandStorage storage = new GemCommandStorage();
    private ObservableList<Module> testModules;
    private Model model;

    public void setUpTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        testModules = gradPad.getModuleList();
    }

    @Test
    public void getGehModules_validTest() {
        ObservableList<Module> actual = storage.getGehModules();
        assertNull(actual);
    }
    @Test
    public void getGeqModules_validTest() {
        ObservableList<Module> actual = storage.getGeqModules();
        assertNull(actual);
    }
    @Test
    public void getGerModules_validTest() {
        ObservableList<Module> actual = storage.getGerModules();
        assertNull(actual);
    }
    @Test
    public void getGesModules_validTest() {
        ObservableList<Module> actual = storage.getGesModules();
        assertNull(actual);
    }
    @Test
    public void getGetModules_validTest() {
        ObservableList<Module> actual = storage.getGetModules();
        assertNull(actual);
    }
    @Test
    public void setGehModulesInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setGehModules(INVALID_PATH));
    }
    @Test
    public void setGeqModulesInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setGeqModules(INVALID_PATH));
    }
    @Test
    public void setGerModulesInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setGerModules(INVALID_PATH));
    }
    @Test
    public void setGesModulesInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setGesModules(INVALID_PATH));
    }
    @Test
    public void setGetModulesInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setGetModules(INVALID_PATH));
    }
    @Test
    public void setGehModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpTestModules(Paths.get(TEST_FOUNDATION_PATH));
        ObservableList<Module> expected = testModules;
        storage.setGehModules(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getGehModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGeqModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpTestModules(Paths.get(TEST_FOUNDATION_PATH));
        ObservableList<Module> expected = testModules;
        storage.setGeqModules(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getGeqModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGerModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpTestModules(Paths.get(TEST_FOUNDATION_PATH));
        ObservableList<Module> expected = testModules;
        storage.setGerModules(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getGerModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGesModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpTestModules(Paths.get(TEST_FOUNDATION_PATH));
        ObservableList<Module> expected = testModules;
        storage.setGesModules(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getGesModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGetModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpTestModules(Paths.get(TEST_FOUNDATION_PATH));
        ObservableList<Module> expected = testModules;
        storage.setGetModules(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getGetModules();
        assertEquals(expected, actual);
    }

    @Test
    public void moduleExtractor_validTest() throws IOException, DataConversionException {
        model = new ModelManager();
        setUpTestModules(SINGLE_MODULE_PATH);
        String expected = "\n" + MISSING_MODULE_1;
        StringBuilder temp = storage.moduleExtractor(testModules, model);
        String actual = "" + temp;
        assertEquals(expected, actual);
    }

    @Test
    public void getCompiledModules_validTest() {
        String actual = storage.getCompiledModules();
        assertNull(actual);
    }

    @Test
    public void setCompiledModules_invalidTest() {
        model = new ModelManager();
        assertThrows(AssertionError.class, () -> storage.setCompiledModules(model));
    }

    @Test
    public void setCompiledModules_validTest() throws IOException, IllegalValueException, DataConversionException {
        model = new ModelManager();

        String expected = MESSAGE_GEM_SUCCESS + "\n\n" + "Semester 1:" + "\n\n";
        setUpTestModules(GEH_PATH_1);
        expected += "Human Cultures\n" + storage.moduleExtractor(testModules, model);
        setUpTestModules(GET_PATH_1);
        expected += "\n\nThinking and Expression\n" + storage.moduleExtractor(testModules, model);
        setUpTestModules(GES_PATH_1);
        expected += "\n\nSingapore Studies\n" + storage.moduleExtractor(testModules, model);
        setUpTestModules(GEQ_PATH_1);
        expected += "\n\nAsking Questions\n" + storage.moduleExtractor(testModules, model);
        setUpTestModules(GER_PATH_1);
        expected += "\n\nQuantitative Reasoning\n" + storage.moduleExtractor(testModules, model);

        storage.setGehModules(GEH_SEM1_PATH);
        storage.setGeqModules(GEQ_PATH);
        storage.setGerModules(GER_PATH);
        storage.setGesModules(GES_SEM1_PATH);
        storage.setGetModules(GET_SEM1_PATH);
        storage.setCompiledModules(model);
        String actual = MESSAGE_GEM_SUCCESS + "\n\nSemester 1:\n\n" + storage.getCompiledModules();

        assertEquals(expected, actual);
    }
}
