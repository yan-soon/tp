package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonGradPadStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class ScienceCommand extends Command {
    public static final String COMMAND_WORD = "science";
    public static final String MESSAGE_SUCCESS = "These are the Science Modules that you can take:";
    public static final String MESSAGE_FAILURE = "There was an error loading the required modules :(";
    
    private Optional<ReadOnlyGradPad> storage;
    private String moduleNames = "";

    public void setStorage() throws IOException, DataConversionException {
        Path path = Paths.get("data", "sciencemodules.json");
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        this.storage = storage.readGradPad();
    }

    @Override
    public CommandResult execute(Model model) throws IOException, DataConversionException {
        requireNonNull(model);
        setStorage();
        ObservableList<Module> modules;
        if (storage.isPresent()) {
            modules = storage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
        
        for (Module module : modules) {
            String moduleName = module.getModuleCode().toString();
            moduleNames += "\n" + moduleName;
        }
        
        return new CommandResult(MESSAGE_SUCCESS + moduleNames);
    }
}
