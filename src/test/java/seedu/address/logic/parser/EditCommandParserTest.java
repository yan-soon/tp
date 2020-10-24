package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103T_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.CREDITS_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.CREDITS_DESC_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CODE_DESC_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CREDITS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CORE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_NON_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NON_CORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CODE_CS2103T, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CODE_DESC_CS2103T, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CODE_DESC_CS2103T, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CODE_DESC_WITH_PREFIX, ModuleCode.MESSAGE_CONSTRAINTS); // invalid
        // code
        assertParseFailure(parser, "1" + INVALID_CREDITS_DESC, ModularCredits.MESSAGE_CONSTRAINTS); // invalid credit
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // valid credit followed by invalid credit. The test case for invalid credit followed by valid credit
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + CREDITS_DESC_CS3216 + INVALID_CREDITS_DESC,
                ModularCredits.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Module} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_CORE + TAG_DESC_NON_CORE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_CORE + TAG_EMPTY + TAG_DESC_NON_CORE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_CORE + TAG_DESC_NON_CORE, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_CODE_DESC_WITH_PREFIX + VALID_CREDITS_CS2103T,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + CREDITS_DESC_CS3216 + TAG_DESC_NON_CORE
                + CODE_DESC_CS2103T_WITH_PREFIX + TAG_DESC_CORE;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_CODE_CS2103T)
                .withModularCredits(VALID_CREDITS_CS3216).withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + CREDITS_DESC_CS3216 + CODE_DESC_CS2103T_WITH_PREFIX;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModularCredits(VALID_CREDITS_CS3216)
                .withModuleCode(VALID_CODE_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // code
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + CODE_DESC_CS2103T_WITH_PREFIX;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_CODE_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // credits
        userInput = targetIndex.getOneBased() + CREDITS_DESC_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withModularCredits(VALID_CREDITS_CS2103T).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CORE;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CORE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + CREDITS_DESC_CS2103T
                + TAG_DESC_CORE + CREDITS_DESC_CS2103T + TAG_DESC_CORE
                + CREDITS_DESC_CS3216 + TAG_DESC_NON_CORE;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModularCredits(VALID_CREDITS_CS3216)
                .withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + INVALID_CREDITS_DESC + CREDITS_DESC_CS3216;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModularCredits(VALID_CREDITS_CS3216).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput =
            targetIndex.getOneBased() + CODE_DESC_CS2103T_WITH_PREFIX + INVALID_CREDITS_DESC + CREDITS_DESC_CS3216;
        descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_CODE_CS2103T)
                .withModularCredits(VALID_CREDITS_CS3216).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
