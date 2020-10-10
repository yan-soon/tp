package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.CREDITS_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.CREDITS_DESC_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CREDITS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CORE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_NON_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NON_CORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS3216;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ModuleBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(CS3216).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CODE_DESC_CS3216 + CREDITS_DESC_CS3216
                + TAG_DESC_NON_CORE, new AddCommand(expectedModule));

        // multiple codes - last code accepted
        assertParseSuccess(parser, CODE_DESC_CS2103T + CODE_DESC_CS3216 + CREDITS_DESC_CS3216
                + TAG_DESC_NON_CORE, new AddCommand(expectedModule));

        // multiple credits - last credit accepted
        assertParseSuccess(parser, CODE_DESC_CS3216 + CREDITS_DESC_CS2103T + CREDITS_DESC_CS3216
                + TAG_DESC_NON_CORE, new AddCommand(expectedModule));

        // multiple tags - all accepted
        Module expectedModuleMultipleTags = new ModuleBuilder(CS3216).withModularCredits(VALID_CREDITS_CS3216)
                .withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE)
                .build();
        assertParseSuccess(parser, CODE_DESC_CS3216 + CREDITS_DESC_CS3216
                + TAG_DESC_CORE + TAG_DESC_NON_CORE, new AddCommand(expectedModuleMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Module expectedModule = new ModuleBuilder(CS2103T).withTags().build();
        assertParseSuccess(parser, CODE_DESC_CS2103T + CREDITS_DESC_CS2103T,
                new AddCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing code prefix
        assertParseFailure(parser, VALID_CODE_CS3216 + CREDITS_DESC_CS3216,
                expectedMessage);

        // missing credit prefix
        assertParseFailure(parser, CODE_DESC_CS3216 + VALID_CREDITS_CS3216,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CODE_CS3216 + VALID_CREDITS_CS3216,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid code
        assertParseFailure(parser, INVALID_CODE_DESC + CREDITS_DESC_CS3216
                + TAG_DESC_CORE + TAG_DESC_NON_CORE, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid credits
        assertParseFailure(parser, CODE_DESC_CS3216 + INVALID_CREDITS_DESC
                + TAG_DESC_CORE + TAG_DESC_NON_CORE, ModularCredits.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CODE_DESC_CS3216 + CREDITS_DESC_CS3216
                + INVALID_TAG_DESC + VALID_TAG_NON_CORE, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_CODE_DESC + CREDITS_DESC_CS3216,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CODE_DESC_CS3216 + CREDITS_DESC_CS3216
                        + TAG_DESC_CORE + TAG_DESC_NON_CORE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
