package ox.app.utility;

import ox.app.exceptions.WrongArgumentException;
import ox.app.languages.InstructionDriver;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetupChooser {
    private static int retryIterator = 3;

    public static boolean check(Supplier<String> input, Consumer<String> output, InstructionDriver instructionDriver) {
        for (; retryIterator > 0; retryIterator--) {
            try {
                switch (input.get().toLowerCase()) {
                    case "custom":
                        output.accept(instructionDriver.customSettingsMessage());
                        return true;
                    case "default":
                        output.accept(instructionDriver.defaultSettingsMessage());
                        return false;
                    default:
                        if (retryIterator > 1) {
                            throw new WrongArgumentException(instructionDriver.wrongCommandMessage() + (retryIterator - 1));
                        }
                }
            } catch (WrongArgumentException e) {
                output.accept(e.getMessage());
            }
        }
        output.accept(instructionDriver.defaultSettingsMessage());
        return false;
    }
}
