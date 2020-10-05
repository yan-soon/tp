package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Module module) {
        return AddCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CODE + module.getModuleCode().toString() + " ");
        sb.append(PREFIX_CREDITS + module.getModularCredits().value + " ");
        module.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleCode().ifPresent(code -> sb.append(PREFIX_CODE).append(code.toString()).append(" "));
        descriptor.getModularCredits().ifPresent(credits -> sb.append(PREFIX_CREDITS).append(credits.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
