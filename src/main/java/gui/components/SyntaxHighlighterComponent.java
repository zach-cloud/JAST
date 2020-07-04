package gui.components;

import gui.VisibleParagraphStyler;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SyntaxHighlighterComponent extends GenericComponent {

    private Pattern pattern;

    public SyntaxHighlighterComponent(ComponentContext context) {
        super(context);
    }

    /**
     * Sets up syntax highlighting
     */
    public void setupHighlighting() {
        setupKeywords();
        context.jassCodeEditor.getVisibleParagraphs().addModificationObserver
                (
                        new VisibleParagraphStyler<>(context.jassCodeEditor, this::computeHighlighting)
                );

        final Pattern whiteSpace = Pattern.compile("^\\s+");
        context.jassCodeEditor.addEventHandler(KeyEvent.KEY_PRESSED, KE ->
        {
            if (KE.getCode() == KeyCode.ENTER) {
                int caretPosition = context.jassCodeEditor.getCaretPosition();
                int currentParagraph = context.jassCodeEditor.getCurrentParagraph();
                Matcher m0 = whiteSpace.matcher(context.jassCodeEditor.getParagraph(currentParagraph - 1).getSegments().get(0));
                if (m0.find()) Platform.runLater(() -> context.jassCodeEditor.insertText(caretPosition, m0.group()));
            }
        });
    }

    /**
     * Sets up keywords to use in syntax highlighting
     */
    private void setupKeywords() {
        String[] array = context.natives.toArray(new String[0]);
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", context.keywords) + ")\\b";
        String TYPE_PATTERN = "\\b(" + String.join("|", context.types) + ")\\b";
        String NATIVE_PATTERN = "\\b(" + String.join("|", array) + ")\\b";
        String PAREN_PATTERN = "\\(|\\)";
        String BRACKET_PATTERN = "\\[|\\]";
        String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
        String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

        pattern = Pattern.compile(
                "(?<NATIVE>" + NATIVE_PATTERN + ")"
                        + "|(?<KEYWORD>" + KEYWORDS_PATTERN + ")"
                        + "|(?<TYPE>" + TYPE_PATTERN + ")"
                        + "|(?<PAREN>" + PAREN_PATTERN + ")"
                        + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                        + "|(?<STRING>" + STRING_PATTERN + ")"
                        + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
        );
    }

    /**
     * Computes syntax highlighting for text
     *
     * @param text Text to highlight
     * @return Highlighted spans
     */
    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = pattern.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass =
                    matcher.group("NATIVE") != null ? "native" :
                            matcher.group("KEYWORD") != null ? "keyword" :
                                    matcher.group("TYPE") != null ? "type" :
                                            matcher.group("PAREN") != null ? "paren" :
                                                    matcher.group("BRACKET") != null ? "bracket" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}
