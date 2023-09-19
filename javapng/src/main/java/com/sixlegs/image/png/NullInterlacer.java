// Copyright (C) 1998-2004 Chris Nokleberg
// Please see included LICENSE.TXT

package com.sixlegs.image.png;

final class NullInterlacer
extends Interlacer
{
    NullInterlacer(int w, int h)
    {
        super(w, h);
    }

    int numPasses()
    {
        return 1;
    }
    
    int getSpacingX(int pass)
    {
        return 1;
    }
    
    int getSpacingY(int pass)
    {
        return 1;
    }
    
    int getOffsetX(int pass)
    {
        return 0;
    }

    int getOffsetY(int pass)
    {
        return 0;
    }
}
