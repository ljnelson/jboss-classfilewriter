/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2012 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.classfilewriter.test.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.jboss.classfilewriter.ClassFile;
import org.jboss.classfilewriter.ClassMethod;

/**
 * Utility class for testing annotation bytecode
 *
 * @author Stuart Douglas
 *
 */
public class AnnotationTester {

    private static int count = 0;

    private static final String NAME = "com.test.AnnotationTest";

    public static Field testFieldAnnotations(Class<?> clazz, String name) {
        try {

            ClassFile file = new ClassFile(NAME + count++, Object.class.getName(), AnnotationTester.class.getClassLoader());
            Field field = clazz.getDeclaredField(name);
            file.addField(field);
            Class<?> newClass = file.define();
            return newClass.getDeclaredField(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Method testMethodAnnotations(Class<?> clazz, String name) {
        try {
            ClassFile file = new ClassFile(NAME + count++, Object.class.getName(), AnnotationTester.class.getClassLoader());
            Method method = clazz.getDeclaredMethod(name, String.class);
            ClassMethod cmeth = file.addMethod(method);
            cmeth.getCodeAttribute().returnInstruction();
            Class<?> newClass = file.define();
            return newClass.getDeclaredMethod(name, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
