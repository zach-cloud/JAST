package services;

import exception.ParsingException;
import interfaces.IBlizzardLoaderService;
import interfaces.ISyntaxTree;
import tree.SyntaxTree;

import java.io.File;

public class BlizzardLoaderService implements IBlizzardLoaderService {

    private File loadBlizzardPath(String which) {
        which = which.replace(".j", "");
        File firstTry = new File("blizzard/" + which);
        if(!firstTry.exists()) {
            File other = new File("blizzard/" + which + ".j");
            if(!other.exists()) {
                throw new ParsingException("Could not load file: " + which);
            } else {
                return other;
            }
        } else {
            return firstTry;
        }
    }

    /**
     * Loads common.j as a Syntax Treee
     *
     * @return Common.j
     */
    @Override
    public ISyntaxTree loadCommon() {
        return SyntaxTree.readTree(loadBlizzardPath("common"));
    }

    /**
     * Loads blizzard.j as a Syntax Tree
     *
     * @return Blizzard.j
     */
    @Override
    public ISyntaxTree loadBlizzard() {
        return SyntaxTree.readTree(loadBlizzardPath("blizzard"));
    }
}
