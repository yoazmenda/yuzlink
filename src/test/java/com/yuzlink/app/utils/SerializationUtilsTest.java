package com.yuzlink.app.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializationUtilsTest {
    private SerializationUtils serializationUtils;
    private TestObj testObj;

    @Before
    public void setUp() throws Exception {
        serializationUtils = new SerializationUtils();
        testObj = new TestObj(1, 2);
    }

    @Test
    public void testSerialize() throws Exception {
        Assert.assertEquals("{\"x\":1,\"y\":2}", serializationUtils.serialize(testObj));
    }

    @Test
    public void testDeserialize() throws Exception {
        String json = "{\"x\":3,\"y\":4}";
        TestObj deserializedTestObj = serializationUtils.deserialize(json, TestObj.class);
        Assert.assertEquals(3, deserializedTestObj.getX());
        Assert.assertEquals(4, deserializedTestObj.getY());
    }
}