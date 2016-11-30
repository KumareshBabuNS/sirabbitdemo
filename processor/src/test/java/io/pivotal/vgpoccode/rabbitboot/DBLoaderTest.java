package io.pivotal.vgpoccode.rabbitboot;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


/**
 * Created by bboe on 11/30/16.
 */
public class DBLoaderTest {
    private DBLoader dbLoader;
    private TestDataRepository testDataRepository;

    @Before
    public void setup() {
        testDataRepository = mock(TestDataRepository.class);
        dbLoader = new DBLoader(testDataRepository);
    }

    @Test
    public void testLoadData() {
        String validKey = "123";
        TestData expectedResult = new TestData(validKey, "valid result");
        String invalidKey = "-1";
        when(testDataRepository.findOne(validKey)).thenReturn(expectedResult);
        when(testDataRepository.findOne(invalidKey)).thenReturn(null);

        assertEquals(expectedResult, dbLoader.loadData(validKey));
        assertNotNull(dbLoader.loadData(invalidKey));
    }
}
