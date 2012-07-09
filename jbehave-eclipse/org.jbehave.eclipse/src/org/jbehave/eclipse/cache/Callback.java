package org.jbehave.eclipse.cache;

public interface Callback<T1, T2> {
    void op(T1 value1, T2 value2);
}
