package com.sixlegs.png;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.zip.Checksum;


public class PngTestCase {

    public static long getChecksum(Checksum checksum, File file, byte[] buf) throws IOException {
        checksum.reset();
        try (InputStream in = Files.newInputStream(file.toPath())) {
            while (true) {
                int amt = in.read(buf);
                if (amt < 0) break;
                checksum.update(buf, 0, amt);
            }
        }
        return checksum.getValue();
    }

    public static void skipFully(DataInput in, int n) throws IOException {
        while (n > 0) {
            int amt = in.skipBytes(n);
            if (amt == 0) {
                in.readByte();
                n--;
            } else {
                n -= amt;
            }
        }
    }
}
