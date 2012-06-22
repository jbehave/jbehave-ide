package org.jbehave.util;


public interface BidirectionalStream {
    
    public int read();

    public void unread();
}