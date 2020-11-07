package seedu.address.logic;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.nusmods.ModuleInfo;
import seedu.address.nusmods.NusmodsDataManager;
import seedu.address.nusmods.exceptions.NusmodsException;

/**
 * Logic for searching a module from the Computer Science curriculum.
 */
public class ModuleInfoSearcher {
    public static final String MESSAGE_FAILED_TO_FIND_MODULE = "Failed to find module: %s";
    public static final String MESSAGE_EMPTY_SEARCH = "Search is empty";

    private NusmodsDataManager dataManager;

    public ModuleInfoSearcher() {
        dataManager = new NusmodsDataManager();
    }

    ModuleInfoSearcher(NusmodsDataManager dataManager) {
        this.dataManager = dataManager;
    }

    /**
     * Perform search function on NUSMods using dataManager.
     * @param moduleCode from SearchCommandParser.
     * @return ModuleInfo of the module searched.
     * @throws CommandException if an error occurs during the search process.
     */
    public ModuleInfo searchModule(String moduleCode) throws CommandException {
        ModuleInfo moduleInfo;
        if (moduleCode.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_SEARCH);
        }
        try {
            Optional<ModuleInfo> moduleDetails = dataManager.getModuleInfo(moduleCode);
            if (moduleDetails.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_FAILED_TO_FIND_MODULE, moduleCode));
            }
            moduleInfo = moduleDetails.get();
        } catch (NusmodsException e) {
            throw new CommandException(e.getMessage());
        }
        return moduleInfo;
    }
}
