/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aperigeek.google.polyline;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Vivien Barousse
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
