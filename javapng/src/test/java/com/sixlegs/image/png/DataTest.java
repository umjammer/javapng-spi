package com.sixlegs.image.png;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


class DataTest {

    @Test
    void testImages() throws Exception {
        int[] buffer = new int[800 * 600]; // big enough to handle the biggest image
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/images.txt")));
        String line;
        int fail = 0;
        int l = 0;
        while ((line = r.readLine()) != null) {
            int space = line.indexOf(' ');
            String image = line.substring(0, space).trim();
            String result = line.substring(space + 1).trim();
            try {
                InputStream in = getClass().getResourceAsStream(image);
                if (in == null)
                    fail("Cannot find image \"" + image + "\"");
                PngImage png = new PngImage(in);
                png.setBuffer(buffer);
                png.getEverything(true);
                byte[] pixbuf = new byte[4];
                for (int i = 0, size = png.getWidth() * png.getHeight(); i < size; i++) {
                    int pixel = buffer[i];
                    pixbuf[3] = (byte)(0xFF & pixel);
                    pixbuf[2] = (byte)(0xFF & (pixel >>> 8));
                    pixbuf[1] = (byte)(0xFF & (pixel >>> 16));
                    pixbuf[0] = (byte)(0xFF & (pixel >>> 24));
                    md5.update(pixbuf);
                }
                String hash = toHexString(md5.digest());
                if (!result.equals(hash)) {
                    System.err.println("Expected digest 0x" + result + " for image " + image + ", got 0x" + hash);
                    fail++;
                }
            } catch (Exception e) {
                if (!result.equals(e.getMessage())) {
                    System.err.println("Caught exception while processing image " + image + ":");
                    e.printStackTrace(System.err);
                    fail++;
                }
            }
            l++;
        }
        if (fail != 0)
            fail("Failures detected: " + fail + "/" + l);
    }

    private static String toHexString(byte[] b) {
       StringBuilder hex = new StringBuilder(2 * b.length);
        for (byte n : b) {
            if (n >= 0 && n <= 15)
                hex.append("0");
            hex.append(Integer.toHexString(0xFF & n));
        }
       return hex.toString().toUpperCase();
    }
}
