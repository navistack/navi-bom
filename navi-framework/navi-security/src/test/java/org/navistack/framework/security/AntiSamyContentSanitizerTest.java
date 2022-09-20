package org.navistack.framework.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AntiSamyContentSanitizerTest {

    @Test
    void testSanitizeContentWithEventHandlerAttributes() throws Exception {
        AntiSamyContentSanitizer sanitizer = new AntiSamyContentSanitizer();
        String content = "<p onabort=\"javascript:alert('onabort')\"onautocomplete=\"javascript:alert('onautocomplete')\"onautocompleteerror=\"javascript:alert('onautocompleteerror')\"onblur=\"javascript:alert('onblur')\"oncancel=\"javascript:alert('oncancel')\"oncanplay=\"javascript:alert('oncanplay')\"oncanplaythrough=\"javascript:alert('oncanplaythrough')\"onchange=\"javascript:alert('onchange')\"onclick=\"javascript:alert('onclick')\"onclose=\"javascript:alert('onclose')\"oncontextmenu=\"javascript:alert('oncontextmenu')\"oncuechange=\"javascript:alert('oncuechange')\"ondblclick=\"javascript:alert('ondblclick')\"ondrag=\"javascript:alert('ondrag')\"ondragend=\"javascript:alert('ondragend')\"ondragenter=\"javascript:alert('ondragenter')\"ondragleave=\"javascript:alert('ondragleave')\"ondragover=\"javascript:alert('ondragover')\"ondragstart=\"javascript:alert('ondragstart')\"ondrop=\"javascript:alert('ondrop')\"ondurationchange=\"javascript:alert('ondurationchange')\"onemptied=\"javascript:alert('onemptied')\"onended=\"javascript:alert('onended')\"onerror=\"javascript:alert('onerror')\"onfocus=\"javascript:alert('onfocus')\"oninput=\"javascript:alert('oninput')\"oninvalid=\"javascript:alert('oninvalid')\"onkeydown=\"javascript:alert('onkeydown')\"onkeypress=\"javascript:alert('onkeypress')\"onkeyup=\"javascript:alert('onkeyup')\"onload=\"javascript:alert('onload')\"onloadeddata=\"javascript:alert('onloadeddata')\"onloadedmetadata=\"javascript:alert('onloadedmetadata')\"onloadstart=\"javascript:alert('onloadstart')\"onmousedown=\"javascript:alert('onmousedown')\"onmouseenter=\"javascript:alert('onmouseenter')\"onmouseleave=\"javascript:alert('onmouseleave')\"onmousemove=\"javascript:alert('onmousemove')\"onmouseout=\"javascript:alert('onmouseout')\"onmouseover=\"javascript:alert('onmouseover')\"onmouseup=\"javascript:alert('onmouseup')\"onmousewheel=\"javascript:alert('onmousewheel')\"onpause=\"javascript:alert('onpause')\"onplay=\"javascript:alert('onplay')\"onplaying=\"javascript:alert('onplaying')\"onprogress=\"javascript:alert('onprogress')\"onratechange=\"javascript:alert('onratechange')\"onreset=\"javascript:alert('onreset')\"onresize=\"javascript:alert('onresize')\"onscroll=\"javascript:alert('onscroll')\"onseeked=\"javascript:alert('onseeked')\"onseeking=\"javascript:alert('onseeking')\"onselect=\"javascript:alert('onselect')\"onshow=\"javascript:alert('onshow')\"onsort=\"javascript:alert('onsort')\"onstalled=\"javascript:alert('onstalled')\"onsubmit=\"javascript:alert('onsubmit')\"onsuspend=\"javascript:alert('onsuspend')\"ontimeupdate=\"javascript:alert('ontimeupdate')\"ontoggle=\"javascript:alert('ontoggle')\"onvolumechange=\"javascript:alert('onvolumechange')\"onwaiting=\"javascript:alert('onwaiting')\">Hello there</p>";
        String expected = "<p>Hello there</p>";
        assertThat(sanitizer.sanitize(content)).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void testSanitizeContentWithATag() throws Exception {
        AntiSamyContentSanitizer sanitizer = new AntiSamyContentSanitizer();
        String content = "<p>For further information, please visit <a href=\"https://example.com\">our website</a>.</p>";
        assertThat(sanitizer.sanitize(content)).isEqualToIgnoringWhitespace(content);
    }

    @Test
    void testSanitizeContentWithATagContainingJavaScriptHref() throws Exception {
        AntiSamyContentSanitizer sanitizer = new AntiSamyContentSanitizer();
        String content = "<p>For further information, please visit <a href=\"javascript:alert('href')\">our website</a>.</p>";
        String expected = "<p>For further information, please visit our website.</p>";
        assertThat(sanitizer.sanitize(content)).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void testSanitizeContentWithATagContainingVbscriptHref() throws Exception {
        AntiSamyContentSanitizer sanitizer = new AntiSamyContentSanitizer();
        String content = "<p>For further information, please visit <a href=\"vbscript:MsgBox(\"href\")\">our website</a>.</p>";
        String expected = "<p>For further information, please visit our website.</p>";
        assertThat(sanitizer.sanitize(content)).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void testSanitizeContentWithScriptTag() throws Exception {
        AntiSamyContentSanitizer sanitizer = new AntiSamyContentSanitizer();
        String content = "<p>Hello there</p><script type=\"text/javascript\">alert('script')</script>";
        String expected = "<p>Hello there</p>";
        assertThat(sanitizer.sanitize(content)).isEqualToIgnoringWhitespace(expected);
    }
}
