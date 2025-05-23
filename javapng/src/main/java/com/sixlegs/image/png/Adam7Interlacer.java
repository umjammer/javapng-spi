// Copyright (C) 1998-2004 Chris Nokleberg
// Please see included LICENSE.TXT

package com.sixlegs.image.png;

final class Adam7Interlacer
        extends Interlacer {

    static final int[] init = {0, 0, 4, 0, 2, 0, 1, 0};
    static final int[] sp = {8, 8, 8, 4, 4, 2, 2, 1};

    Adam7Interlacer(int w, int h) {
        super(w, h);
    }

    int numPasses() {
        return 7;
    }

    int getSpacingX(int pass) {
        return sp[pass + 1];
    }

    int getSpacingY(int pass) {
        return sp[pass];
    }

    int getOffsetX(int pass) {
        return init[pass + 1];
    }

    int getOffsetY(int pass) {
        return init[pass];
    }
}
