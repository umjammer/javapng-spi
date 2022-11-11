package com.sixlegs.png.iio;

import org.w3c.dom.Node;


// Tests the native metadata format javax_imageio_png_1.0
// by comparing it to Sun's implementation
class TestNativeMetadata extends MetadataTestCase {

    @Override
    protected String getFormatName() {
        return "javax_imageio_png_1.0";
    }

    @Override
    protected Node getSunsTree() throws Exception {
        return sunIR.getImageMetadata(0).getAsTree("javax_imageio_png_1.0");
    }
}
