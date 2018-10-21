package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Calculates an interest rate for a given transaction (either using simple or compound scheme as specified by the
 * user).
 */
public class InterestCommand extends Command {
    public static final String COMMAND_WORD = "interest";
    public static final String COMMAND_ALIAS = "int";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculates interest on transactions filtered"
            + "by name(s)\n"
            + "Parameters: <SCHEME> <INTEREST_RATE> KEYWORD [MORE KEYWORDS..]"
            + "Example: " + COMMAND_WORD + " simple 1.3% alex bernice irfan";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        return null;
    }
}
