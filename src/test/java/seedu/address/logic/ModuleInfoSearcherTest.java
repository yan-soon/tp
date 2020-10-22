package seedu.address.logic;

import org.junit.jupiter.api.Test;
import seedu.address.nusmods.NusmodsDataManager;

import seedu.address.logic.commands.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.*;

class ModuleInfoSearcherTest {

    private ModuleInfoSearcher moduleInfoSearcher = new ModuleInfoSearcher();

    @Test
    public void search_empty_module() {
        String emptyModule = "";
//        assertCommandException(moduleInfoSearcher.searchModule(emptyModule), ModuleInfoSearcher.MESSAGE_EMPTY_SEARCH);
    }

}
