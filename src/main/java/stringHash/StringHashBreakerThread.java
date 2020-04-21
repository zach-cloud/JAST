package stringHash;

public class StringHashBreakerThread extends Thread {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-";
    private long attempts = 0;
    private boolean broken = false;

    private String hash;
    private String plaintext;

    /**
     * Makes a new Stringhash Breaker
     *
     * @param hash  Hash to be broken
     */
    public StringHashBreakerThread(String hash) {
        super();
        this.hash = hash;
        this.plaintext = "(unknown)";
    }

    /**
     * Generates combinations until it finds the plaintext
     *
     * @param set   Charset
     * @param k     Word length
     */
    void tryCombinations(char[] set, int k) {
        if(!broken) {
            int n = set.length;
            tryCombinationsRecursive(set, "", n, k);
        }
    }

    /**
     * Recursive helper for trying Stringhash combinations
     *
     * @param set       Charset
     * @param prefix    Partially generated word
     * @param n         Current letter to try
     * @param k         Word length
     */
    void tryCombinationsRecursive(char[] set, String prefix, int n, int k) {
        if (k == 0) {
            String hash = SStrHash2.hash(prefix);
            attempts++;
            if(hash.equals(this.hash)) {
                this.plaintext = prefix;
                this.broken = true;
            }
            return;
        }
        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            if(!broken) {
                tryCombinationsRecursive(set, newPrefix, n, k - 1);
            }
        }
    }

    /**
     * Loops until the hash is broken, attempting combinations.
     */
    private void breakHash() {
        while(!broken) {
            for(int i = 1; i < 12; i++) {
                tryCombinations(ALPHABET.toCharArray(), i);
            }
        }
    }

    /**
     * Runs the thread until hash is broken
     */
    @Override
    public void run() {
        breakHash();
    }

    /**
     * Gets the current attempt count of the thread.
     *
     * @return  Attempt count
     */
    public long getAttempts() {
        return attempts;
    }

    public void setAttempts(long attempts) {
        this.attempts = attempts;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
}

