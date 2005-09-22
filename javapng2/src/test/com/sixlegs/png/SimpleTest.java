package com.sixlegs.png;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import junit.framework.*;

public class SimpleTest
extends PngTestCase
{
    public void testRecolorMonochrome()
    throws Exception
    {
        PngImage png = new PngImage(){
            protected BufferedImage createImage(InputStream in) throws IOException {
                if (getBitDepth() == 1 &&
                    getColorType() == PngConstants.COLOR_TYPE_GRAY) {
                    Map props = getProperties();
                    props.put(PngConstants.COLOR_TYPE,
                              new Integer(PngConstants.COLOR_TYPE_PALETTE));
                    props.put(PngConstants.PALETTE, new byte[]{
                        (byte)255, 0, 0,
                        (byte)255, (byte)255, 0
                    });
                }
                return super.createImage(in);
            }
        };
        InputStream in = getClass().getResourceAsStream("/images/suite/basn0g01.png");
        BufferedImage img = png.read(in, true);
        javax.imageio.ImageIO.write(img, "PNG", File.createTempFile("recolor", ".png"));
    }


    public void testPrivateChunk()
    throws Exception
    {
        final String ORIGINAL_GIF = "original_gif";
        final int msOG_type = PngChunk.getType("msOG");

        PngImage png = readResource("/images/misc/anigif.png", new PngImage(){
            protected PngChunk getChunk(int type) {
                if (type == msOG_type) {
                    return new PngChunk(){
                        public void read(int type, PngInputStream in, PngImage png) throws IOException {
                            byte[] bytes = new byte[in.getRemaining()];
                            in.readFully(bytes);
                            png.getProperties().put(ORIGINAL_GIF, bytes);
                        }
                    };
                }
                return super.getChunk(type);
            }                
        });
        byte[] bytes = (byte[])png.getProperty(ORIGINAL_GIF);

        assertEquals("MSOFFICE9.0", new String(bytes, 0, 11, "US-ASCII"));
        
        File file = File.createTempFile("msog", ".gif");
        OutputStream out = new FileOutputStream(file);
        out.write(bytes, 11, bytes.length - 11);
        out.close();

        BufferedImage image = javax.imageio.ImageIO.read(file);
        assertEquals(32, image.getWidth());
        assertEquals(32, image.getHeight());        
    }

    public void testRead()
    throws Exception
    {
        PngImage png = readResource("/images/misc/cc1.png");
        assertEquals(138, png.getWidth());
        assertEquals(180, png.getHeight());
    }

    public void testProgressBar()
    throws Exception
    {
        final List progress = new ArrayList();
        readResource("/images/misc/cc1.png", new PngImage(){
            protected boolean handleProgress(BufferedImage image, float pct) {
                progress.add(new Float(pct));
                return true;
            }
        });
        assertEquals(Arrays.asList(new Float[]{
            new Float(5.5555553f),
            new Float(10.555555f),
            new Float(15.555555f),
            new Float(20.555555f),
            new Float(25.555555f),
            new Float(30.555555f),
            new Float(35.555557f),
            new Float(40.555557f),
            new Float(45.555557f),
            new Float(50.555557f),
            new Float(55.555557f),
            new Float(60.555557f),
            new Float(65.55556f),
            new Float(70.55556f),
            new Float(75.55556f),
            new Float(80.55556f),
            new Float(85.55556f),
            new Float(90.55556f),
            new Float(95.55556f), 
        }), progress);
    }

    public void testAbort()
    throws Exception
    {
        readResource("/images/suite/basi3p04.png", new PngImage(){
            protected boolean handleFrame(BufferedImage image, int framesLeft) {
                if (framesLeft != 6)
                    throw new IllegalStateException("Should have aborted after first frame");
                return false;
            }
        });
    }

    public void testReadAncillary()
    throws Exception
    {
        PngImage png = readResource("/images/misc/anigif.png");
        assertEquals(32, png.getWidth());
        assertEquals(32, png.getHeight());
    }
    
    public void testErrors()
    throws Exception
    {
        errorHelper("/images/suite/x00n0g01.png");
        errorHelper("/images/suite/xcrn0g04.png");
        errorHelper("/images/suite/xlfn0g04.png");
    }

    public void errorHelper(String path)
    throws Exception
    {
        try {
            readResource(path);
            fail("Expected exception");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private PngImage readResource(String path)
    throws IOException
    {
        return readResource(path, new PngImage());
    }

    private PngImage readResource(String path, PngImage png)
    throws IOException
    {
        InputStream in = getClass().getResourceAsStream(path);
        png.read(in, true);
        return png;
    }

    public SimpleTest(String name)
    {
        super(name);
    }

    public static Test suite()
    {
        return getSuite(SimpleTest.class);
    }
}
