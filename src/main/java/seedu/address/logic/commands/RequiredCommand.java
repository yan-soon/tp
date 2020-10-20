package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.GradPad;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.storage.JsonGradPadStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class RequiredCommand extends Command {
    public static final String COMMAND_WORD = "required";
    public static final String MESSAGE_SUCCESS = "These are the modules you have yet to take: ";
    public static final String MESSAGE_FAILURE = "There was an error loading the required modules :(";

    private ObservableList<Module> modules;
    private String leftOverModules = "";
    private ObservableList<Module> required;
    
    @Override
    public CommandResult execute(Model model) throws IOException, DataConversionException {
        requireNonNull(model);
        modules = model.getGradPad().getModuleList();

        Path path = Paths.get("data", "requiredmodules.json");
        JsonGradPadStorage test = new JsonGradPadStorage(path);
        Optional<ReadOnlyGradPad> test2 = test.readGradPad();
        
        if (test2.isPresent()) {
            required = test2.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
        
        for (Module module : required) {
            boolean add = true;
            for (Module mod : modules) {
                if (module.isSameModule(mod)) {
                    add = false;
                    break;
                }
            }
            if (add) {
                String moduleToAdd = module.getModuleCode().toString();
                leftOverModules += "\n" + moduleToAdd;
            }
        }
        return new CommandResult(MESSAGE_SUCCESS + leftOverModules);
    }
}
