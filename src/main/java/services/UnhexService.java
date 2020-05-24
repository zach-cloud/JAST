package services;

import interfaces.ISyntaxTree;
import interfaces.IUnhexService;
import nodes.functions.Argument;

import java.util.List;

public class UnhexService implements IUnhexService {

    @Override
    public void unhex(ISyntaxTree tree) {
        List<Argument> arguments = tree.getScript().getArguments();
        System.out.println("(ZACH) " + arguments.size());
        for(Argument arg : arguments) {
            String hex = arg.toString();
            if(hex.startsWith("$")) {
                try {
                    int decimal=Integer.parseInt(hex.substring(1),16);
                    arg.setArgument(""+decimal);
                } catch (NumberFormatException ex) {
                    // Ignore - it wasn't a hex num
                }
            }
        }
    }
}
