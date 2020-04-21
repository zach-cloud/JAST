package interfaces;

import nodes.AbstractNode;

/**
 * Represents a Node type that can rename variables
 * or uses of variables inside it.
 */
public interface IVariableRenameable {

    /**
     * Renames the variable and all uses of this variable.
     *
     * @param oldVariableName   Existing variable name
     * @param newVariableName   Desired variable name
     * @return                  AST Node with variable declaration/use changed
     */
    AbstractNode renameVariable(String oldVariableName, String newVariableName);
}
