package gui.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import nodes.AbstractFunction;
import nodes.functions.Function;
import nodes.functions.Input;
import nodes.functions.Inputs;
import nodes.functions.NativeFunction;
import org.apache.commons.lang3.StringUtils;
import org.reactfx.Subscription;

import javax.swing.*;
import java.time.Duration;

/**
 * Component for showing/running autocomplete.
 */
public final class AutocompleteComponent extends GenericComponent {

    /**
     * Tracks current text that the user has written, clears out on spaces/newlines
     */
    private String currentAutocompleteWord = "";
    /**
     * Current word the user wants to fill in with autocomplete.
     */
    private String autocompleteDesired;
    /**
     * True if function browser is displayed; false if not
     */
    private boolean browserDisplayed = false;

    /**
     * Required constructor.
     *
     * @param context Component context.
     */
    public AutocompleteComponent(ComponentContext context) {
        super(context);
    }

    /**
     * Sets up autocomplete on the JASS code editor box
     */
    public void setupAutocomplete() {
        context.jassCodeEditor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > s.length()) {
                    String diff = StringUtils.difference(s, t1).charAt(0) + "";
                    if (diff.equalsIgnoreCase(" ") || diff.equalsIgnoreCase("\n")) {
                        currentAutocompleteWord = "";
                    } else {
                        currentAutocompleteWord += diff;
                    }
                } else {
                    try {
                        currentAutocompleteWord = currentAutocompleteWord.substring(0, currentAutocompleteWord.length() - 1);
                    } catch (Exception ex) {
                        currentAutocompleteWord = "";
                    }
                }
            }
        });

        Subscription cleanupWhenNoLongerNeedIt = context.jassCodeEditor
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(100))
                .subscribe(ignore -> showAutocomplete());
    }

    /**
     * Function browser search for a specific word
     */
    public void searchForFunction() {
        String input = JOptionPane.showInputDialog("Search for: ");
        if (input != null && !input.isEmpty()) {
            currentAutocompleteWord = input;
            showAutocomplete();
        }
    }

    /**
     * Clears function browser
     */
    public void clearBrowser() {
        System.out.println(currentAutocompleteWord);
        currentAutocompleteWord = "";
        context.functionBrowser.replaceText("");
    }

    /**
     * When the user presses the autocomplete hotkey
     * Autofill at their current cursor location
     */
    public void runAutocomplete() {
        if (autocompleteDesired != null && !autocompleteDesired.isEmpty()) {
            // Find word before caret
            int finalPosition = context.jassCodeEditor.getCaretPosition();
            int firstPosition = context.jassCodeEditor.getCaretPosition();
            String substring = "";
            while (firstPosition > 0 && !substring.contains(" ") && !substring.contains("\n")) {
                firstPosition--;
                substring = context.jassCodeEditor.getText().substring(firstPosition, finalPosition);
            }
            if (firstPosition != 0) {
                firstPosition++;
            }
            context.jassCodeEditor.replaceText(firstPosition, finalPosition, autocompleteDesired);
            currentAutocompleteWord = "";
            autocompleteDesired = "";
        }
    }

    /**
     * Displays function browser.
     */
    public void showFunctionBrowser() {
        if (!browserDisplayed) {
            bindElementSizesSmall();
            browserDisplayed = true;
        }
    }

    /**
     * Hides function browser
     */
    public void hideFunctionBrowser() {
        if (browserDisplayed) {
            bindElementSizes();
            browserDisplayed = false;
        }
    }

    /**
     * Make the JASS edit fill screen
     * TODO: Move
     */
    public void bindElementSizes() {
        context.root.prefHeightProperty().bind(context.stage.heightProperty());
        context.jassCodeEditor.prefHeightProperty().bind(context.root.heightProperty());
        context.functionBrowser.setPrefHeight(0);
    }

    /**
     * Makes the JASS editor + function browser fill screen
     * TODO: Move
     */
    private void bindElementSizesSmall() {
        context.jassCodeEditor.prefHeightProperty().bind(context.root.heightProperty().subtract(200));
        context.functionBrowser.setPrefHeight(200);
        showAutocomplete();
    }

    /**
     * Turns function browser on/off
     */
    public void toggleFunctionBrowser() {
        if (browserDisplayed) {
            hideFunctionBrowser();
        } else {
            showFunctionBrowser();
        }
    }

    /**
     * Displays auto complete to user (via function browser)
     * and computes current desired autocomplete word
     */
    private void showAutocomplete() {
        boolean foundAutocomplete = false;
        StringBuilder builder = new StringBuilder();
        for (AbstractFunction function : context.commonFunctions) {
            if (function.getName().startsWith(currentAutocompleteWord)) {
                builder.append(function.toString()).append("\n");
                // TODO: Fix this nasty code when TypeFunction is refactored.
                // TODO: Oh my gosh this is really nasty. But it works.
                if (!foundAutocomplete) {
                    foundAutocomplete = true;
                    autocompleteDesired = function.getName();
                    if (function instanceof Function || function instanceof NativeFunction) {
                        autocompleteDesired += "(";
                        Inputs inputs = null;
                        if (function instanceof Function) {
                            inputs = ((Function) function).getFunctionDeclaration().getInputs();
                        } else if (function instanceof NativeFunction) {
                            inputs = ((NativeFunction) function).getInputs();
                        }

                        if (!inputs.getInputs().isEmpty()) {
                            for (Input input : inputs.getInputs()) {
                                autocompleteDesired += input.getType() + ",";
                            }
                            autocompleteDesired = autocompleteDesired.substring(0, autocompleteDesired.length() - 1);
                        }
                        autocompleteDesired += ")";
                    }
                }

            }
        }
        for (String entry : context.autocompleteEntries) {
            if (entry.startsWith(currentAutocompleteWord)) {
                builder.append(entry).append("\n");
                if (!foundAutocomplete) {
                    foundAutocomplete = true;
                    autocompleteDesired = entry;
                }
            }
        }
        if (builder.length() > 0) {
            context.functionBrowser.replaceText(builder.toString());
        }
    }

}
