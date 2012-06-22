package org.jbehave.util;

public interface Transform<R,T> {
    T transform(R elem);
}
