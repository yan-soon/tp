package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CONSTRAINTS_CREDITS;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's no. of modular credits in the GradPad.
 * Guarantees: immutable; is valid as declared in {@link #isValidMC(String)}
 */
public class ModularCredits {

    public static final String VALIDATION_REGEX = "\\d{1,2}";
    public final String value;

    /**
     * Constructs a {@code ModularCredits}.
     *
     * @param credits A valid value of modular credits.
     */
    public ModularCredits(String credits) {
        requireNonNull(credits);
        checkArgument(isValidMC(credits), MESSAGE_CONSTRAINTS_CREDITS);
        value = credits;
    }

    /**
     * Returns true if a given string is a valid no. of modular credits.
     */
    public static boolean isValidMC(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModularCredits // instanceof handles nulls
                && value.equals(((ModularCredits) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
