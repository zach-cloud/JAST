package interfaces;

/**
 * Used to generate random and unique variable/function names
 */
public interface IRandomNameGeneratorService {

    /**
     * Produces the next unique random name
     *
     * @return  Unique random name
     */
    String next();
}
