package io.pivotal.vgpoccode.rabbitboot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bboe on 11/14/16.
 */
@Entity
@Table(name = "testdata")
@XmlRootElement(name="testdata")
public class TestData {
    @Id
    private String id;

    private String value;

    public TestData() {
    }

    public TestData(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name="value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
