package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonGradPadStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ScienceCommandTest {
    
    Model model;
    ScienceCommand scienceCommand = new ScienceCommand();
    ObservableList<Module> modules;
    String moduleNames = "";
    public static final String MESSAGE_SUCCESS = "These are the Science Modules that you can take:";
    public static final Path INVALID_PATH = Paths.get("data", "science.json");
    public static final Path VALID_PATH = Paths.get("data", "sciencemodules.json");
    
    public void setUp() throws IOException, DataConversionException {
        Path path = Paths.get("data", "sciencemodules.json");
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        modules = storage.readGradPad().get().getModuleList();
        for (Module module : modules) {
            String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
            moduleNames += "\n" + moduleToAdd;
        }
    }
    
    @Test
    public void validGetStorageTest() {
        Optional<ReadOnlyGradPad> empty = scienceCommand.getStorage();
        assertEquals(null, empty);
    }
    
    @Test
    public void nullModel_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scienceCommand.execute(model));
    }
    
    @Test
    public void nullPath_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scienceCommand.setStorage(null));
    }
    
    @Test
    public void validPathExecuteRequiredCommand_Success() throws IOException, DataConversionException {
        model = new ModelManager();
        setUp();
        CommandResult expected = new CommandResult(MESSAGE_SUCCESS + moduleNames);
        CommandResult actual = scienceCommand.execute(model);
        assertEquals(expected, actual);
    }
    
    @Test
    public void setStorageInvalidPath_ReturnsEmptyOptional() throws IOException, DataConversionException {
        scienceCommand.setStorage(INVALID_PATH);
        Optional<ReadOnlyGradPad> actual = scienceCommand.getStorage();
        assertEquals(actual, Optional.empty());
    }
    
    @Test
    public void setStorageValidPath_ReturnsFilledOptional() throws IOException, DataConversionException {
        JsonGradPadStorage expectedJsonStorage = new JsonGradPadStorage(VALID_PATH);
        Optional<ReadOnlyGradPad> expected = expectedJsonStorage.readGradPad();
        scienceCommand.setStorage(VALID_PATH);
        Optional<ReadOnlyGradPad> actual = scienceCommand.getStorage();
        assertEquals(actual, expected);
    }

}