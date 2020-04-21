package interfaces;

import nodes.AbstractNode;

/**
 * Represents a Node's behavior of being able to be merged with
 * another node of the same type without causing error.
 */
public interface IMergable {

    /**
     * Combines this AST Node with another and then checks
     * for errors. Gracefully handles function main.
     *
     * @param other Other AST node to combine
     * @return      Merged AST nodes
     */
    AbstractNode merge(AbstractNode other);
}
