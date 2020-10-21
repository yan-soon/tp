package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.storage.JsonGradPadStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.RequiredCommand.FOUNDATION_PATH;
import static seedu.address.logic.commands.RequiredCommand.INTERNSHIP_PATH;
import static seedu.address.logic.commands.ScienceCommandTest.INVALID_PATH;
import static seedu.address.logic.commands.RequiredCommand.ITPROF_PATH;
import static seedu.address.logic.commands.RequiredCommand.MATHANDSCI_PATH;
import static seedu.address.logic.commands.RequiredCommand.SCIENCE_PATH;

class RequiredCommandTest {
    
    Model model;
    RequiredCommand requiredCommand= new RequiredCommand();

    @Test
    public void nullModel_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> requiredCommand.execute(model));
    }

    @Test
    public void validGetFoundationStorageTest() {
        Optional<ReadOnlyGradPad> empty = requiredCommand.getFoundationStorage();
        assertEquals(null, empty);
    }

    @Test
    public void validGetITProfStorageTest() {
        Optional<ReadOnlyGradPad> empty = requiredCommand.getITProfStorage();
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
    public void setFoundationStorageValidPath_ReturnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(FOUNDATION_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setFoundationStorage(FOUNDATION_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getFoundationStorage();
        assertEquals(actual, expected);
    }
    
    @Test
    public void setITProfStorageValidPath_ReturnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(ITPROF_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setITProfStorage(ITPROF_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getITProfStorage();
        assertEquals(actual, expected);
    }
    
    @Test
    public void setMathAndScienceStorageValidPath_ReturnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(MATHANDSCI_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setMathAndScienceStorage(MATHANDSCI_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getMathAndScienceStorage();
        assertEquals(actual, expected);
    }
    
    @Test
    public void setScienceStorageValidPath_ReturnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(SCIENCE_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setScienceStorage(SCIENCE_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getScienceStorage();
        assertEquals(actual, expected);
    }
    
    @Test
    public void setInternshipStorageValidPath_ReturnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(INTERNSHIP_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        requiredCommand.setInternshipStorage(INTERNSHIP_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getInternshipStorage();
        assertEquals(actual, expected);
    }

    @Test
    public void setFoundationStorageInvalidPath_ReturnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setFoundationStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getFoundationStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setITProfStorageInvalidPath_ReturnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setITProfStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getITProfStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setMathAndScienceStorageInvalidPath_ReturnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setMathAndScienceStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getMathAndScienceStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setScienceStorageInvalidPath_ReturnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setScienceStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getScienceStorage();
        assertEquals(actual, Optional.empty());
    }

    @Test
    public void setInternshipStorageInvalidPath_ReturnsEmptyOptional() throws IOException, DataConversionException {
        requiredCommand.setInternshipStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = requiredCommand.getInternshipStorage();
        assertEquals(actual, Optional.empty());
    }
    

}