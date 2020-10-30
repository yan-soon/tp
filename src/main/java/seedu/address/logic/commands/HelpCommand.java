package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.SHOWING_HELP_MESSAGE;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false);
    }
}
