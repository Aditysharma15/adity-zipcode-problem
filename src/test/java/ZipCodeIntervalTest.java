import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ZipCodeIntervalTest {
    ZipCodeInterval zipCodeInterval;

    @Before
    public void setUp() throws Exception {
        zipCodeInterval = new ZipCodeInterval();

    }

    @Test
    public void testRangeForListOfSize1() throws Exception {
        ArrayList<Range> listWithOneElement = new ArrayList<>();
        listWithOneElement.add(new Range(19876, 19880));
        assertEquals(zipCodeInterval.ranges(listWithOneElement), listWithOneElement);
    }

    @Test
    public void testRangeForListSize0() throws Exception {
        ArrayList<Range> emptyList = new ArrayList<>();
        assertEquals(zipCodeInterval.ranges(emptyList), emptyList);
    }


    @Test
    public void testWhenNoMergingRequired() {
        List<Range> inputList = new ArrayList<>();
        inputList.add(new Range(94133, 94133));
        inputList.add(new Range(94200, 94299));
        inputList.add(new Range(94600, 94699));

        List<Range> mergedList = zipCodeInterval.ranges(inputList);
        assertThat(mergedList,
                containsInAnyOrder(
                        new Range(94133, 94133),
                        new Range(94200, 94299),
                        new Range(94600, 94699)
                ));
    }

    @Test
    public void testWhenMergingRequired() throws Exception {

        List<Range> inputList = new ArrayList<>();
        inputList.add(new Range(94133, 94133));
        inputList.add(new Range(94200, 94299));
        inputList.add(new Range(94226, 94399));

        List<Range> mergedList = zipCodeInterval.ranges(inputList);
        //assertThat(mergedList, containsInAnyOrder(result));
        assertThat(mergedList,
                containsInAnyOrder(
                        new Range(94133, 94133),
                        new Range(94200, 94399)

                ));
    }

    @Test
    public void testZipCodeDigitLengthGreaterThanFive() {

        List<Range> inputList = new ArrayList<>();
        inputList.add(new Range(94133, 941333));
        inputList.add(new Range(94200, 94299));
        inputList.add(new Range(942264, 94399));
        assertEquals(zipCodeInterval.ranges(inputList), null);

    }

    @Test
    public void testZipCodeDigitLengthLessThanFive() {

        List<Range> inputList = new ArrayList<>();
        inputList.add(new Range(94133, 941));
        inputList.add(new Range(94, 94299));
        inputList.add(new Range(942264, 94399));
        assertEquals(zipCodeInterval.ranges(inputList), null);
    }

    @Test
    public void testZipCodeListIsNull() {

        List<Range> inputList = null;
        assertEquals(zipCodeInterval.ranges(inputList), null);

    }

    @Test
    public void testZipCodeStartGreaterThanEnd() {

        List<Range> inputList = new ArrayList<>();
        inputList.add(new Range(94133, 94130));
        inputList.add(new Range(94122, 94299));
        inputList.add(new Range(942264, 94399));
        assertEquals(zipCodeInterval.ranges(inputList), null);
    }

}