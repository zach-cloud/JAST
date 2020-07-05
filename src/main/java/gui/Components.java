package gui;

import gui.components.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

final class Components {

    ComponentContext context;
    KeywordsComponent keywordsComponent;
    SyntaxHighlighterComponent syntaxHighlighterComponent;
    AutocompleteComponent autocompleteComponent;
    ConfigLoaderComponent configLoaderComponent;
    ThemesComponent themesComponent;
    FileComponent fileComponent;
    StatusComponent statusComponent;
    RawcodeComponent rawcodeComponent;
    MpqComponent mpqComponent;
    RefactorComponent refactorComponent;
    UnhexComponent unhexComponent;
    SyntaxCheckerComponent syntaxCheckerComponent;
    StringHashComponent stringHashComponent;
    CloseComponent closeComponent;
    SearchComponent searchComponent;
    HotkeyComponent hotkeyComponent;
    AboutComponent aboutComponent;
    IsolateComponent isolateComponent;

    public Components(CodeArea jassCodeEditor, CodeArea functionBrowser, VBox root, Stage stage, Label statusLabel) {
        this.context = new ComponentContext(jassCodeEditor, functionBrowser, root, stage, statusLabel);
        this.keywordsComponent = new KeywordsComponent(context);
        this.syntaxHighlighterComponent = new SyntaxHighlighterComponent(context);
        this.autocompleteComponent = new AutocompleteComponent(context);
        this.configLoaderComponent = new ConfigLoaderComponent(context);
        this.themesComponent = new ThemesComponent(context, configLoaderComponent);
        this.statusComponent = new StatusComponent(context);
        this.mpqComponent = new MpqComponent(context, statusComponent, fileComponent);
        this.rawcodeComponent = new RawcodeComponent(context, statusComponent, fileComponent);
        this.fileComponent = new FileComponent(context, statusComponent, configLoaderComponent, rawcodeComponent, mpqComponent);
        this.refactorComponent = new RefactorComponent(context, statusComponent, fileComponent);
        this.unhexComponent = new UnhexComponent(context, statusComponent, refactorComponent);
        this.syntaxCheckerComponent = new SyntaxCheckerComponent(context);
        this.stringHashComponent = new StringHashComponent(context);
        this.closeComponent = new CloseComponent(context, statusComponent);
        this.searchComponent = new SearchComponent(context);
        this.hotkeyComponent = new HotkeyComponent(context);
        this.aboutComponent = new AboutComponent(context);
        this.isolateComponent = new IsolateComponent(context, statusComponent);

        this.keywordsComponent.setupKeywords();
        configLoaderComponent.readConfigs(fileComponent);
    }
}
