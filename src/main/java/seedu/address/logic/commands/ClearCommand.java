package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.GradPad;
import seedu.address.model.Model;

/**
 * Clears the GradPad.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "GradPad has been cleared!";

    public static final String MESSAGE_CONFIRMATION = "Are you sure you wish to clear all modules?";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setGradPad(new GradPad());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean requiresStall() {
        return true;
    }
}
