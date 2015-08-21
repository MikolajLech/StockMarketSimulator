package milech.reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvReader implements Reader{
	List<String> data;
	int lastUsedLine = 0;
	
	public CsvReader(String file) {
		try {
			data = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNextLine() {
		if(lastUsedLine >= data.size()) {
			return null;
		}
		return data.get(++lastUsedLine - 1);
	}
}

