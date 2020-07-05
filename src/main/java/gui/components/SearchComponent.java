package gui.components;

import gui.Controller;
import gui.window.SearchWindow;
import org.fxmisc.richtext.CodeArea;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SearchComponent extends GenericComponent {

    private SearchWindow searchWindow;

    public SearchComponent(ComponentContext context) {
        super(context);
    }

    public void search(Controller controller) {
        if (searchWindow == null) {
            searchWindow = new SearchWindow(controller);
        }
        searchWindow.show();
    }

    public void searchExecute() {
        if (searchWindow != null) {
            regexFind(context.jassCodeEditor, searchWindow.getSearchText(), context.jassCodeEditor.getCaretPosition());
        }
    }

    private void regexFind(CodeArea area, String regex, int offset) {
        Matcher matcher = Pattern.compile(Pattern.quote(regex)).matcher(area.getText().substring(offset));
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            area.selectRange(start + offset, end + offset);
            int current = context.jassCodeEditor.getCurrentParagraph();
            context.jassCodeEditor.showParagraphAtTop(current);
        } else {
            JOptionPane.showMessageDialog(null, "No more matches found");
        }
    }

    public void closeSearch() {
        if (searchWindow != null) {
            searchWindow.hide();
        }
    }
}
