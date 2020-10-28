package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.storage.GemCommandMessages.GEH_SEM1_PATH;
import static seedu.address.storage.GemCommandMessages.GEH_SEM2_PATH;
import static seedu.address.storage.GemCommandMessages.GEQ_PATH;
import static seedu.address.storage.GemCommandMessages.GER_PATH;
import static seedu.address.storage.GemCommandMessages.GES_SEM1_PATH;
import static seedu.address.storage.GemCommandMessages.GES_SEM2_PATH;
import static seedu.address.storage.GemCommandMessages.GET_SEM1_PATH;
import static seedu.address.storage.GemCommandMessages.GET_SEM2_PATH;

import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.storage.GemCommandStorage;

public class GemCommand extends Command{
    public static final String COMMAND_WORD = "gem";
    public static final String MESSAGE_SUCCESS = "These are the GE Modules that you can take:";
    public static final String MESSAGE_FAILURE_SCIENCE = "There was an error loading the required GE Modules :(";
    private GemCommandStorage sem1Storage;
    private GemCommandStorage sem2Storage;

    /**
     * Retrieves the attribute sem1Storage of a GemCommand object.
     * @return storage attribute of type GemCommandStorage.
     */
    public GemCommandStorage getSem1Storage() {
        return sem1Storage;
    }

    /**
     * Retrieves the attribute sem2Storage of a GemCommand object.
     * @return storage attribute of type GemCommandStorage.
     */
    public GemCommandStorage getSem2Storage() {
        return sem2Storage;
    }

    /**
     * Loads the gemModules attribute with Semester 1 GE Modules by using
     * the various set() methods from the GemCommandStorage class.
     * @throws IOException When the path in invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setSem1Storage() throws IOException, IllegalValueException {
        sem1Storage = new GemCommandStorage();
        sem1Storage.setGehModules(GEH_SEM1_PATH);
        sem1Storage.setGeqModules(GEQ_PATH);
        sem1Storage.setGerModules(GER_PATH);
        sem1Storage.setGesModules(GES_SEM1_PATH);
        sem1Storage.setGetModules(GET_SEM1_PATH);
    }

    /**
     * Loads the gemModules attribute with Semester 2 GE Modules by using
     * the various set() methods from the GemCommandStorage class.
     * @throws IOException When the path in invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setSem2Storage() throws IOException, IllegalValueException {
        sem2Storage = new GemCommandStorage();
        sem2Storage.setGehModules(GEH_SEM2_PATH);
        sem2Storage.setGeqModules(GEQ_PATH);
        sem2Storage.setGerModules(GER_PATH);
        sem2Storage.setGesModules(GES_SEM2_PATH);
        sem2Storage.setGetModules(GET_SEM2_PATH);
    }

    /**
     * Takes a List of Modules and extracts out their Module Code and Modular Credits.
     * @param modules List of Modules.
     * @return String of Module Codes and Modular Credits.
     */
    public StringBuilder moduleExtractor(ObservableList<Module> modules) {
        StringBuilder modulesToAdd = new StringBuilder();
        for (Module module : modules) {
            String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
            modulesToAdd.append("\n").append(moduleToAdd);
        }
        return modulesToAdd;
    }

    /**
     * Sets up the all the GE modules in the sem1Storage and sem2Storage and displays them
     * with the CommandResult object.
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult displaying all the available GE modules.
     */
    @Override
    public CommandResult execute(Model model) {
        try {
            requireNonNull(model);
            setSem1Storage();
            setSem2Storage();
            String modulesToAdd = "\n \n" + "SEMESTER 1:" + "\n";
            modulesToAdd += moduleExtractor(sem1Storage.getGehModules());
            modulesToAdd += moduleExtractor(sem1Storage.getGeqModules());
            modulesToAdd += moduleExtractor(sem1Storage.getGerModules());
            modulesToAdd += moduleExtractor(sem1Storage.getGesModules());
            modulesToAdd += moduleExtractor(sem1Storage.getGetModules());
            modulesToAdd += "\n \n" + "SEMESTER 2:" + "\n";
            modulesToAdd += moduleExtractor(sem2Storage.getGehModules());
            modulesToAdd += moduleExtractor(sem2Storage.getGeqModules());
            modulesToAdd += moduleExtractor(sem2Storage.getGerModules());
            modulesToAdd += moduleExtractor(sem2Storage.getGesModules());
            modulesToAdd += moduleExtractor(sem2Storage.getGetModules());
            return new CommandResult(MESSAGE_SUCCESS + modulesToAdd);
        } catch (IOException | IllegalValueException e) {
            return new CommandResult(MESSAGE_FAILURE_SCIENCE);
        } 
    }
}
