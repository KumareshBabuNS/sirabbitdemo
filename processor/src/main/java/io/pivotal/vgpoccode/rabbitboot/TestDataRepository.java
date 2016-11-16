package io.pivotal.vgpoccode.rabbitboot;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by bboe on 11/14/16.
 */
public interface TestDataRepository extends CrudRepository<TestData, String> {
}
