/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aperigeek.google.polyline;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

/**
 * Tests the Google Maps Polylines decoder.
 *
 * Samples used in these tests are issues from the official Google Maps
 * polyline algorithm page:
 * http://code.google.com/apis/maps/documentation/utilities/polylinealgorithm.html
 *
 * @author Vivien Barousse
 */
public class PolylineDecoderTest extends TestCase {

    public void testDecodePoint() {
        char[] chars = "`~oia@".toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (chars[i] - 63);
        }

        double value = new PolylineDecoder().decodePoint(bytes, 0, bytes.length);
        assertEquals(-179.98321, value);
    }

    public void testDecodePoint2() {
        char[] chars = "_p~iF".toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (chars[i] - 63);
        }

        double value = new PolylineDecoder().decodePoint(bytes, 0, bytes.length);
        assertEquals(38.5, value);
    }

    public void testDecodePoint3() {
        char[] chars = "~ps|U".toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (chars[i] - 63);
        }

        double value = new PolylineDecoder().decodePoint(bytes, 0, bytes.length);
        assertEquals(-120.2, value);
    }

    public void testDecodePointNotStart() {
        char[] chars = "  `~oia@".toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (chars[i] - 63);
        }

        double value = new PolylineDecoder().decodePoint(bytes, 2, 6);
        assertEquals(-179.98321, value);
    }

    public void testDecodePointNotEnd() {
        char[] chars = "`~oia@  ".toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (chars[i] - 63);
        }

        double value = new PolylineDecoder().decodePoint(bytes, 0, 6);
        assertEquals(-179.98321, value);
    }

    public void testDecodePointMiddle() {
        char[] chars = "  `~oia@  ".toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (chars[i] - 63);
        }

        double value = new PolylineDecoder().decodePoint(bytes, 2, 6);
        assertEquals(-179.98321, value);
    }

    public void testDecode() {
        String polyline = "_p~iF~ps|U_ulLnnqC_mqNvxq`@";
        List<Double> numbers = new PolylineDecoder().decode(polyline);
        assertEquals(Arrays.asList(38.5, -120.2, 2.2, -0.75, 2.552, -5.503),
                numbers);
    }

    public void testDecodePath() {
        String polyline = "_p~iF~ps|U_ulLnnqC_mqNvxq`@";
        List<Point> points = new PolylineDecoder().decodePath(polyline);
        assertEquals(Arrays.asList(
                new Point(38.5, -120.2),
                new Point(40.7, -120.95),
                new Point(43.252, -126.453)),
                points);
    }

}
