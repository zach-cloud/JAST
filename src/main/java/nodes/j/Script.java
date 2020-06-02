package nodes.j;

import interfaces.IFunctionRenameable;
import interfaces.IMergable;
import interfaces.IVariableRenameable;
import nodes.AbstractFunction;
import nodes.AbstractNode;
import exception.ParsingException;
import nodes.functions.Argument;
import nodes.functions.TypeFunction;
import nodes.vjass.Library;
import nodes.vjass.Scope;
import nodes.vjass.Struct;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a .j file with a globals/functions section.
 */
public final class Script extends AbstractNode implements IMergable, IFunctionRenameable, IVariableRenameable {

    private GlobalsSection globalsSection;
    private FunctionsSection functionsSection;
    private List<TypeFunction> types;
    private List<Library> libraries;
    private List<Scope> scopes;
    private List<Struct> structs;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public Script(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    public Script(GlobalsSection globalsSection, FunctionsSection functionsSection, List<TypeFunction> types, TreeContext context) {
        super(context);
        this.globalsSection = globalsSection;
        this.functionsSection = functionsSection;
        this.types = types;
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public final String toString() {
        StringBuilder returnValue = new StringBuilder();
        if(globalsSection != null) {
            returnValue.append(globalsSection.toString());
            returnValue.append("\n");
        }
        if(functionsSection != null) {
            returnValue.append(functionsSection.toString());
            returnValue.append("\n");
        }
        if(libraries != null && libraries.size() > 0) {
            for(Library library : libraries) {
                returnValue.append(library.toString());
                returnValue.append("\n");
            }
        }
        if(scopes != null && scopes.size() > 0) {
            for(Scope scope : scopes) {
                returnValue.append(scope.toString());
                returnValue.append("\n");
            }
        }
        if(structs != null && structs.size() > 0) {
            for(Struct struct : structs) {
                returnValue.append(struct.toString());
                returnValue.append("\n");
            }
        }

        removeFinalCharacter(returnValue);
        return returnValue.toString();
    }

    /**
     * Converts this node back to its original form.
     *
     * @param indentationLevel Current indentation level
     * @return Original form of this node (code or string) with indentation
     */
    @Override
    public String toFormattedString(int indentationLevel) {
        StringBuilder builder = new StringBuilder();
        if(globalsSection != null) {
            builder.append(globalsSection.toFormattedString(indentationLevel + 1));
            builder.append("\n");
        }
        if(functionsSection != null) {
            builder.append(functionsSection.toFormattedString(indentationLevel + 1));
            builder.append("\n");
        }
        if(libraries != null && libraries.size() > 0) {
            for(Library library : libraries) {
                builder.append(library.toFormattedString(indentationLevel + 1));
                builder.append("\n");
            }
        }
        if(scopes != null && scopes.size() > 0) {
            for(Scope scope : scopes) {
                builder.append(scope.toFormattedString(indentationLevel + 1));
                builder.append("\n");
            }
        }
        if(structs != null && structs.size() > 0) {
            for(Struct struct : structs) {
                builder.append(struct.toFormattedString(indentationLevel + 1));
                builder.append("\n");
            }
        }
        removeFinalCharacter(builder);
        return builder.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        boolean readingGlobals = false; // set to true when "globals" is discovered
        boolean readingFunctions = true; // set to true when "endglobals" is discovered
        StringBuilder currentAccumulatedString = new StringBuilder(); // contains either the globals or endglobal section
        boolean readingLibrary = false;
        boolean readingScope = false;
        boolean readingStruct = false;

        while(hasNextLine()) {
            String line = readLine();
            if(line.equals("globals") && !readingLibrary && !readingScope && !readingStruct) {
                // Read the entire script until endglobals
                if (!readingGlobals) {
                    readingGlobals = true;
                    readingFunctions = false;
                    currentAccumulatedString.append(line).append("\n");
                } else {
                    throw new ParsingException("Nested globals section not supported: " + line);
                }
            } else if(line.startsWith("type")  && !readingLibrary && !readingScope && !readingStruct) {
                if(types == null) {
                    types = new ArrayList<>();
                }
                TypeFunction typeFunction = new TypeFunction(new Scanner(line), context);
                types.add(typeFunction);
            } else if(line.equals("endglobals")  && !readingLibrary && !readingScope && !readingStruct) {
                // Read the entire script until EOF
                if (readingFunctions) {
                    throw new ParsingException("Globals in functions section not supported: " + line);
                }
                if (readingGlobals) {
                    readingGlobals = false;
                    currentAccumulatedString.append(line);
                    // Parse the globals before resetting
                    this.globalsSection = new GlobalsSection(new Scanner(currentAccumulatedString.toString()), context);
                    currentAccumulatedString = new StringBuilder();
                    readingFunctions = true;
                } else {
                    throw new ParsingException("Found endglobals before globals: " + line);
                }
            } else if(line.startsWith("library")) {
                if(currentAccumulatedString.length() > 0) {
                    saveData(readingFunctions, currentAccumulatedString, readingLibrary, readingScope, readingStruct);
                }
                readingLibrary = true;
                readingFunctions = false;
                currentAccumulatedString.append(line).append("\n");
            } else if(line.startsWith("endlibrary")) {
                if (libraries == null) {
                    libraries = new ArrayList<>();
                }
                currentAccumulatedString.append(line);
                libraries.add(new Library(new Scanner(currentAccumulatedString.toString()), context));
                currentAccumulatedString.setLength(0);
                readingLibrary = false;
            } else if(line.startsWith("scope")) {
                if(currentAccumulatedString.length() > 0) {
                    saveData(readingFunctions, currentAccumulatedString, readingLibrary, readingScope, readingStruct);
                }
                readingScope = true;
                readingFunctions = false;
                currentAccumulatedString.append(line).append("\n");
            } else if(line.startsWith("endscope")) {
                if (scopes == null) {
                    scopes = new ArrayList<>();
                }
                currentAccumulatedString.append(line);
                scopes.add(new Scope(new Scanner(currentAccumulatedString.toString()), context));
                currentAccumulatedString.setLength(0);
                readingScope = false;
            } else if(line.startsWith("struct")) {
                if(currentAccumulatedString.length() > 0) {
                    saveData(readingFunctions, currentAccumulatedString, readingLibrary, readingScope, readingStruct);
                }
                readingStruct = true;
                readingFunctions = false;
                currentAccumulatedString.append(line).append("\n");
            } else if(line.startsWith("endstruct")) {
                if (structs == null) {
                    structs = new ArrayList<>();
                }
                currentAccumulatedString.append(line);
                structs.add(new Struct(new Scanner(currentAccumulatedString.toString()), context));
                currentAccumulatedString.setLength(0);
                readingStruct = false;
            } else {
                currentAccumulatedString.append(line).append("\n");
            }
        }
        saveData(readingFunctions, currentAccumulatedString, readingLibrary, readingScope, readingStruct);
    }

    private void saveData(boolean readingFunctions, StringBuilder currentAccumulatedString, boolean readingLibrary, boolean readingScope, boolean readingStruct) {
        if (readingFunctions) {
            // Finally parse the Functions
            this.functionsSection = new FunctionsSection(new Scanner(currentAccumulatedString.toString()), context);
        }
        if (readingLibrary && currentAccumulatedString.length() > 0) {
            if (libraries == null) {
                libraries = new ArrayList<>();
            }
            libraries.add(new Library(new Scanner(currentAccumulatedString.toString()), context));
        }
        if (readingScope && currentAccumulatedString.length() > 0) {
            if (scopes == null) {
                scopes = new ArrayList<>();
            }
            scopes.add(new Scope(new Scanner(currentAccumulatedString.toString()), context));
        }
        if (readingStruct && currentAccumulatedString.length() > 0) {
            if (structs == null) {
                structs = new ArrayList<>();
            }
            structs.add(new Struct(new Scanner(currentAccumulatedString.toString()), context));
        }
        currentAccumulatedString.setLength(0);
    }

    /**
     * Renames the variable and all uses of this variable.
     *
     * @param oldVariableName   Existing variable name
     * @param newVariableName   Desired variable name
     * @return                  AST Node with variable declaration/use changed
     */
    @Override
    public final AbstractNode renameVariable(String oldVariableName, String newVariableName) {
        GlobalsSection newGlobals = (GlobalsSection)globalsSection.renameVariable(oldVariableName, newVariableName);
        FunctionsSection newFunctions = (FunctionsSection)functionsSection.renameVariable(oldVariableName, newVariableName);
        return new Script(newGlobals, newFunctions, types, context);
    }

    /**
     * Renames a function and uses to a new name
     *
     * @param oldFunctionName   Existing function name
     * @param newFunctionName   Desired function name
     * @return                  This node with function/uses renamed
     */
    @Override
    public final AbstractNode renameFunction(String oldFunctionName, String newFunctionName) {
        GlobalsSection newGlobals = globalsSection;
        FunctionsSection newFunctions = (FunctionsSection)functionsSection.renameFunction(oldFunctionName, newFunctionName);
        return new Script(newGlobals, newFunctions, types, context);
    }

    public final GlobalsSection getGlobalsSection() {
        return globalsSection;
    }

    public final FunctionsSection getFunctionsSection() {
        return functionsSection;
    }

    /**
     * Combines this AST Node with another and then checks
     * for errors. Gracefully handles function main.
     *
     * @param other Other AST node to combine
     * @return      Merged AST nodes
     */
    @Override
    public final AbstractNode merge(AbstractNode other) {
        Script otherScript = (Script)other;
        GlobalsSection newGlobals = (GlobalsSection)this.globalsSection.merge(otherScript.globalsSection);
        FunctionsSection newFunctions = (FunctionsSection)this.functionsSection.merge(otherScript.functionsSection);
        return new Script(newGlobals, newFunctions, types, context);
    }

    public final List<Argument> getArguments() {
        List<Argument> baseArguments = new ArrayList<>();
        List<Argument> finalArguments = new ArrayList<>();

        for(Variable variable : globalsSection.getGlobalVariables()) {
            baseArguments.add(variable.getInitialValue());
        }

        for(AbstractFunction function : functionsSection.getFunctions()) {
            baseArguments.addAll(function.getArguments());
        }

        for(Argument argument : baseArguments) {
            if(argument != null) {
                finalArguments.addAll(argument.getArguments());
            }
        }

        return finalArguments;
    }

    public List<TypeFunction> getTypes() {
        return types;
    }

    public void addFunctionMain() {
        if(functionsSection == null) {
            functionsSection = new FunctionsSection(new Scanner("function main takes nothing returns nothing\nendfunction"), new TreeContext());
        } else {
            functionsSection.addFunctionMain();
        }
    }
}
