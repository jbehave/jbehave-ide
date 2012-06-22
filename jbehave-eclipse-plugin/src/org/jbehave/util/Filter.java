package org.jbehave.util;

public interface Filter<T> {
    boolean isAccepted(T value);
}
