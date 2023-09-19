// Copyright (C) 1998-2004 Chris Nokleberg
// Please see included LICENSE.TXT

package com.sixlegs.image.png;
import java.io.IOException;

final class Chunk_gIFg
extends Chunk
{
    Chunk_gIFg()
    {
        super(gIFg);
    }

    protected void readData()
    throws IOException
    {
        img.data.properties.put("gif disposal method", in_data.readUnsignedByte());
        img.data.properties.put("gif user input flag", in_data.readUnsignedByte());
        img.data.properties.put("gif delay time", (int) in_data.readShort());
    }    
}
