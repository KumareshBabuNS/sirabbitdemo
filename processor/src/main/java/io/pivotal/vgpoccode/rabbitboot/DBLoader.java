package io.pivotal.vgpoccode.rabbitboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bboe on 11/30/16.
 */
public class DBLoader {
    private static final Logger LOGGER = Logger.getLogger(DBLoader.class);

    private TestDataRepository testDataRepository;

    @Autowired
    public DBLoader(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }

    public TestData loadData(String input) {
        LOGGER.info("DBLoader.loadData " + input);
        TestData result = testDataRepository.findOne(input);
        if (result == null) {
            return new TestData("NOT FOUND", "NOT FOUND");
        } else {
            return result;
        }
    }
}
