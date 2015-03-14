package de.donhilion.multiclipboard.clipboard;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClipboardTest {

    @Test
    public void testGetClipboardContent() throws Exception {
        String testString = "content to test with";
        Clipboard clipboard = new Clipboard();

        clipboard.setClipboardContent(testString);
        String result = clipboard.getClipboardContent();

        assertEquals("Content is not equal", testString, result);
    }

    private boolean called = false;
    private String calledString = null;
    @Test
    public void testListener() throws Exception {
        String testString1 = "content to test with";
        String testString2 = "another content";
        Clipboard clipboard = new Clipboard();

        ContentChangeListener listener = new ContentChangeListener() {
            @Override
            public void contentChanged(String newContent) {
                called = true;
                calledString = newContent;
            }
        };
        called = false;
        clipboard.setContentChangeListener(listener);
        clipboard.setClipboardContent(testString1);

        clipboard = new Clipboard();
        clipboard.setClipboardContent(testString2);

        Thread.sleep(1000); // wait for listener to be called

        assertTrue("Listener was not called", called);
        assertEquals("New content is wrong", testString2, calledString);
    }
}