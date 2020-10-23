package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Deletes a Module identified using it's displayed index from the GradPad.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";
    public static final String MESSAGE_CONFIRMATION = "Are you sure you wish to delete the following"
            + " module?\n\n";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Retrieves the module to be deleted.
     *
     * @param model The Model which the command operates on.
     * @return The module to be deleted.
     * @throws CommandException if the target index given is not within the bounds of the module list.
     */
    public Module getModuleToDelete(Model model) throws CommandException {
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        return lastShownList.get(targetIndex.getZeroBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module moduleToDelete = getModuleToDelete(model);
        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

    @Override
    public boolean requiresStall() {
        return true;
    }
}
