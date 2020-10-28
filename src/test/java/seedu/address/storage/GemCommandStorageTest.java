package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.ScienceCommandTest.INVALID_PATH;
import static seedu.address.storage.RequiredCommandMessages.FOUNDATION_PATH;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;

public class GemCommandStorageTest {
    public static final String TEST_FOUNDATION_PATH = "src/main/resources/data/foundationmodules.json";
    public static final String TEST_SCIENCE_PATH = "src/main/resources/data/sciencemodules.json";
    private GemCommandStorage storage = new GemCommandStorage();
    private ObservableList<Module> requiredFoundation;
    public void setUpRequiredFoundation() throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(Paths.get(TEST_FOUNDATION_PATH));
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        requiredFoundation = gradPad.getModuleList();
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
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setGehModules(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getGehModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGeqModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setGeqModules(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getGeqModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGerModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setGerModules(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getGerModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGesModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setGesModules(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getGesModules();
        assertEquals(expected, actual);
    }
    @Test
    public void setGetModulesValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setGetModules(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getGetModules();
        assertEquals(expected, actual);
    }
}
