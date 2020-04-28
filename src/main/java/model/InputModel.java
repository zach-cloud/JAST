package model;

import interfaces.ISyntaxTree;

public final class InputModel {

    private ISyntaxTree tree1 = null;
    private ISyntaxTree tree2 = null;
    private String activator = null;
    private String outputPath = null;
    private String oldName = null;
    private String newName = null;
    private String hash = null;
    private String plaintext = null;
    private String rawcodeInput = null;
    private String rawcodeOutput = null;
    private String wtsFile = null;
    private String inputMpq = null;
    private String mpqDirectory = null;

    public ISyntaxTree getTree1() {
        return tree1;
    }

    public void setTree1(ISyntaxTree tree1) {
        this.tree1 = tree1;
    }

    public ISyntaxTree getTree2() {
        return tree2;
    }

    public void setTree2(ISyntaxTree tree2) {
        this.tree2 = tree2;
    }

    public String getActivator() {
        return activator;
    }

    public void setActivator(String activator) {
        this.activator = activator;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
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

    public String getRawcodeInput() {
        return rawcodeInput;
    }

    public void setRawcodeInput(String rawcodeInput) {
        this.rawcodeInput = rawcodeInput;
    }

    public String getRawcodeOutput() {
        return rawcodeOutput;
    }

    public void setRawcodeOutput(String rawcodeOutput) {
        this.rawcodeOutput = rawcodeOutput;
    }

    public String getWtsFile() {
        return wtsFile;
    }

    public void setWtsFile(String wtsFile) {
        this.wtsFile = wtsFile;
    }

    public String getInputMpq() {
        return inputMpq;
    }

    public void setInputMpq(String inputMpq) {
        this.inputMpq = inputMpq;
    }

    public String getMpqDirectory() {
        return mpqDirectory;
    }

    public void setMpqDirectory(String mpqDirectory) {
        this.mpqDirectory = mpqDirectory;
    }
}
