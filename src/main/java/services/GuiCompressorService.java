package services;

import interfaces.IGuiCompressorService;
import interfaces.ISyntaxTree;
import nodes.AbstractFunction;
import nodes.AbstractStatement;
import nodes.functions.*;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Helps in combining inefficient GUI if statements into one-line.
 */
public class GuiCompressorService implements IGuiCompressorService {

    /**
     * Finds inefficient uses of GUI such as:
     * if(not(condition))then
     * return false
     * else
     * return true
     *
     * @param tree Syntax tree with unoptimized GUI
     * @return List of functions which can be optimized
     */
    @Override
    public List<Function> getOptimizableFunctionsBasedOnIf(ISyntaxTree tree) {
        List<Function> optimizableFunctions = new ArrayList<>();
        for(AbstractFunction abstractFunction : tree.getScript().getFunctionsSection().getFunctions()) {
            if(abstractFunction instanceof Function) {
                Function function = (Function)abstractFunction;
                Statements statements = function.getStatements();
                List<AbstractStatement> abstractStatements = statements.getStatements();
                // Should be an If Statement with an inner return, and then endif and return
                if(abstractStatements.size() == 2) {
                    // Grab the if and return statement
                    AbstractStatement statement1 = abstractStatements.get(0);
                    AbstractStatement statement2 = abstractStatements.get(1);
                    if(statement1 instanceof IfStatement && statement2 instanceof ReturnStatement) {
                        IfStatement ifStatement = (IfStatement)statement1;
                        ReturnStatement returnStatement = (ReturnStatement)statement2;
                        if ((returnStatement.toString().equals("return true") || returnStatement.toString().equals("return false")) &&
                                ifStatement.getElseifConditions().isEmpty() && ifStatement.getElseifStatements().isEmpty() &&
                                        ifStatement.getElseStatements() == null) {

                            if(ifStatement.getThenStatements() != null && ifStatement.getThenStatements().getStatements().size() == 1) {
                                if(ifStatement.getThenStatements().getStatements().get(0) instanceof ReturnStatement) {
                                    ReturnStatement finalReturn = (ReturnStatement)ifStatement.getThenStatements().getStatements().get(0);
                                    if (finalReturn.toString().equals("return true") || finalReturn.toString().equals("return false")) {
                                        // After all this checking, we are now sure this is optimizable.
                                        optimizableFunctions.add(function);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return optimizableFunctions;
    }

    /**
     * Optimizes a bad gui if statement into a one-liner.
     *
     * @param oldFunction Old function
     * @return New optimized function
     */
    @Override
    public Function optimize(Function oldFunction) {
        IfStatement ifStatement = (IfStatement)oldFunction.getStatements().getStatements().get(0);
        FunctionDeclaration declaration = oldFunction.getFunctionDeclaration();
        Argument mainCondition = ifStatement.getCondition();
        boolean not = mainCondition.isNot();
        if(not) {
            mainCondition = mainCondition.getNotPart();
        }
        boolean returnTrueIfTrue = ifStatement.getThenStatements().getStatements().get(0).toString().equalsIgnoreCase("return true");
        String newReturnStatement = "";
        // Handle the convoluted logic of not and return false in the then block
        if(returnTrueIfTrue && !not) {
            newReturnStatement = "return " + mainCondition.toString();
        } else if(returnTrueIfTrue && not) {
            newReturnStatement = "return not(" + mainCondition.toString() + ")";
        } else if(!returnTrueIfTrue && !not) {
            newReturnStatement = "return not(" + mainCondition.toString() + ")";
        } else if(!returnTrueIfTrue && not) {
            newReturnStatement = "return " + mainCondition.toString();
        }
        // Build the final statement that replaces this function
        AbstractStatement convertedStatement = new ReturnStatement(new Scanner(newReturnStatement), new TreeContext());
        List<AbstractStatement> statementList = new ArrayList<>();
        statementList.add(convertedStatement);
        Statements statements = new Statements(statementList, new TreeContext());
        return new Function(declaration, statements, new TreeContext());
    }
}
