package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonGradPadStorage;

public class ScienceCommand extends Command {
    public static final String COMMAND_WORD = "science";
    public static final String MESSAGE_SUCCESS = "These are the Science Modules that you can take:";
    public static final String MESSAGE_FAILURE = "There was an error loading the required modules :(";
    private Optional<ReadOnlyGradPad> storage;
    private String moduleNames = "";

    /**
     * Returns the storage attribute of a given ScienceCommand object.
     * @return storage attribute of type Optional<ReadOnlyGradPad/>.
     */
    public Optional<ReadOnlyGradPad> getStorage() {
        return storage;
    }

    /**
     * Loads the storage attribute with Science Modules.
     * @throws IOException
     * @throws DataConversionException
     */
    public void setStorage(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        this.storage = storage.readGradPad();
    }

    /**
     * Goes through the storage attribute and parses all Science Modules, to be read by the user.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult Object with the relevant Science Modules or Failure Message if modules
     * are absent.
     * @throws IOException
     * @throws DataConversionException
     */
    @Override
    public CommandResult execute(Model model) throws IOException, DataConversionException {
        requireNonNull(model);
        Path sciencePath = Paths.get("src/main/data/sciencemodules.json");
        setStorage(sciencePath);
        ObservableList<Module> modules;
        if (storage.isPresent()) {
            modules = storage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
        for (Module module : modules) {
            String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
            moduleNames += "\n" + moduleToAdd;
        }
        return new CommandResult(MESSAGE_SUCCESS + moduleNames);
    }
}
