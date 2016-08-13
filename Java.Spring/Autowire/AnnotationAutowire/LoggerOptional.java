package spring.testing.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class LoggerOptional {

	private LogWriter consoleWriter;
	private LogWriter fileWriter;
	
	@Autowired
	@Qualifier("toConsole")
	public void setConsoleWriter(LogWriter writer) {
		this.consoleWriter = writer;
	}

	@Autowired
	@Qualifier("toFile")
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	
	public void writeConsole(String text) {
		if(this.consoleWriter != null) {
			consoleWriter.write(text);
		}
	}

}
