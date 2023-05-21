
/**
 * Title        : DatabaseTest<p>
 * Description  : Test application<p>
 * Copyright    : Copyright (c)
 * Company:     :
 * @author      : Hasan
 * @version     : 2.0
 * Date                 programmer                      comments
 * -----------------------------------------------------------------------------
 * 27-02-2006          Hasan                            Modified
 * -----------------------------------------------------------------------------
 */


package com.skills4testing.core.soap;

import java.io.*;

public class CBufferedInputStream extends BufferedInputStream {
	private static int defaultExpectedLineLength = 80;

	public CBufferedInputStream(InputStream in) {
		super(in);
	}

	public CBufferedInputStream(InputStream in, int size) {
		super(in, size);
	}

	/**
	 * Read a line of text. A line is considered to be terminated by any one of
	 * a line feed ('\n'), a carriage return ('\r'), or a carriage return
	 * followed immediately by a linefeed.
	 * 
	 * @return A String containing the contents of the line, not including any
	 *         line-termination characters, or null if the end of the stream has
	 *         been reached
	 * 
	 * @see BufferedReader#readLine()
	 * 
	 * @exception IOException
	 *                If an I/O error occurs
	 */

	public synchronized String readLine() throws IOException {
		StringBuffer s = new StringBuffer(defaultExpectedLineLength);

		ensureOpen();

		for (;;) {
			int i = read();
			if (i == -1)
				break;
			if (i == '\r') {
				mark(1);
				if (read() != '\n') {
					reset();
				}
				break;
			}
			if (i == '\n')
				break;
			s.append((char) i);
		}

		return s.toString();
	}

	private void ensureOpen() throws IOException {
		if (in == null)
			throw new IOException("Stream closed");
	}
}
