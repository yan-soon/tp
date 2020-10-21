package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.RequiredCommand.FOUNDATION_PATH;
import static seedu.address.logic.commands.RequiredCommand.INTERNSHIP_PATH;
import static seedu.address.logic.commands.RequiredCommand.ITPROF_PATH;
import static seedu.address.logic.commands.RequiredCommand.MATHANDSCI_PATH;
import static seedu.address.logic.commands.RequiredCommand.MESSAGE_SUCCESS_FOUNDATION;
import static seedu.address.logic.commands.RequiredCommand.MESSAGE_SUCCESS_INTERN;
import static seedu.address.logic.commands.RequiredCommand.MESSAGE_SUCCESS_ITPROF;
import static seedu.address.logic.commands.RequiredCommand.MESSAGE_SUCCESS_MATHANDSCI;
import static seedu.address.logic.commands.RequiredCommand.MESSAGE_SUCCESS_SCIENCE;
import static seedu.address.logic.commands.RequiredCommand.SCIENCE_PATH;
import static seedu.address.logic.commands.ScienceCommandTest.INVALID_PATH;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.storage.JsonGradPadStorage;

class RequiredCommandTest {
    private Model model;
    private RequiredCommand requiredCommand = new RequiredCommand();
    public void setUp() throws IOException, DataConversionException {
        String string = "src/test/data/RequiredCommandTest/compiledmodules.json";
        Path path = Paths.get(string);
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        model = new ModelManager();
        model.setGradPad(gradPad);
    }

    @Test
    public void nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> requiredCommand.execute(model));
    }

    @Test
    public void validGetFoundationStorageTest() {
        Optional<ReadOnlyGradPad> empty = requiredCommand.getFoundationStorage();
        assertEquals(null, empty);
    }

    @Test
    public void validGetITprofStorageTest() {
        Optional<ReadOnlyGradPad> empty = requiredCommand.getITprofStorage();
        assertEquals(null, empty);
    }
    @Test
    public void validGetMathAndScienceStorageTest() {
        Optional<ReadOnlyGradPad> empty = requiredCommand.getMathAndScienceStorage();
        assertEquals(null, empty);
    }

    @Test
    public void validGetScienceStorageTest() {
        Optional<ReadOnlyGradPad> empty = requiredCommand.getScienceStorage();
        assertEquals(null, empty);
    }

    @Test
    public void validGetInternshipStorageTest() {
        Optional<ReadOnlyGradPad> empty = requiredCommand.getInternshipStorage();
        assertEquals(null, empty);
    }

    @Test
    public void setFoundationStorageValidPath_returnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(FOUNDATION_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setFoundationStorage(FOUNDATION_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getFoundationStorage();
        assertEquals(actual, expected);
    }
    @Test
    public void setITprofStorageValidPath_returnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(ITPROF_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setITprofStorage(ITPROF_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getITprofStorage();
        assertEquals(actual, expected);
    }
    @Test
    public void setMathAndScienceStorageValidPath_returnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(MATHANDSCI_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setMathAndScienceStorage(MATHANDSCI_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getMathAndScienceStorage();
        assertEquals(actual, expected);
    }
    @Test
    public void setScienceStorageValidPath_returnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(SCIENCE_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setScienceStorage(SCIENCE_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getScienceStorage();
        assertEquals(actual, expected);
    }
    @Test
    public void setInternshipStorageValidPath_returnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(INTERNSHIP_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setInternshipStorage(INTERNSHIP_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getInternshipStorage();
        assertEquals(actual, expected);
    }

    @Test
    public void setFoundationStorageInvalidPath_returnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setFoundationStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getFoundationStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setITprofStorageInvalidPath_returnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setITprofStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getITprofStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setMathAndScienceStorageInvalidPath_returnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setMathAndScienceStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getMathAndScienceStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setScienceStorageInvalidPath_returnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setScienceStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getScienceStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setInternshipStorageInvalidPath_returnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setInternshipStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getInternshipStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void validPathExecuteRequiredCommand_success() throws IOException, DataConversionException {
        setUp();
        String expectedMessage = "" + MESSAGE_SUCCESS_FOUNDATION + "\n" + "\n"
                + MESSAGE_SUCCESS_ITPROF + "\n" + "\n" + MESSAGE_SUCCESS_MATHANDSCI
                + "\n" + "\n" + MESSAGE_SUCCESS_SCIENCE + "\n" + "\n" + MESSAGE_SUCCESS_INTERN;
        CommandResult expected = new CommandResult(expectedMessage);
        CommandResult actual = requiredCommand.execute(model);
        assertEquals(expected, actual);
    }
}
