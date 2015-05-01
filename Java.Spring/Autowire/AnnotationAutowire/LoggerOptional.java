package spring.testing.annotations;

import org.springframework.beans.factory.annotation.Autowired;

public class LoggerOptional {

	private ConsoleWriter consoleWriter;
	private FileWriter fileWriter;
	
	@Autowired(required=false)
	public void setConsoleWriter(ConsoleWriter writer) {
		this.consoleWriter = writer;
	}

	@Autowired
	public void setFileWriter(FileWriter fileWriter) {
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
