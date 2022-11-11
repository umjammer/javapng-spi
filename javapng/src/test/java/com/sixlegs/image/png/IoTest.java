package com.sixlegs.image.png;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IoTest {

    @Test
    void testConcatenatedImages() throws Exception {
        InputStream in = getClass().getResourceAsStream("/images/misc/concat.dat");
        PngImage p1 = new PngImage(in, false);
        p1.getEverything();
        PngImage p2 = new PngImage(in, false);
        p2.getEverything();
        assertEquals(32, p1.getWidth());
        assertEquals(32, p2.getWidth());
    }
}
