[![Release](https://jitpack.io/v/umjammer/javapng-spi.svg?style=flat-square)](https://jitpack.io/#umjammer/javapng-spi)
[![Java CI](https://github.com/umjammer/javapng-spi/actions/workflows/maven.yml/badge.svg)](https://github.com/umjammer/javapng-spi/actions/workflows/maven.yml)
[![CodeQL](https://github.com/umjammer/javapng-spi/actions/workflows/codeql.yml/badge.svg)](https://github.com/umjammer/javapng-spi/actions/workflows/codeql-analysis.yml)
![Java](https://img.shields.io/badge/Java-8-b07219)
[![Parent](https://img.shields.io/badge/Parent-vavi--image-pink)](https://github.com/umjammer/vavi-image)

# javapng-spi

mavenized and spi-nised javapng

---

# Original

Copyright (C) 1998-2008 Chris Nokleberg
All rights reserved.

README - Sixlegs Java PNG Decoder

## Features

Supports all valid bit depths (grayscale/color), interlacing, paletted
images, alpha channel/transparency, gamma correction, access to all
standard chunks, private chunk handling, and progressive display.

## Getting Started

After including the javapng jar file in your classpath, typical usage
is as follows:

### create a new instance of PngImage
`com.sixlegs.png.PngImage png = new com.sixlegs.png.PngImage();`

### decode a PNG file into a BufferedImage using the read method

```java
java.awt.image.BufferedImage image =
png.read(new java.io.File("test.png"));
```

### the PngImage object will now provide access to the metadata

`int colorType = png.getColorType();`

## Revision History

See http://code.google.com/p/javapng/wiki/ChangeLog

## Contact Information

The latest version of this package is available from:
http://code.google.com/p/javapng/

Please let me know about any problems you encounter, or features
that you would find valuable.

Chris Nokleberg <chris@sixlegs.com>

