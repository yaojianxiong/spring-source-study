/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Arjen Poutsma
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
public class MethodParameterTests {

	private Method method;

	private MethodParameter stringParameter;

	private MethodParameter longParameter;

	private MethodParameter intReturnType;


	@Before
	public void setup() throws NoSuchMethodException {
		method = getClass().getMethod("method", String.class, Long.TYPE);
		stringParameter = new MethodParameter(method, 0);
		longParameter = new MethodParameter(method, 1);
		intReturnType = new MethodParameter(method, -1);
	}


	@Test
	public void testEquals() throws NoSuchMethodException {
		assertEquals(stringParameter, stringParameter);
		assertEquals(longParameter, longParameter);
		assertEquals(intReturnType, intReturnType);

		assertFalse(stringParameter.equals(longParameter));
		assertFalse(stringParameter.equals(intReturnType));
		assertFalse(longParameter.equals(stringParameter));
		assertFalse(longParameter.equals(intReturnType));
		assertFalse(intReturnType.equals(stringParameter));
		assertFalse(intReturnType.equals(longParameter));

		Method method = getClass().getMethod("method", String.class, Long.TYPE);
		MethodParameter methodParameter = new MethodParameter(method, 0);
		assertEquals(stringParameter, methodParameter);
		assertEquals(methodParameter, stringParameter);
		assertNotEquals(longParameter, methodParameter);
		assertNotEquals(methodParameter, longParameter);
	}

	@Test
	public void testHashCode() throws NoSuchMethodException {
		assertEquals(stringParameter.hashCode(), stringParameter.hashCode());
		assertEquals(longParameter.hashCode(), longParameter.hashCode());
		assertEquals(intReturnType.hashCode(), intReturnType.hashCode());

		Method method = getClass().getMethod("method", String.class, Long.TYPE);
		MethodParameter methodParameter = new MethodParameter(method, 0);
		assertEquals(stringParameter.hashCode(), methodParameter.hashCode());
		assertNotEquals(longParameter.hashCode(), methodParameter.hashCode());
	}

	@Test
	public void annotatedConstructorParameterInStaticNestedClass() throws Exception {
		Constructor<?> constructor = NestedClass.class.getDeclaredConstructor(String.class);
		MethodParameter methodParameter = MethodParameter.forMethodOrConstructor(constructor, 0);
		assertEquals(String.class, methodParameter.getParameterType());
		assertNotNull("Failed to find @Param annotation", methodParameter.getParameterAnnotation(Param.class));
	}

	@Test  // SPR-16652
	public void annotatedConstructorParameterInInnerClass() throws Exception {
		Constructor<?> constructor = InnerClass.class.getConstructor(getClass(), String.class, Integer.class);

		MethodParameter methodParameter = MethodParameter.forMethodOrConstructor(constructor, 0);
		assertEquals(getClass(), methodParameter.getParameterType());
		assertNull(methodParameter.getParameterAnnotation(Param.class));

		methodParameter = MethodParameter.forMethodOrConstructor(constructor, 1);
		assertEquals(String.class, methodParameter.getParameterType());
		assertNotNull("Failed to find @Param annotation", methodParameter.getParameterAnnotation(Param.class));

		methodParameter = MethodParameter.forMethodOrConstructor(constructor, 2);
		assertEquals(Integer.class, methodParameter.getParameterType());
		assertNull(methodParameter.getParameterAnnotation(Param.class));
	}


	public int method(String p1, long p2) {
		return 42;
	}

	@SuppressWarnings("unused")
	private static class NestedClass {

		NestedClass(@Param String s) {
		}
	}

	@SuppressWarnings("unused")
	private class InnerClass {

		public InnerClass(@Param String s, Integer i) {
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.PARAMETER)
	private @interface Param {
	}

}