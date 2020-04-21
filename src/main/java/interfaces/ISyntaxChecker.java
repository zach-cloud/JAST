package interfaces;

import exception.SyntaxErrorException;

/**
 * Represents an abstract JASS syntax checker
 */
public interface ISyntaxChecker {

    /**
     * Checks the tree for valid syntax.
     *
     * @param tree  Syntax tree to check
     */
    void syntaxCheck(ISyntaxTree tree) throws SyntaxErrorException;
}
