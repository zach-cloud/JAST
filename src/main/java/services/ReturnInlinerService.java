package services;

import interfaces.IReturnInlinerService;
import interfaces.ISyntaxTree;
import nodes.AbstractFunction;
import nodes.AbstractStatement;
import nodes.functions.CallStatement;
import nodes.functions.Function;
import nodes.functions.ReturnStatement;
import nodes.functions.Statements;
import nodes.j.FunctionsSection;
import nodes.j.Script;
import tree.SyntaxTree;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to help inline one-liner return statements
 */
public class ReturnInlinerService implements IReturnInlinerService {

    /**
     * Inlines one-line return statements
     *
     * @param whichFunction Function to inline
     * @param original      The tree containing the function
     * @return Syntax tree with inlined functions
     */
    @Override
    public ISyntaxTree inline(Function whichFunction, ISyntaxTree original) {
        ReturnStatement thisStatement = (ReturnStatement)whichFunction.getStatements().getStatements().get(0);
        FunctionsSection section = original.getScript().getFunctionsSection();
        List<AbstractFunction> newFunctions = new ArrayList<>();

        for(AbstractFunction function : section.getFunctions()) {
            if(!function.getName().equals(whichFunction.getName())) {
                if(function instanceof Function) {
                    Function entity = (Function)function;
                    Statements newStatements = entity.getStatements().inline(whichFunction.getName(), thisStatement.getReturnBody());
                    newFunctions.add(new Function(entity.getFunctionDeclaration(), newStatements, new TreeContext()));
                } else {
                    newFunctions.add(function);
                }
            }
        }
        return new SyntaxTree(new Script(original.getScript().getGlobalsSection(), new FunctionsSection(newFunctions, new TreeContext()), new TreeContext()));
    }

    private boolean usesAsFunction(ISyntaxTree original, String functionName) {
        for(AbstractFunction abstractFunction : original.getScript().getFunctionsSection().getFunctions()) {
            if(abstractFunction instanceof Function) {
                Function function = (Function)abstractFunction;
                if(!function.getName().equals(functionName)) {
                    if(function.usesAsFunction(functionName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Inlines all applicable functions
     *
     * @param original Tree containing functions to inline
     * @return Syntax tree with inlined functions
     */
    @Override
    public ISyntaxTree inlineAll(ISyntaxTree original) {
        boolean continueInlining = true;
        while(continueInlining) {
            FunctionsSection functionsSection = original.getScript().getFunctionsSection();
            Function toInline = null;
            for(AbstractFunction abstractFunction : functionsSection.getFunctions()) {
                if(abstractFunction instanceof Function) {
                    Function function = (Function)abstractFunction;
                    if(function.getStatements() != null &&
                            function.getStatements().getStatements().size() == 1 &&
                            function.getStatements().getStatements().get(0) instanceof ReturnStatement &&
                            toInline == null &&
                            !usesAsFunction(original, function.getName()) &&
                            function.getFunctionDeclaration().getOutput().getType().equals("boolean")) {
                        toInline = function;
                    }
                }
            }
            if(toInline != null) {
                original = inline(toInline, original);
            } else {
                continueInlining = false;
            }
        }
        return original;
    }
}
