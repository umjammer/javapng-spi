/*
com.sixlegs.image.png - Java package to read and display PNG images
Copyright (C) 1998-2004 Chris Nokleberg

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Library General Public
License as published by the Free Software Foundation; either
version 2 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Library General Public License for more details.

You should have received a copy of the GNU Library General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*/

package com.sixlegs.image.png;

import java.util.Enumeration;
import java.util.NoSuchElementException;


public class EmptyEnumeration
        implements Enumeration<Object> {

    private static final EmptyEnumeration INSTANCE = new EmptyEnumeration();

    public static EmptyEnumeration getInstance() {
        return INSTANCE;
    }

    public boolean hasMoreElements() {
        return false;
    }

    public Object nextElement() {
        throw new NoSuchElementException();
    }
}
