package services;

import interfaces.IGuiCompressorService;
import interfaces.IGuiOptimizerService;
import interfaces.IReturnInlinerService;
import interfaces.ISyntaxTree;
import model.InputModel;
import nodes.AbstractFunction;
import nodes.functions.Function;
import nodes.j.FunctionsSection;
import nodes.j.Script;
import tree.SyntaxTree;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to optimize GUI code.
 */
public class GuiOptimizerService implements IGuiOptimizerService {

    private IGuiCompressorService compressorService;
    private IReturnInlinerService inlinerService;

    public GuiOptimizerService() {
        this.compressorService = new GuiCompressorService();
        this.inlinerService = new ReturnInlinerService();
    }

    public GuiOptimizerService(IGuiCompressorService compressorService, IReturnInlinerService inlinerService) {
        this.compressorService = compressorService;
        this.inlinerService = inlinerService;
    }

    /**
     * Optimizes all GUI code.
     *
     * @param original Original code
     * @return New code
     */
    @Override
    public ISyntaxTree optimize(ISyntaxTree original) {
        List<Function> toOptimize = compressorService.getOptimizableFunctionsBasedOnIf(original);
        List<AbstractFunction> newFunctions = new ArrayList<>();
        for(AbstractFunction function : original.getScript().getFunctionsSection().getFunctions()) {
            if(function instanceof Function) {
                if(toOptimize.contains((Function)function)) {
                    newFunctions.add(compressorService.optimize((Function)function));
                } else {
                    newFunctions.add(function);
                }
            } else {
                newFunctions.add(function);
            }
        }
        ISyntaxTree firstTree = new SyntaxTree(
                new Script(original.getScript().getGlobalsSection(),
                        new FunctionsSection(newFunctions,
                                new TreeContext()), original.getTypes(),
                        new TreeContext()));
        return inlinerService.inlineAll(firstTree);
    }

    /**
     * Optimizes all GUI code based on formatted user input
     *
     * @param input User input
     * @return New code
     */
    @Override
    public ISyntaxTree runOptimize(InputModel input) {
        return optimize(input.getTree1());
    }
}
