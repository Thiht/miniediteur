package minieditor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditorEngineImplTest {

    private EditorEngine editorEngine;

    @Before
    public void setUp() {
        editorEngine = new EditorEngineImpl();
    }

    @Test
    public void testEmptyCut() throws Exception {
        final String clipboard = editorEngine.getClipboard();
        editorEngine.cut();
        assertEquals(clipboard, editorEngine.getClipboard());
    }

    @Test
    public void testValidSelectionCut() throws Exception {
        final String toInsert          = "Test data sample";
        final String expectedClipboard = "data";
        final String expectedContent   = "Test  sample";
        final int newSelectionStart    = 5;
        final int newSelectionEnd      = 9;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        editorEngine.cut();
        assertEquals(expectedClipboard, editorEngine.getClipboard());
        assertEquals(expectedContent, editorEngine.getContent());
    }

    @Test
    public void testEmptyCopy() throws Exception {
        final String clipboard = editorEngine.getClipboard();
        editorEngine.copy();
        assertEquals(clipboard, editorEngine.getClipboard());
    }

    @Test
    public void testValidSelectionCopy() throws Exception {
        final String toInsert          = "Test data sample";
        final String expectedClipboard = "data";
        final String expectedContent   = toInsert;
        final int newSelectionStart    = 5;
        final int newSelectionEnd      = 9;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        editorEngine.copy();
        assertEquals(expectedClipboard, editorEngine.getClipboard());
        assertEquals(expectedContent, editorEngine.getContent());
    }

    @Test
    public void testEmptyPaste() throws Exception {
        final String content = editorEngine.getContent();
        editorEngine.paste();
        assertEquals(content, editorEngine.getContent());
    }

    @Test
    public void testEmptyPasteWithValidSelection() throws Exception {
        final String toInsert        = "Test data sample";
        final String expectedContent = "Test  sample";
        final int newSelectionStart  = 5;
        final int newSelectionEnd    = 9;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        editorEngine.paste();
        assertEquals(expectedContent, editorEngine.getContent());
    }

    @Test
    public void testCopyPasteWithValidSelections() throws Exception {
        final String toInsert        = "Test data sample";
        final String expectedContent = "data data sample";
        final int newSelectionStart  = 5;
        final int newSelectionEnd    = 9;
        final int newSelectionStart2 = 0;
        final int newSelectionEnd2   = 4;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        editorEngine.copy();
        editorEngine.changeSelection(newSelectionStart2, newSelectionEnd2);
        editorEngine.paste();
        assertEquals(expectedContent, editorEngine.getContent());
    }

    @Test
    public void testInsertText() throws Exception {
        final String toInsert = "Test data sample";
        editorEngine.insertText(toInsert);
        assertEquals(toInsert, editorEngine.getContent());
    }

    @Test
    public void testInsertTextWithValidSelection() throws Exception {
        final String toInsert        = "Test data sample";
        final String toInsert2       = "my new data";
        final String expectedContent = "Test my new data sample";
        final int newSelectionStart  = 5;
        final int newSelectionEnd    = 9;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        editorEngine.insertText(toInsert2);
        assertEquals(expectedContent, editorEngine.getContent());
    }

    @Test
    public void testGetContent() throws Exception {
        assertTrue(editorEngine.getContent().equals(""));
    }

    @Test
    public void testChangeSelection() throws Exception {
        final String toInsert       = "Test data sample";
        final int newSelectionStart = 3;
        final int newSelectionEnd   = 5;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, 5);
        assertEquals(newSelectionStart, editorEngine.getSelectionStart());
        assertEquals(newSelectionEnd, editorEngine.getSelectionEnd());
    }
}