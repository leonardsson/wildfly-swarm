/**
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildfly.swarm.spi.api;

import java.util.concurrent.Callable;

/**
 * Utility to execute code within a context using a particular {@code ClassLoader} as the
 * thread-context-classloader.
 */
public interface ClassLoading {

    static <T> T withTCCL(ClassLoader loader, Callable<T> action) {
        ClassLoader oldTCCL = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(loader);
            return action.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Thread.currentThread().setContextClassLoader(oldTCCL);
        }
    }
}
