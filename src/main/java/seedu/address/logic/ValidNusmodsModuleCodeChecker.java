package seedu.address.logic;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.nusmods.NusmodsDataManager;
import seedu.address.nusmods.exceptions.NusmodsException;

/**
 * Checks if a module code is a valid module code in NUSMods.
 */
public class ValidNusmodsModuleCodeChecker {
    private NusmodsDataManager dataManager;

    public ValidNusmodsModuleCodeChecker() {
        dataManager = new NusmodsDataManager();
    }

    /**
     * Checks if a module code is a valid module code based on whether a module title
     * can be retrieved from NUSMods.
     *
     * @param moduleCode the module code to be checked.
     * @return true if a module title can be retrieved from NUSMods, and false otherwise.
     * @throws CommandException if an error occurs during the module title retrieval process.
     */
    public boolean isValidNusmodsModuleCode(String moduleCode) throws CommandException {
        try {
            Optional<String> moduleTitle = dataManager.getModuleTitle(moduleCode);
            if (moduleTitle.isEmpty()) {
                return false;
            }
        } catch (NusmodsException e) {
            throw new CommandException(e.getMessage());
        }
        return true;
    }
}
