package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.ModuleContainsTagsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> keywords = Arrays.asList(trimmedArgs.split("\\s+"));

        return new FindCommand(new CompoundFindPredicate(keywords));
    }

    public static class CompoundFindPredicate implements Predicate<Module> {
        private final List<String> keywords;

        CompoundFindPredicate(List<String> keywords) {
            this.keywords = keywords;
        }

        @Override
        public boolean test(Module module) {
            Predicate<Module> moduleCodePredicate = new ModuleCodeContainsKeywordsPredicate(keywords);
            Predicate<Module> tagsPredicate = new ModuleContainsTagsPredicate(keywords);
            // chain the predicates
            return moduleCodePredicate.or(tagsPredicate).test(module);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                           || (other instanceof CompoundFindPredicate // instanceof handles nulls
                                       && keywords.equals(((CompoundFindPredicate) other).keywords)); // state check
        }
    }
}
