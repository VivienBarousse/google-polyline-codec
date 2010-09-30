/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aperigeek.google.polyline;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author viv
 */
public class PolylineEncoderTest extends TestCase {

    public void testEncode() {
        double number = -179.9832104;
        String expected = "`~oia@";
        String actual = new PolylineEncoder().encode(number);
        assertEquals(expected, actual);
    }

    public void testEncode2() {
        double number = 38.5;
        String expected = "_p~iF";
        String actual = new PolylineEncoder().encode(number);
        assertEquals(expected, actual);
    }

    public void testEncode3() {
        double number = -120.2;
        String expected = "~ps|U";
        String actual = new PolylineEncoder().encode(number);
        assertEquals(expected, actual);
    }

    public void testEncodePath() {
        List<Point> points = Arrays.asList(
                new Point(38.5, -120.2),
                new Point(40.7, -120.95),
                new Point(43.252, -126.453)
                );
        String expected = "_p~iF~ps|U_ulLnnqC_mqNvxq`@";
        String actual = new PolylineEncoder().encodePath(points);
        assertEquals(expected, actual);
    }

}
