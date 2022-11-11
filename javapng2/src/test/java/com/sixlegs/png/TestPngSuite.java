package com.sixlegs.png;

import org.junit.jupiter.api.Test;


class TestPngSuite extends PngTestCase {

    @Test
    void testRead() throws Exception {
        long t = System.currentTimeMillis();
        new SuiteViewer().render(false);
        t = System.currentTimeMillis() - t;
        System.err.println("Read PngSuite in " + t + " ms");
    }

    @Test
    void testNoReduce() throws Exception {
        PngConfig config = new PngConfig.Builder().gammaCorrect(false).reduce16(false).build();
        new SuiteViewer(config).render(false);
    }

    @Test
    public void testConvertIndexed() throws Exception {
        PngConfig config = new PngConfig.Builder().convertIndexed(true).build();
        new SuiteViewer(config).render(false);
    }
}
