package minieditor;

import minieditor.commands.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EditorEngineImplTest {

    private EditorEngine editorEngine;
    private UserInterface userInterfaceMock;

    @Before
    public void setUp() {
        editorEngine = new EditorEngineImpl();
        userInterfaceMock = mock(UserInterface.class);
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
    public void testValidChangeSelection() throws Exception {
        final String toInsert       = "Test data sample";
        final int newSelectionStart = 3;
        final int newSelectionEnd   = 5;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        assertEquals(newSelectionStart, editorEngine.getSelectionStart());
        assertEquals(newSelectionEnd, editorEngine.getSelectionEnd());
    }

    @Test
    public void testInvalidChangeSelectionNegativeStartOutOfBondsEnd() throws Exception {
        final String toInsert            = "Test data sample";
        final int newSelectionStart      = -8;
        final int newSelectionEnd        = 25;
        final int expectedSelectionStart = 0;
        final int expectedSelectionEnd   = toInsert.length();
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        assertEquals(expectedSelectionStart, editorEngine.getSelectionStart());
        assertEquals(expectedSelectionEnd, editorEngine.getSelectionEnd());
    }

    @Test
    public void testInvalidChangeSelectionStartGreaterThanEnd() throws Exception {
        final String toInsert            = "Test data sample";
        final int newSelectionStart      = 5;
        final int newSelectionEnd        = 3;
        final int expectedSelectionStart = newSelectionEnd;
        final int expectedSelectionEnd   = newSelectionStart;
        editorEngine.insertText(toInsert);
        editorEngine.changeSelection(newSelectionStart, newSelectionEnd);
        assertEquals(expectedSelectionStart, editorEngine.getSelectionStart());
        assertEquals(expectedSelectionEnd, editorEngine.getSelectionEnd());
    }


    @Test
    public void testSampleMacro() throws Exception {
        final String toInsert = "Test data sample";
        when(userInterfaceMock.promptTextToInsert()).thenReturn(toInsert);
        new StartMacro(editorEngine).execute();
        new InsertText(editorEngine, userInterfaceMock).execute();
        new StopMacro(editorEngine).execute();
        assertEquals(toInsert, editorEngine.getContent());
        new ReplayMacro(editorEngine).execute();
        verify(userInterfaceMock, times(1)).promptTextToInsert(); // We check `promptTextToInsert` has been called one time only
        assertEquals(toInsert + toInsert, editorEngine.getContent());
    }

    @Test
    public void testUndoRedo() throws Exception {
        final String toInsert = "Test data sample";
        when(userInterfaceMock.promptTextToInsert()).thenReturn(toInsert);
        new InsertText(editorEngine, userInterfaceMock).execute();
        assertEquals(toInsert, editorEngine.getContent());
        new Undo(editorEngine).execute();
        assertEquals("", editorEngine.getContent());
        new Redo(editorEngine).execute();
        verify(userInterfaceMock, times(1)).promptTextToInsert(); // We check `promptTextToInsert` has been called one time only
        assertEquals(toInsert, editorEngine.getContent());
    }
}