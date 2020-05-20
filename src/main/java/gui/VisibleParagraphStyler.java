package gui;

import javafx.application.Platform;
import org.fxmisc.richtext.GenericStyledArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.reactfx.collection.ListModification;

import java.util.function.Consumer;
import java.util.function.Function;

class VisibleParagraphStyler<PS, SEG, S> implements Consumer<ListModification> {
    private final GenericStyledArea<PS, SEG, S> area;
    private final Function<String, StyleSpans<S>> computeStyles;
    private int prevParagraph, prevTextLength;

    public VisibleParagraphStyler(GenericStyledArea<PS, SEG, S> area, Function<String, StyleSpans<S>> computeStyles) {
        this.computeStyles = computeStyles;
        this.area = area;
    }

    @Override
    public void accept(ListModification lm) {
        if (lm.getAddedSize() > 0) {
            int paragraph = area.visibleParToAllParIndex(lm.getFrom());
            String text = area.getText(paragraph, 0, paragraph, area.getParagraphLength(paragraph));

            if (paragraph != prevParagraph || text.length() != prevTextLength) {
                int startPos = area.getAbsolutePosition(paragraph, 0);
                Platform.runLater(() -> area.setStyleSpans(startPos, computeStyles.apply(text)));
                prevTextLength = text.length();
                prevParagraph = paragraph;
            }
        }
    }
}