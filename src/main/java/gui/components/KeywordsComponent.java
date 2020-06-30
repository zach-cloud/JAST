package gui.components;

import interfaces.ISyntaxTree;
import nodes.AbstractFunction;
import nodes.functions.TypeDeclaration;
import nodes.j.Variable;

import java.util.ArrayList;
import java.util.Collections;

public class KeywordsComponent extends GenericComponent {

    public KeywordsComponent(ComponentContext context) {
        super(context);
    }

    public void setupKeywords() {
        addKeywords(context.blizzardLoaderService.loadCommon());
        addKeywords(context.blizzardLoaderService.loadBlizzard());
        context.types.add("string");
        context.types.add("integer");
        context.types.add("real");
        context.types.add("boolean");
        context.types.add("array");
        context.autocompleteEntries.addAll(context.types);
        for (Variable variable : context.commonVariables) {
            context.autocompleteEntries.add(variable.getName());
        }
        Collections.addAll(context.autocompleteEntries, context.keywords);
    }

    /**
     * Reads keywords from common/blizzard
     *
     * @param tree Keywords tree
     */
    private void addKeywords(ISyntaxTree tree) {
        if(tree.getTypes() != null) {
            for (TypeDeclaration type : tree.getTypes()) {
                context.types.add(type.getName());
            }
        }
        for (AbstractFunction function : tree.getScript().getFunctionsSection().getFunctions()) {
            context.natives.add(function.getName());
            context.commonFunctions.add(function);
        }
        for (Variable variable : tree.getScript().getGlobalsSection().getGlobalVariables()) {
            context.natives.add(variable.getName());
            context.commonVariables.add(variable);
        }
    }
}
