package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;


/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_ALIAS = "a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRANSACTION = "This transaction already exists in the database";

    private final Transaction newTransaction;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Transaction transaction) {
        requireNonNull(transaction);
        newTransaction = transaction;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasTransaction(newTransaction)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRANSACTION);
        }

        model.addTransaction(newTransaction);

        model.commitFinancialDatabase();
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTransaction)
                                       .concat(String.format(MESSAGE_SUCCESS, newTransaction)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && newTransaction.equals(((AddCommand) other).newTransaction));
    }
}
