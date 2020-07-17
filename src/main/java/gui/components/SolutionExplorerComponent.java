package gui.components;

import helper.FileHelper;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SolutionExplorerComponent extends GenericComponent {

    private List<TreeItem<String>> solutions = new ArrayList<>();
    private String openSolution = "";

    public SolutionExplorerComponent(ComponentContext context) {
        super(context);
    }

    public void addSolution(String solutionName) {
        boolean alreadyExists = false;
        TreeItem<String> newSolution = null;
        for(TreeItem<String> items : solutions) {
            if(items.getValue().equalsIgnoreCase(solutionName)) {
                alreadyExists = true;
                newSolution = items;
                break;
            }
        }

        if(!alreadyExists) {
            newSolution = new TreeItem<>(solutionName);
            context.solutionExplorerRoot.getChildren().add(newSolution);
            solutions.add(newSolution);
        } else {
            newSolution.getChildren().clear();
        }

        File[] files = new File("projects/" + solutionName + "/").listFiles();
        if(files != null) {
            for (File file : files) {
                recusiveFileAdd(newSolution, file);
            }
        }
    }

    private void recusiveFileAdd(TreeItem<String> rootSolutionNode, File directoryPath) {
        if(!directoryPath.isDirectory()) {
            TreeItem<String> newItem = new TreeItem<>(directoryPath.getName());
            rootSolutionNode.getChildren().add(newItem);
        } else {
            File[] files = directoryPath.listFiles();
            TreeItem<String> newRoot = new TreeItem<>(directoryPath.getName());
            rootSolutionNode.getChildren().add(newRoot);
            if(files != null) {
                for (File file : files) {
                    recusiveFileAdd(newRoot, file);
                }
            }
        }
    }

    public File getSolutionPath(TreeItem<String> value) {
        List<String> pathParts = new ArrayList<>();
        while(value.getParent() != null) {
            pathParts.add(value.getValue());
            if(solutions.contains(value)) {
                this.openSolution = value.getValue();
                break;
            }
            value = value.getParent();
        }
        StringBuilder fullPath = new StringBuilder().append("projects/");
        for(int i = pathParts.size() - 1; i >= 0; i--) {
            fullPath.append(pathParts.get(i)).append("/");
        }
        return new File(fullPath.toString());
    }

    public void findProject() {
        File root = new File("projects/");
        File[] files = root.listFiles();
        if(files != null) {
            for (File file : files) {
                if(file.isDirectory() && file.exists()) {
                    addSolution(file.getName());
                }
            }
        }
    }

    public void closeProject() {
        if(openSolution != null && !openSolution.isEmpty()) {
            File toDelete = new File("projects/" + openSolution);
            if(toDelete.exists() && toDelete.isDirectory()) {
                FileHelper.deleteDirectory(toDelete);
            }
            Iterator<TreeItem<String>> solutionIterator = solutions.iterator();
            while(solutionIterator.hasNext()) {
                TreeItem<String> next = solutionIterator.next();
                if(next != null && next.getValue().equalsIgnoreCase(openSolution)) {
                    this.context.solutionExplorerRoot.getChildren().removeAll(next);
                    solutionIterator.remove();
                }
            }
            this.context.jassCodeEditor.clear();
        }
    }

    public String getCurrentProject() {
        return openSolution;
    }
}
