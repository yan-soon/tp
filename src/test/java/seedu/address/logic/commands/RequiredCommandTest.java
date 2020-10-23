package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.storage.RequiredCommandMessages.FOUNDATION_PATH;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_FOUNDATION;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_FOUNDATION;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_INTERN;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_ITPROF;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_MATHANDSCI;
import static seedu.address.storage.RequiredCommandMessages.MESSAGE_SUCCESS_SCIENCE;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonGradPadStorage;
import seedu.address.storage.RequiredCommandStorage;

class RequiredCommandTest {
    private Model model;
    private RequiredCommand requiredCommand = new RequiredCommand();
    private ObservableList<Module> requiredFoundation;
    public void setUpRequiredFoundation() throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(FOUNDATION_PATH);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        requiredFoundation = gradPad.getModuleList();
    }
    public void setUp() throws IOException, DataConversionException {
        String string = "src/test/data/RequiredCommandTest/compiledmodules.json";
        Path path = Paths.get(string);
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        model = new ModelManager();
        model.setGradPad(gradPad);
    }
    @Test
    public void getLeftOverModules_validTest() {
        String actual = requiredCommand.getLeftOverModules();
        assertEquals("", actual);
    }
    @Test
    public void getStorage_validTest() {
        RequiredCommandStorage actual = requiredCommand.getStorage();
        assertNull(actual);
    }
    @Test
    public void setStorage_validTest() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        requiredCommand.setStorage();
        RequiredCommandStorage storage = requiredCommand.getStorage();
        ObservableList<Module> actual = storage.getRequiredFoundation();
        assertEquals(requiredFoundation, actual);
    }
    @Test
    public void getCurrentModules_validTest() {
        ObservableList<Module> actual = requiredCommand.getCurrentModules();
        assertNull(actual);
    }
    @Test
    public void setCurrentModules_validTest() {
        requiredCommand.setCurrentModules(null);
        assertNull(requiredCommand.getCurrentModules());
    }
    @Test
    public void compareModules_validTest() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        requiredCommand.setCurrentModules(requiredFoundation);
        requiredCommand.compareModules(requiredFoundation, MESSAGE_FOUNDATION, MESSAGE_SUCCESS_FOUNDATION);
        String expected = MESSAGE_SUCCESS_FOUNDATION + "\n" + "\n";
        String actual = requiredCommand.getLeftOverModules();
        assertEquals(expected, actual);
    }
    @Test
    public void compareScience_validTest() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        requiredCommand.setCurrentModules(requiredFoundation);
        requiredCommand.compareScience(requiredFoundation);
        String actual = requiredCommand.getLeftOverModules();
        String expected = MESSAGE_SUCCESS_SCIENCE + "\n" + "\n";
        assertEquals(expected, actual);
    }
    @Test
    public void compareInternship_validTest() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        requiredCommand.setCurrentModules(requiredFoundation);
        requiredCommand.compareInternship(requiredFoundation);
        String actual = requiredCommand.getLeftOverModules();
        String expected = MESSAGE_SUCCESS_INTERN;
        assertEquals(expected, actual);
    }
    @Test
    public void nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> requiredCommand.execute(model));
    }
    @Test
    public void execute_validTest() throws IOException, DataConversionException {
        setUp();
        String expectedMessage = "" + MESSAGE_SUCCESS_FOUNDATION + "\n" + "\n"
                + MESSAGE_SUCCESS_ITPROF + "\n" + "\n" + MESSAGE_SUCCESS_MATHANDSCI
                + "\n" + "\n" + MESSAGE_SUCCESS_SCIENCE + "\n" + "\n" + MESSAGE_SUCCESS_INTERN;
        CommandResult expected = new CommandResult(expectedMessage);
        CommandResult actual = requiredCommand.execute(model);
        assertEquals(expected, actual);
    }
}
