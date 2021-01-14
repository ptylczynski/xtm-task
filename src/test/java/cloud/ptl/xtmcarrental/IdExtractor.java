package cloud.ptl.xtmcarrental;

import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class IdExtractor {

    public static Long extract(MvcResult mvcResult) throws UnsupportedEncodingException {
        String content = mvcResult.getResponse().getContentAsString();
        int start = content.indexOf("id\":") + 4;
        int pos = start + 1;
        while(Character.isDigit(content.charAt(pos))) pos++;
        return Long.valueOf(
                content.substring(start, pos)
        );
    }

}
