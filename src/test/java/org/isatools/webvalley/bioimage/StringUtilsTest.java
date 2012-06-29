package org.isatools.webvalley.bioimage;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 29/06/2012
 *         Time: 17:26
 */
public class StringUtilsTest {

    @Test
    public void testStringProcessing() {
        String testString = "AreaShape_MajorAxisLength";

        String processedString = StringUtils.processString(testString);

        assertTrue("Oh no, the string wasn't processed correctly.",
                processedString.equals("Area Shape Major Axis Length"));

    }
}
