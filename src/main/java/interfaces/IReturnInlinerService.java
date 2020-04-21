package interfaces;

import nodes.functions.Function;

/**
 * Service to help inline one-liner return statements
 */
public interface IReturnInlinerService {

    /**
     * Inlines one-line return statements
     *
     * @param whichFunction Function to inline
     * @param original      The tree containing the function
     * @return              Syntax tree with inlined functions
     */
    ISyntaxTree inline(Function whichFunction, ISyntaxTree original);

    /**
     * Inlines all applicable functions
     *
     * @param original  Tree containing functions to inline
     * @return          Syntax tree with inlined functions
     */
    ISyntaxTree inlineAll(ISyntaxTree original);
}
