package minieditor;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditorEngineImplTest {

    private EditorEngine editorEngine;

    @Before
    public void setUp() throws Exception {
        editorEngine = new EditorEngineImpl();
    }

    @Test
    public void testCut() throws Exception {

    }

    @Test
    public void testCopy() throws Exception {

    }

    @Test
    public void testPaste() throws Exception {

    }

    @Test
    public void testInsertText() throws Exception {
        final String toInsert = "Test data sample";
        editorEngine.insertText(toInsert);
        assertTrue(editorEngine.getContent().equals(toInsert));
    }

    @Test
    public void testGetContent() throws Exception {
        assertTrue(editorEngine.getContent().equals(""));
    }

    @Test
    public void testChangeSelection() throws Exception {

    }
}