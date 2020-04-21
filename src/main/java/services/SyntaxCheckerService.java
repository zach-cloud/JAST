package services;

import nodes.AbstractFunction;
import nodes.AbstractStatement;
import nodes.functions.Function;
import nodes.functions.LocalStatement;
import nodes.functions.ReturnStatement;
import nodes.functions.Statements;
import nodes.j.FunctionsSection;
import nodes.j.GlobalsSection;
import nodes.j.Script;
import nodes.j.Variable;
import exception.SyntaxErrorException;
import interfaces.ISyntaxChecker;
import interfaces.ISyntaxTree;

import java.util.HashSet;
import java.util.Set;

/**
 * Helper class to check some basic syntax errors
 */
public class SyntaxCheckerService implements ISyntaxChecker {

    /**
     * Checks the tree for valid syntax.
     *
     * @param tree  Syntax tree to check
     */
    @Override
    public void syntaxCheck(ISyntaxTree tree) throws SyntaxErrorException {
        Script script = tree.getScript();
        GlobalsSection globalsSection = script.getGlobalsSection();
        FunctionsSection functionsSection = script.getFunctionsSection();
        checkVariableCollisions(globalsSection);
        checkFunctionsCollisions(functionsSection);
    }

    /**
     * Checks for variable name collisions in the globals section.
     *
     * @param globals Globals section
     */
    private void checkVariableCollisions(GlobalsSection globals) throws SyntaxErrorException {
        Set<String> tempSet = new HashSet<>();
        for(Variable variable : globals.getGlobalVariables()) {
            if(!tempSet.add(variable.getName())) {
                throw new SyntaxErrorException("Variable collision at: " + variable.toString());
            }
        }
    }

    /**
     * Checks for function name collisions in the globals section.
     *
     * @param functions Functions section
     */
    private void checkFunctionsCollisions(FunctionsSection functions) throws SyntaxErrorException {
        Set<String> tempSet = new HashSet<>();
        for(AbstractFunction function : functions.getFunctions()) {
            if(!tempSet.add(function.getName())) {
                throw new SyntaxErrorException("Function collision at: " + function.toString());
            }
        }
    }
}
