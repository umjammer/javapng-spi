// Copyright (C) 1998-2004 Chris Nokleberg
// Please see included LICENSE.TXT

package com.sixlegs.image.png;

/**
 * A class implementing the <code>ChunkHandler</code> interface
 * can be registered using the {@link PngImage#registerChunk(ChunkHandler, String)}
 * method. 
 */
public interface ChunkHandler
{
    /**
     * Process chunk data.
     * This method is called upon encountering a chunk 
     * registered as being handled by the implementing class.
     * @see PngImage#registerChunk(ChunkHandler, String)
     * @param type chunk type
     * @param data raw chunk data
     */
    void handleChunk(String type, byte[] data);
}
