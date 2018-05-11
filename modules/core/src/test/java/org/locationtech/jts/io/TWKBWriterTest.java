package org.locationtech.jts.io;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import org.locationtech.jts.geom.CoordinateSequenceComparator;
import org.locationtech.jts.geom.CoordinateSequenceFactory;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.impl.PackedCoordinateSequenceFactory;

import java.util.Arrays;

public class TWKBWriterTest extends TestCase {
    public static void main(String args[]) {
        TestRunner.run(TWKBWriterTest.class);
    }

    public void testPoints() throws ParseException {
        check("POINT(1 2)", 0, 0, 0, false, false, "01000204");

        check("POINT(1 2)", 0, 0, 0, true, false, "0102020204");
        check("POINT(1 2)", 0, 0, 0, false, true, "0101020004000204");

        check("POINT(1 2)", 0, 0, 0, true, true, "010306020004000204");


        check("POINT(1 2)", 0, 1, 0, true, true, "010306020004000204");
        check("POINT(1 2)", 0, 1, 0, true, false, "0102020204");
        check("POINT(1 2)", 0, 1, 0, false, true, "0101020004000204");
        check("POINT(1 2)", 0, 1, 0, false, false, "01000204");
        check("POINT(1 2)", 0, 5, 0, true, true, "010306020004000204");
        check("POINT(1 2)", 0, 5, 0, true, false, "0102020204");
        check("POINT(1 2)", 0, 5, 0, false, true, "0101020004000204");
        check("POINT(1 2)", 0, 5, 0, false, false, "01000204");

        check("POINT(1 2)", -1, 0, 0, true, true, "110306000000000000");
        check("POINT(1 2)", -1, 0, 0, true, false, "1102020000");
        check("POINT(1 2)", -1, 0, 0, false, true, "1101000000000000");
        check("POINT(1 2)", -1, 0, 0, false, false, "11000000");
        check("POINT(1 2)", -1, 1, 0, true, true, "110306000000000000");
        check("POINT(1 2)", -1, 1, 0, true, false, "1102020000");
        check("POINT(1 2)", -1, 1, 0, false, true, "1101000000000000");
        check("POINT(1 2)", -1, 1, 0, false, false, "11000000");
        check("POINT(1 2)", -1, 5, 0, true, true, "110306000000000000");
        check("POINT(1 2)", -1, 5, 0, true, false, "1102020000");
        check("POINT(1 2)", -1, 5, 0, false, true, "1101000000000000");
        check("POINT(1 2)", -1, 5, 0, false, false, "11000000");

        check("POINT(1 2)", 1, 0, 0, true, true, "210306140028001428");
        check("POINT(1 2)", 1, 0, 0, true, false, "2102021428");
        check("POINT(1 2)", 1, 0, 0, false, true, "2101140028001428");
        check("POINT(1 2)", 1, 0, 0, false, false, "21001428");
        check("POINT(1 2)", 1, 1, 0, true, true, "210306140028001428");
        check("POINT(1 2)", 1, 1, 0, true, false, "2102021428");
        check("POINT(1 2)", 1, 1, 0, false, true, "2101140028001428");
        check("POINT(1 2)", 1, 1, 0, false, false, "21001428");
        check("POINT(1 2)", 1, 5, 0, true, true, "210306140028001428");
        check("POINT(1 2)", 1, 5, 0, true, false, "2102021428");
        check("POINT(1 2)", 1, 5, 0, false, true, "2101140028001428");
        check("POINT(1 2)", 1, 5, 0, false, false, "21001428");
        check("POINT(1 2)", 5, 0, 0, true, true, "a1030ec09a0c0080b51800c09a0c80b518");
        check("POINT(1 2)", 5, 0, 0, true, false, "a10206c09a0c80b518");
        check("POINT(1 2)", 5, 0, 0, false, true, "a101c09a0c0080b51800c09a0c80b518");
        check("POINT(1 2)", 5, 0, 0, false, false, "a100c09a0c80b518");
        check("POINT(1 2)", 5, 1, 0, true, true, "a1030ec09a0c0080b51800c09a0c80b518");
        check("POINT(1 2)", 5, 1, 0, true, false, "a10206c09a0c80b518");
        check("POINT(1 2)", 5, 1, 0, false, true, "a101c09a0c0080b51800c09a0c80b518");
        check("POINT(1 2)", 5, 1, 0, false, false, "a100c09a0c80b518");
        check("POINT(1 2)", 5, 5, 0, true, true, "a1030ec09a0c0080b51800c09a0c80b518");
        check("POINT(1 2)", 5, 5, 0, true, false, "a10206c09a0c80b518");
        check("POINT(1 2)", 5, 5, 0, false, true, "a101c09a0c0080b51800c09a0c80b518");
        check("POINT(1 2)", 5, 5, 0, false, false, "a100c09a0c80b518");
        check("POINT(1234.567891234 98765.54321)", -1, 0, 0, true, true, "11030cf60100aa9a0100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 0, 0, true, false, "110205f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 0, 0, false, true, "1101f60100aa9a0100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 0, 0, false, false, "1100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 1, 0, true, true, "11030cf60100aa9a0100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 1, 0, true, false, "110205f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 1, 0, false, true, "1101f60100aa9a0100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 1, 0, false, false, "1100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 5, 0, true, true, "11030cf60100aa9a0100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 5, 0, true, false, "110205f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 5, 0, false, true, "1101f60100aa9a0100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", -1, 5, 0, false, false, "1100f601aa9a01");
        check("POINT(1234.567891234 98765.54321)", 0, 0, 0, true, true, "01030ca613009c870c00a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 0, 0, true, false, "010205a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 0, 0, false, true, "0101a613009c870c00a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 0, 0, false, false, "0100a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 1, 0, true, true, "01030ca613009c870c00a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 1, 0, true, false, "010205a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 1, 0, false, true, "0101a613009c870c00a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 1, 0, false, false, "0100a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 5, 0, true, true, "01030ca613009c870c00a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 5, 0, true, false, "010205a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 5, 0, false, true, "0101a613009c870c00a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 0, 5, 0, false, false, "0100a6139c870c");
        check("POINT(1234.567891234 98765.54321)", 1, 0, 0, true, true, "21030ef4c001008ec87800f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 0, 0, true, false, "210206f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 0, 0, false, true, "2101f4c001008ec87800f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 0, 0, false, false, "2100f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 1, 0, true, true, "21030ef4c001008ec87800f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 1, 0, true, false, "210206f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 1, 0, false, true, "2101f4c001008ec87800f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 1, 0, false, false, "2100f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 5, 0, true, true, "21030ef4c001008ec87800f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 5, 0, true, false, "210206f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 5, 0, false, true, "2101f4c001008ec87800f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 1, 5, 0, false, false, "2100f4c0018ec878");
        check("POINT(1234.567891234 98765.54321)", 5, 0, 0, true, true, "a10314aab4de7500a28982cb4900aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 0, 0, true, false, "a10209aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 0, 0, false, true, "a101aab4de7500a28982cb4900aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 0, 0, false, false, "a100aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 1, 0, true, true, "a10314aab4de7500a28982cb4900aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 1, 0, true, false, "a10209aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 1, 0, false, true, "a101aab4de7500a28982cb4900aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 1, 0, false, false, "a100aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 5, 0, true, true, "a10314aab4de7500a28982cb4900aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 5, 0, true, false, "a10209aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 5, 0, false, true, "a101aab4de7500a28982cb4900aab4de75a28982cb49");
        check("POINT(1234.567891234 98765.54321)", 5, 5, 0, false, false, "a100aab4de75a28982cb49");

    }

    public void testPointsZ() throws ParseException {

        check("POINT Z(1 2 3)", -1, 0, 0, true, true, "110b0109000000000600000006");
        check("POINT Z(1 2 3)", -1, 0, 0, true, false, "110a0103000006");
        check("POINT Z(1 2 3)", -1, 0, 0, false, true, "110901000000000600000006");
        check("POINT Z(1 2 3)", -1, 0, 0, false, false, "110801000006");
        check("POINT Z(1 2 3)", -1, 1, 0, true, true, "110b0509000000003c0000003c");
        check("POINT Z(1 2 3)", -1, 1, 0, true, false, "110a050300003c");
        check("POINT Z(1 2 3)", -1, 1, 0, false, true, "110905000000003c0000003c");
        check("POINT Z(1 2 3)", -1, 1, 0, false, false, "11080500003c");
        check("POINT Z(1 2 3)", -1, 5, 0, true, true, "110b150d00000000c0cf24000000c0cf24");
        check("POINT Z(1 2 3)", -1, 5, 0, true, false, "110a15050000c0cf24");
        check("POINT Z(1 2 3)", -1, 5, 0, false, true, "11091500000000c0cf24000000c0cf24");
        check("POINT Z(1 2 3)", -1, 5, 0, false, false, "1108150000c0cf24");
        check("POINT Z(1 2 3)", 0, 0, 0, true, true, "010b0109020004000600020406");
        check("POINT Z(1 2 3)", 0, 0, 0, true, false, "010a0103020406");
        check("POINT Z(1 2 3)", 0, 0, 0, false, true, "010901020004000600020406");
        check("POINT Z(1 2 3)", 0, 0, 0, false, false, "010801020406");
        check("POINT Z(1 2 3)", 0, 1, 0, true, true, "010b0509020004003c0002043c");
        check("POINT Z(1 2 3)", 0, 1, 0, true, false, "010a050302043c");
        check("POINT Z(1 2 3)", 0, 1, 0, false, true, "010905020004003c0002043c");
        check("POINT Z(1 2 3)", 0, 1, 0, false, false, "01080502043c");
        check("POINT Z(1 2 3)", 0, 5, 0, true, true, "010b150d02000400c0cf24000204c0cf24");
        check("POINT Z(1 2 3)", 0, 5, 0, true, false, "010a15050204c0cf24");
        check("POINT Z(1 2 3)", 0, 5, 0, false, true, "01091502000400c0cf24000204c0cf24");
        check("POINT Z(1 2 3)", 0, 5, 0, false, false, "0108150204c0cf24");
        check("POINT Z(1 2 3)", 1, 0, 0, true, true, "210b0109140028000600142806");
        check("POINT Z(1 2 3)", 1, 0, 0, true, false, "210a0103142806");
        check("POINT Z(1 2 3)", 1, 0, 0, false, true, "210901140028000600142806");
        check("POINT Z(1 2 3)", 1, 0, 0, false, false, "210801142806");
        check("POINT Z(1 2 3)", 1, 1, 0, true, true, "210b0509140028003c0014283c");
        check("POINT Z(1 2 3)", 1, 1, 0, true, false, "210a050314283c");
        check("POINT Z(1 2 3)", 1, 1, 0, false, true, "210905140028003c0014283c");
        check("POINT Z(1 2 3)", 1, 1, 0, false, false, "21080514283c");
        check("POINT Z(1 2 3)", 1, 5, 0, true, true, "210b150d14002800c0cf24001428c0cf24");
        check("POINT Z(1 2 3)", 1, 5, 0, true, false, "210a15051428c0cf24");
        check("POINT Z(1 2 3)", 1, 5, 0, false, true, "21091514002800c0cf24001428c0cf24");
        check("POINT Z(1 2 3)", 1, 5, 0, false, false, "2108151428c0cf24");
        check("POINT Z(1 2 3)", 5, 0, 0, true, true, "a10b0111c09a0c0080b518000600c09a0c80b51806");
        check("POINT Z(1 2 3)", 5, 0, 0, true, false, "a10a0107c09a0c80b51806");
        check("POINT Z(1 2 3)", 5, 0, 0, false, true, "a10901c09a0c0080b518000600c09a0c80b51806");
        check("POINT Z(1 2 3)", 5, 0, 0, false, false, "a10801c09a0c80b51806");
        check("POINT Z(1 2 3)", 5, 1, 0, true, true, "a10b0511c09a0c0080b518003c00c09a0c80b5183c");
        check("POINT Z(1 2 3)", 5, 1, 0, true, false, "a10a0507c09a0c80b5183c");
        check("POINT Z(1 2 3)", 5, 1, 0, false, true, "a10905c09a0c0080b518003c00c09a0c80b5183c");
        check("POINT Z(1 2 3)", 5, 1, 0, false, false, "a10805c09a0c80b5183c");
        check("POINT Z(1 2 3)", 5, 5, 0, true, true, "a10b1515c09a0c0080b51800c0cf2400c09a0c80b518c0cf24");
        check("POINT Z(1 2 3)", 5, 5, 0, true, false, "a10a1509c09a0c80b518c0cf24");
        check("POINT Z(1 2 3)", 5, 5, 0, false, true, "a10915c09a0c0080b51800c0cf2400c09a0c80b518c0cf24");
        check("POINT Z(1 2 3)", 5, 5, 0, false, false, "a10815c09a0c80b518c0cf24");
    }

        public void testPointsZandM() throws ParseException {

            check("POINT Z(1 2 3)", -1, 0, 0, false, false, "110801000006");
            check("POINT Z(1 2 3)", -1, 1, 0, false, false, "11080500003c");
            check("POINT Z(1 2 3)", -1, 5, 0, false, false, "1108150000c0cf24");
            check("POINT Z(1 2 3)", 0, 0, 0, false, false, "010801020406");
            check("POINT Z(1 2 3)", 0, 1, 0, false, false, "01080502043c");
            check("POINT Z(1 2 3)", 0, 5, 0, false, false, "0108150204c0cf24");
            check("POINT Z(1 2 3)", 1, 0, 0, false, false, "210801142806");
            check("POINT Z(1 2 3)", 1, 1, 0, false, false, "21080514283c");
            check("POINT Z(1 2 3)", 1, 5, 0, false, false, "2108151428c0cf24");
            check("POINT Z(1 2 3)", 5, 0, 0, false, false, "a10801c09a0c80b51806");
            check("POINT Z(1 2 3)", 5, 1, 0, false, false, "a10805c09a0c80b5183c");
            check("POINT Z(1 2 3)", 5, 5, 0, false, false, "a10815c09a0c80b518c0cf24");

            check("POINT M(1 2 3)", -1, 0, 0, false, false, "110802000006");
            check("POINT M(1 2 3)", -1, 1, 0, false, false, "110806000006");
            check("POINT M(1 2 3)", -1, 5, 0, false, false, "110816000006");
            check("POINT M(1 2 3)", 0, 0, 0, false, false, "010802020406");
            check("POINT M(1 2 3)", 0, 1, 0, false, false, "010806020406");
            check("POINT M(1 2 3)", 0, 5, 0, false, false, "010816020406");
            check("POINT M(1 2 3)", 1, 0, 0, false, false, "210802142806");
            check("POINT M(1 2 3)", 1, 1, 0, false, false, "210806142806");
            check("POINT M(1 2 3)", 1, 5, 0, false, false, "210816142806");
            check("POINT M(1 2 3)", 5, 0, 0, false, false, "a10802c09a0c80b51806");
            check("POINT M(1 2 3)", 5, 1, 0, false, false, "a10806c09a0c80b51806");
            check("POINT M(1 2 3)", 5, 5, 0, false, false, "a10816c09a0c80b51806");
        }

    private CoordinateSequenceFactory factory = new PackedCoordinateSequenceFactory(PackedCoordinateSequenceFactory.DOUBLE, 2);
    private GeometryFactory geometryFactory = new GeometryFactory(factory);
    private TWKBWriter writer = new TWKBWriter();
    private WKTReader reader = new WKTReader(geometryFactory);

    private void check(String wkt,
                       int xyprecision,
                       int zprecision,
                       int mprecision,
                       boolean includeSize,
                       boolean includeBbox,
                       String expectedTWKB) throws ParseException {

        Geometry geom = reader.read(wkt);
        byte[] written = writer.write(geom, xyprecision, zprecision, mprecision, includeSize, includeBbox);
        byte[] twkb = WKBReader.hexToBytes(expectedTWKB);

        boolean isEqualHex = Arrays.equals(twkb, written);

        if (!isEqualHex) {
            System.out.println("Expected    " + expectedTWKB);
            System.out.println("Output:     " + javax.xml.bind.DatatypeConverter.printHexBinary(written));
        }
        assertTrue(isEqualHex);
    }

//    private GeometryFactory geomFactory = new GeometryFactory();
//    private WKTReader rdr = new WKTReader(geomFactory);

//    GeometryFactory geomFactory = new GeometryFactory(
//            new PackedCoordinateSequenceFactory(PackedCoordinateSequenceFactory.DOUBLE, 2));
//    WKTReader rdr = new WKTReader(geomFactory);


    public void testPointGeometries() throws ParseException {
       // checkTWKBGeometry("01000204", 2, "POINT(1 2)");
        checkTWKBGeometry("01080302040608", 4,"POINT(1 2 3 4)");

        // Written with precision = 5
        checkTWKBGeometry("a100c09a0c80b518", 2,"POINT(1 2)");
        checkTWKBGeometry("a10080a8d6b90780d0acf30e", 2,"POINT(10000 20000)");

        // With bounding boxes
        checkTWKBGeometry("0101020004000204", 2,"POINT(1 2)");
        checkTWKBGeometry("010903020004000600080002040608", 4, "POINT(1 2 3 4)");
    }

    public void testTWKB() throws ParseException {
        checkTWKBGeometry("0110", "POINT EMPTY");
        checkTWKBGeometry("a100c09a0c80b518", "POINT(1 2)");
        checkTWKBGeometry("a210", "LINESTRING EMPTY");
        checkTWKBGeometry("a20002c09a0c80b51880b51880b518", "LINESTRING(1 2,3 4)");
        checkTWKBGeometry("a310", "POLYGON EMPTY");
        checkTWKBGeometry("a3000104c09a0c80b51880b51880b51880b51880b518ffe930ffe930", "POLYGON((1 2,3 4,5 6,1 2))");
        checkTWKBGeometry("a3000204c09a0c80b51880b51880b51880b51880b518ffe930ffe9300480897a80897a80b51880b51880b51880b518ffe930ffe930", "POLYGON((1 2,3 4,5 6,1 2),(11 12,13 14,15 16,11 12))");
        checkTWKBGeometry("a3000304c09a0c80b51880b51880b51880b51880b518ffe930ffe9300480897a80897a80b51880b51880b51880b518ffe930ffe9300480897a80897a80b51880b51880b51880b518ffe930ffe930", "POLYGON((1 2,3 4,5 6,1 2),(11 12,13 14,15 16,11 12),(21 22,23 24,25 26,21 22))");
        checkTWKBGeometry("a410", "MULTIPOINT EMPTY");
        checkTWKBGeometry("a40001c09a0c80b518", "MULTIPOINT(1 2)");
        checkTWKBGeometry("a40002c09a0c80b51880b51880b518", "MULTIPOINT(1 2,3 4)");
        checkTWKBGeometry("a510", "MULTILINESTRING EMPTY");
        checkTWKBGeometry("a5000102c09a0c80b51880b51880b518", "MULTILINESTRING((1 2,3 4))");
        checkTWKBGeometry("a5000202c09a0c80b51880b51880b5180280b51880b51880b51880b518", "MULTILINESTRING((1 2,3 4),(5 6,7 8))");
        checkTWKBGeometry("a610", "MULTIPOLYGON EMPTY");
        checkTWKBGeometry("a600010104c09a0c80b51880b51880b51880b51880b518ffe930ffe930", "MULTIPOLYGON(((1 2,3 4,5 6,1 2)))");
        checkTWKBGeometry("a600020104c09a0c80b51880b51880b51880b51880b518ffe930ffe9300304000080b51880b51880b51880b518ffe930ffe9300480897a80897a80b51880b51880b51880b518ffe930ffe9300480897a80897a80b51880b51880b51880b518ffe930ffe930", "MULTIPOLYGON(((1 2,3 4,5 6,1 2)),((1 2,3 4,5 6,1 2),(11 12,13 14,15 16,11 12),(21 22,23 24,25 26,21 22)))");
        checkTWKBGeometry("a710", "GEOMETRYCOLLECTION EMPTY");
        checkTWKBGeometry("a70001a100c09a0c80b518", "GEOMETRYCOLLECTION(POINT(1 2))");
        checkTWKBGeometry("a70002a100c09a0c80b518a20002c09a0c80b51880b51880b518", "GEOMETRYCOLLECTION(POINT(1 2),LINESTRING(1 2,3 4))");
        checkTWKBGeometry("a70003a100c09a0c80b518a20002c09a0c80b51880b51880b518a3000304c09a0c80b51880b51880b51880b51880b518ffe930ffe9300480897a80897a80b51880b51880b51880b518ffe930ffe9300480897a80897a80b51880b51880b51880b518ffe930ffe930", "GEOMETRYCOLLECTION(POINT(1 2),LINESTRING(1 2,3 4),POLYGON((1 2,3 4,5 6,1 2),(11 12,13 14,15 16,11 12),(21 22,23 24,25 26,21 22)))");
    }

    private static CoordinateSequenceComparator comp2 = new CoordinateSequenceComparator(2);
    private static CoordinateSequenceComparator comp3 = new CoordinateSequenceComparator(3);
    private static CoordinateSequenceComparator comp4 = new CoordinateSequenceComparator(4);

    private void checkTWKBGeometry(String twkbHex, String expectedWKT) throws ParseException
    {
        checkTWKBGeometry(twkbHex, 2, expectedWKT);
        checkTWKBGeometry(twkbHex, 3, expectedWKT);
    }

    private void checkTWKBGeometry(String twkbHex, int dimension, String expectedWKT) throws ParseException
    {
        CoordinateSequenceFactory csf =
                new PackedCoordinateSequenceFactory(PackedCoordinateSequenceFactory.DOUBLE ,dimension);
        GeometryFactory geomFactory = new GeometryFactory(csf);
        WKTReader rdr = new WKTReader(geomFactory);

        Geometry g = rdr.read(expectedWKT);

        TWKBWriter twkbWriter = new TWKBWriter();
        byte[] twkb = WKBReader.hexToBytes(twkbHex);
        byte[] written = twkbWriter.write(g);

        boolean isEqualHex = Arrays.equals(twkb, written);

        if (!isEqualHex) {
            System.out.println("Input:      " + twkbHex);
            System.out.println("Expected " + javax.xml.bind.DatatypeConverter.printHexBinary(written));
        }

        TWKBReader reader = new TWKBReader();
        Geometry g2 = reader.read(written);

        CoordinateSequenceComparator comp = null;
        switch (dimension) {
            case 2: comp = comp2; break;
            case 3: comp = comp3; break;
            case 4: comp = comp4; break;
            //default: throw new Exception("Never gonna get here!");
        }
        boolean isEqual = (g.compareTo(g2, comp) == 0);

        if (!isEqual || !isEqualHex) {
            System.out.println("isEqual: " + isEqual + " isEqualHex: " + isEqualHex);
            System.out.println("Input:      " + expectedWKT);
            System.out.println("Round-trip: " + g2);
            System.out.println("Expected " + twkbHex);
            System.out.println("Written  " + javax.xml.bind.DatatypeConverter.printHexBinary(written));
        }
        assertTrue(isEqualHex);
        assertTrue(isEqual);
    }

}