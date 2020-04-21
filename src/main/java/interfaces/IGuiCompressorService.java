package interfaces;

import nodes.functions.Function;

import java.util.List;

/**
 * Helps in combining inefficient GUI if statements into one-line.
 */
public interface IGuiCompressorService {

    /**
     * Finds inefficient uses of GUI such as:
     * if(condition)then
     * return false
     * else
     * return true
     *
     * @param tree  Syntax tree with unoptimized GUI
     * @return      List of functions which can be optimized
     */
    List<Function> getOptimizableFunctionsBasedOnIf(ISyntaxTree tree);

    /**
     * Optimizes a bad gui if statement into a one-liner.
     *
     * @param oldFunction   Old function
     * @return              New optimized function
     */
    Function optimize(Function oldFunction);

}
