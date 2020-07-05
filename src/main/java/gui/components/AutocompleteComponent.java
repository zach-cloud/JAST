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

public final class AutocompleteComponent extends GenericComponent {

    private String currentAutocompleteWord = "";
    private String autocompleteDesired;
    private boolean browserDisplayed = false;

    public AutocompleteComponent(ComponentContext context) {
        super(context);
    }

    public void setupAutocomplete(Scene scene) {
        context.jassCodeEditor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > s.length()) {
                    String diff = StringUtils.difference(s, t1).charAt(0)+"";
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

    public void searchForFunction() {
        String input = JOptionPane.showInputDialog("Search for: ");
        if(input != null && !input.isEmpty()) {
            currentAutocompleteWord = input;
            showAutocomplete();
        }
    }

    public void clearBrowser() {
        System.out.println(currentAutocompleteWord);
        currentAutocompleteWord = "";
        context.functionBrowser.replaceText("");
    }

    public void runAutocomplete() {
        if(browserDisplayed) {
            if(autocompleteDesired != null && !autocompleteDesired.isEmpty()) {
                // Find word before caret
                int finalPosition = context.jassCodeEditor.getCaretPosition();
                int firstPosition = context.jassCodeEditor.getCaretPosition();
                String substring = "";
                while(firstPosition > 0 && !substring.contains(" ") && !substring.contains("\n")) {
                    firstPosition--;
                    substring = context.jassCodeEditor.getText().substring(firstPosition, finalPosition);
                }
                if(firstPosition != 0) {
                    firstPosition++;
                }
                context.jassCodeEditor.replaceText(firstPosition, finalPosition, autocompleteDesired);
                currentAutocompleteWord = "";
                autocompleteDesired = "";
            }
        }
    }

    public void showFunctionBrowser() {
        if (!browserDisplayed) {
            bindElementSizesSmall();
            browserDisplayed = true;
        }
    }

    public void hideFunctionBrowser() {
        if (browserDisplayed) {
            bindElementSizes();
            browserDisplayed = false;
        }
    }

    public void bindElementSizes() {
        context.root.prefHeightProperty().bind(context.stage.heightProperty());
        context.jassCodeEditor.prefHeightProperty().bind(context.root.heightProperty());
        context.functionBrowser.setPrefHeight(0);
    }

    public void bindElementSizesSmall() {
        context.jassCodeEditor.prefHeightProperty().bind(context.root.heightProperty().subtract(200));
        context.functionBrowser.setPrefHeight(200);
        showAutocomplete();
    }

    public void toggleFunctionBrowser() {
        if(browserDisplayed) {
            hideFunctionBrowser();
        } else {
            showFunctionBrowser();
        }
    }

    private void showAutocomplete() {
        if(browserDisplayed) {
            boolean foundAutocomplete = false;
            StringBuilder builder = new StringBuilder();
            for (AbstractFunction function : context.commonFunctions) {
                if (function.getName().startsWith(currentAutocompleteWord)) {
                    builder.append(function.toString()).append("\n");
                    // TODO: Fix this nasty code when TypeFunction is refactored.
                    // TODO: Oh my gosh this is really nasty. But it works.
                    if(!foundAutocomplete) {
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

                            if(!inputs.getInputs().isEmpty()) {
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
            for(String entry: context.autocompleteEntries) {
                if(entry.startsWith(currentAutocompleteWord)) {
                    builder.append(entry).append("\n");
                    if(!foundAutocomplete) {
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
}
