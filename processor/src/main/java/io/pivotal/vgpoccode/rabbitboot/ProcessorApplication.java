package io.pivotal.vgpoccode.rabbitboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ImportResource("processor-context.xml")
public class ProcessorApplication {
    private static final Logger LOGGER = Logger.getLogger(ProcessorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

    @Component
    public class AppInitializer implements CommandLineRunner {
        private final Logger LOGGER = Logger.getLogger(AppInitializer.class);

        private TestDataRepository testDataRepository;

        @Autowired
        public AppInitializer(TestDataRepository testDataRepository) {
            this.testDataRepository = testDataRepository;
        }

        public void run(String... args) {
            LOGGER.info("Initialize test data");
            try{
                //mock up some test data
                for(int i = 1; i <= 100; i++) {
                    testDataRepository.save(new TestData(Integer.valueOf(i).toString(), "Test data " + i));
                }
            } catch (Exception e) {
                LOGGER.error("Can't initialize test data", e);
            }
            LOGGER.info("Initialize test data complete");
        }
    }

    public static class DBLoader {
        private TestDataRepository testDataRepository;

        @Autowired
        public DBLoader(TestDataRepository testDataRepository) {
            this.testDataRepository = testDataRepository;
        }

        public TestData loadData(String input) {
            LOGGER.info("DBLoader.loadData " + input);
            TestData result = testDataRepository.findOne(input);
            if(result == null) {
                return new TestData("NOT FOUND", "NOT FOUND");
            } else {
                return result;
            }
        }
    }
}
