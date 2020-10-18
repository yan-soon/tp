package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Displays the cumulative Modular Credits of the modules in the GradPad.
 */
public class CheckMcCommand extends Command {

    public static final String COMMAND_WORD = "checkmc";
    public static final String MESSAGE_SUCCESS = "Total MC has been calculated: ";

    private ObservableList<Module> modules;
    private int totalMc = 0;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        modules = model.getGradPad().getModuleList();

        for (Module module : modules) {
            totalMc += Integer.parseInt(module.getModularCredits().toString());
        }
        return new CommandResult(MESSAGE_SUCCESS + totalMc);
    }
}
