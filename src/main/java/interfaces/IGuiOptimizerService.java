package interfaces;

import model.InputModel;

/**
 * Service to optimize GUI code.
 */
public interface IGuiOptimizerService {

    /**
     * Optimizes all GUI code.
     *
     * @param original  Original code
     * @return          New code
     */
    ISyntaxTree optimize(ISyntaxTree original);

    /**
     * Optimizes all GUI code based on formatted user input
     *
     * @param input  User input
     * @return       New code
     */
    ISyntaxTree runOptimize(InputModel input);
}
