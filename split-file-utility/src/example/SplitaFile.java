package example;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * Split a file utility, you could split any file to sized chunks
 * Read the ByteArrayOutputStream as a string or to other files
 */

public class SplitaFile {

	private SplitaFile() {

	}

	// file to chunks (split files to 500kb)? 500kb is 500000 bytes
	public static List<ByteArrayOutputStream> splitFile(File f, int sizeinBytes) {
		List<ByteArrayOutputStream> chunklist = new ArrayList<>();
		try {
			int sizeOfFiles = sizeinBytes;
			byte[] buffer = new byte[sizeOfFiles];

			try (FileInputStream fis = new FileInputStream(f); BufferedInputStream bis = new BufferedInputStream(fis)) {

				int bytesAmount = 0;
				while ((bytesAmount = bis.read(buffer)) > 0) {
					try (OutputStream out = new ByteArrayOutputStream()) {
						out.write(buffer, 0, bytesAmount);
						out.flush();
						chunklist.add((ByteArrayOutputStream) out);
					}
				}
			}
		} catch (Exception e) {
			// get the error
		}

		return chunklist;
	}

}
