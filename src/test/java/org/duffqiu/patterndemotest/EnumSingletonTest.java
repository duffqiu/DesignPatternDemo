/**
 * 
 */
package org.duffqiu.patterndemotest;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.SerializationUtils;
import org.duffqiu.patterndemo.EnumSingleton;
import org.junit.Test;

/**
 * @author macbook
 * 
 *         2014年2月5日
 */
public class EnumSingletonTest {

    @Test
    public final void testSerialibel() {
	EnumSingleton a = EnumSingleton.INSTANCE;

	byte[] bytes = SerializationUtils.serialize(a);
	EnumSingleton b = SerializationUtils.deserialize(bytes);

	assertThat(a).isSameAs(b);
    }

    @Test
    public final void testGetSet() {

	final int newAge = 30;
	EnumSingleton a = EnumSingleton.INSTANCE;
	EnumSingleton b = EnumSingleton.INSTANCE;

	a.setAge(newAge);
	b.setName("test name");

	assertThat(a.getName()).isEqualTo(b.getName());
	assertThat(a.getAge()).isEqualTo(b.getAge());
    }

}
