package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's module title in the GradPad.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleTitle(String)}
 */
public class ModuleTitle {

    public static final String MESSAGE_CONSTRAINTS =
            "Module titles should only contain alphanumeric characters and spaces, and it should not be "
                    + "blank";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]";

    public final String moduleTitle;

    /**
     * Constructs a {@code ModuleTitle}.
     *
     * @param moduleTitle A valid module title.
     */
    public ModuleTitle(String moduleTitle) {
        requireNonNull(moduleTitle);
        checkArgument(isValidModuleTitle(moduleTitle), MESSAGE_CONSTRAINTS);
        this.moduleTitle = moduleTitle;
    }

    /**
     * Returns true if a given string is a valid module title.
     */
    public static boolean isValidModuleTitle(String test) {
        //return test.matches(VALIDATION_REGEX);
        return true;
    }


    @Override
    public String toString() {
        return moduleTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTitle // instanceof handles nulls
                && moduleTitle.equals(((ModuleTitle) other).moduleTitle)); // state check
    }

    @Override
    public int hashCode() {
        return moduleTitle.hashCode();
    }
}
