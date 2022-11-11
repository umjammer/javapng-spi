/*
 * Copyright (c) 2022 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vavi.imageio.IIOUtil;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Test1.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2022-11-11 nsano initial version <br>
 */
class Test1 {

    static {
        IIOUtil.setOrder(ImageReaderSpi.class,
                "com.sixlegs.png.iio.PngImageReaderSpi",
                "com.sun.imageio.plugins.png.PNGImageReaderSpi");
    }

    String file = "src/test/resources/images/misc/manhat.png";

    @Test
    @DisplayName("spi specified")
    void test01() throws Exception {
        ImageReader ir = ImageIO.getImageReadersByFormatName("png").next();
        assertInstanceOf(com.sixlegs.png.iio.PngImageReader.class, ir);
        ImageInputStream iis = ImageIO.createImageInputStream(Files.newInputStream(Paths.get(file)));
        ir.setInput(iis);
        BufferedImage image = ir.read(0);
        assertNotNull(image);
    }

    @Test
    @DisplayName("spi auto")
    void test02() throws Exception {
        BufferedImage image = ImageIO.read(Files.newInputStream(Paths.get(file)));
        assertNotNull(image);
    }
}