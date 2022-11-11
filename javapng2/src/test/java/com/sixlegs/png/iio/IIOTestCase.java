package com.sixlegs.png.iio;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


abstract class IIOTestCase {

    static Stream<Arguments> transform() {
        Scanner s = new Scanner(IIOTestCase.class.getResourceAsStream("/images.txt"));
        List<Arguments> arguments = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.startsWith("#")) continue;
            String image = line.split(" ")[0];
            if (!line.startsWith("/images/suite/")) continue;
            arguments.add(Arguments.of(image));
        }
        s.close();
        return arguments.stream();
    }

    protected ImageReader sunIR = null;

    @ParameterizedTest
    @MethodSource("transform")
    void test(String name) throws Exception {
        // Initialise sun's plugin
        sunIR = new com.sun.imageio.plugins.png.PNGImageReader(
                new com.sun.imageio.plugins.png.PNGImageReaderSpi());
        sunIR.setInput(ImageIO.createImageInputStream(getClass().getResourceAsStream(name)));

        InputStream in = getClass().getResourceAsStream(name);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        PngImageReaderSpi spi = new PngImageReaderSpi();
        assertTrue(spi.canDecodeInput(iis));
        PngImageReader ir = new PngImageReader(spi);
        ir.setInput(iis);
        BufferedImage bi = ir.read(0);
        assertNotNull(bi);
        test(name, ir, bi);
    }

    abstract void test(String name, PngImageReader ir, BufferedImage bi) throws Exception;
}
