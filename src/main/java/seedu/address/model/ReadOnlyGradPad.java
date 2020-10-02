package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;

/**
 * Unmodifiable view of a GradPad
 */
public interface ReadOnlyGradPad {

    /**
     * Returns an unmodifiable view of the module list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

}
