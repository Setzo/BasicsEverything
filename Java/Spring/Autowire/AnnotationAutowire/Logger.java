package spring.testing.annotations;

import org.springframework.beans.factory.annotation.Autowired;

public class Logger {

    @Autowired
    private ConsoleWriter consoleWriter;
    @Autowired
    private FileWriter fileWriter;

	/*@Autowired
    public Logger(ConsoleWriter consoleWriter, FileWriter fileWriter) {
		
		this.consoleWriter = consoleWriter;
		this.fileWriter = fileWriter;
	}
	
	//By Type
	// @Autowired
	public void setConsoleWriter(ConsoleWriter writer) {
		this.consoleWriter = writer;
	}

	// @Autowired
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}*/

    public void writeFile(String text) {
        fileWriter.write(text);
    }

    public void writeConsole(String text) {
        consoleWriter.write(text);
    }

}
