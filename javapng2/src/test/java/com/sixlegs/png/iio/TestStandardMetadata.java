package com.sixlegs.png.iio;

import javax.imageio.metadata.IIOMetadataFormatImpl;

import org.w3c.dom.Node;


class TestStandardMetadata extends MetadataTestCase {

    @Override
    protected String getFormatName() {
        return IIOMetadataFormatImpl.standardMetadataFormatName;
    }

    @Override
    protected Node getSunsTree() throws Exception {
        return sunIR.getImageMetadata(0).getAsTree("javax_imageio_1.0");
    }
}
