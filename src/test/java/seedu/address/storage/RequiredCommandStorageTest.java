package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.ScienceCommandTest.INVALID_PATH;
import static seedu.address.storage.RequiredCommandMessages.FOUNDATION_PATH;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;

class RequiredCommandStorageTest {
    private RequiredCommandStorage storage = new RequiredCommandStorage();
    private ObservableList<Module> requiredFoundation;
    public void setUpRequiredFoundation() throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(FOUNDATION_PATH);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        requiredFoundation = gradPad.getModuleList();
    }
    @Test
    public void getRequiredFoundation_validTest() {
        ObservableList<Module> actual = storage.getRequiredFoundation();
        assertNull(actual);
    }
    @Test
    public void getRequiredITprof_validTest() {
        ObservableList<Module> actual = storage.getRequiredITprof();
        assertNull(actual);
    }
    @Test
    public void getRequiredMathAndScience_validTest() {
        ObservableList<Module> actual = storage.getRequiredMathAndScience();
        assertNull(actual);
    }
    @Test
    public void getRequiredScience_validTest() {
        ObservableList<Module> actual = storage.getRequiredScience();
        assertNull(actual);
    }
    @Test
    public void getRequiredInternship_validTest() {
        ObservableList<Module> actual = storage.getRequiredInternship();
        assertNull(actual);
    }
    @Test
    public void setRequiredFoundationInvalidPath_throwsNoSuchElementException() throws IOException,
            DataConversionException {
        assertThrows(NoSuchElementException.class, () -> storage.setRequiredFoundation(INVALID_PATH));
    }
    @Test
    public void setRequiredITprofInvalidPath_throwsNoSuchElementException() throws IOException,
            DataConversionException {
        assertThrows(NoSuchElementException.class, () -> storage.setRequiredITprof(INVALID_PATH));
    }
    @Test
    public void setRequiredMathAndScienceInvalidPath_throwsNoSuchElementException() throws IOException,
            DataConversionException {
        assertThrows(NoSuchElementException.class, () -> storage.setRequiredMathAndScience(INVALID_PATH));
    }
    @Test
    public void setRequiredScienceInvalidPath_throwsNoSuchElementException() throws IOException,
            DataConversionException {
        assertThrows(NoSuchElementException.class, () -> storage.setRequiredScience(INVALID_PATH));
    }
    @Test
    public void setRequiredInternshipInvalidPath_throwsNoSuchElementException() throws IOException,
            DataConversionException {
        assertThrows(NoSuchElementException.class, () -> storage.setRequiredInternship(INVALID_PATH));
    }
    @Test
    public void setRequiredFoundationValidPath_success() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setRequiredFoundation(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getRequiredFoundation();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredITprofValidPath_success() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setRequiredITprof(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getRequiredITprof();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredMathAndScienceValidPath_success() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setRequiredMathAndScience(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getRequiredMathAndScience();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredScienceValidPath_success() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setRequiredScience(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getRequiredScience();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredInternshipValidPath_success() throws IOException, DataConversionException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setRequiredInternship(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getRequiredInternship();
        assertEquals(expected, actual);
    }
}
