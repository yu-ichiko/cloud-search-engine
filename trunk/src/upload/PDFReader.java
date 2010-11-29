//---------------------------------------------------------
//PDFReaderクラス
//
//UploadServerでPDFの内容を登録する時に使用する
//---------------------------------------------------------
package upload;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReader {

	/**
	 *PDFからテキストを抽出する
	 * @param filePath
	 * @return
	 */
	public String extractPDF(String filePath) {
		ByteArrayOutputStream out = null;
		try {
			FileInputStream pdfStream = new FileInputStream(filePath);
			PDFParser parser = new PDFParser(pdfStream);
			parser.parse();
			PDDocument pdf = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			out = new ByteArrayOutputStream();
			stripper.writeText(pdf, new BufferedWriter(new OutputStreamWriter(out)));
			pdfStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

}