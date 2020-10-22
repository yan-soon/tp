package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.storage.RequiredCommandMessages.SCIENCE_PATH;

import java.io.IOException;
import java.nio.file.Path;
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
    private ObservableList<Module> scienceModules;

    /**
     * Returns the storage attribute of a given ScienceCommand object.
     * @return storage attribute of type Optional<ReadOnlyGradPad/>.
     */
    public ObservableList<Module> getScienceModules() {
        return scienceModules;
    }

    /**
     * Loads the storage attribute with Science Modules.
     * @throws IOException When the path in invalid.
     * @throws DataConversionException When there is an error converting from the JSON file.
     */
    public void setScienceModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        Optional<ReadOnlyGradPad> gradPad = storage.readGradPad();
        scienceModules = gradPad.orElseThrow().getModuleList();
    }

    /**
     * Goes through the storage attribute and parses all Science Modules, to be read by the user.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult Object with the relevant Science Modules or Failure Message if modules
     * are absent.
     */
    @Override
    public CommandResult execute(Model model) {
        try {
            requireNonNull(model);
            setScienceModules(SCIENCE_PATH);
            StringBuilder modulesToAdd = new StringBuilder();
            for (Module module : scienceModules) {
                String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
                modulesToAdd.append("\n").append(moduleToAdd);
            }
            return new CommandResult(MESSAGE_SUCCESS + modulesToAdd);
        } catch (DataConversionException | IOException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
