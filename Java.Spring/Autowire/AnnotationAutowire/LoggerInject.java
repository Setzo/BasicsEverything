package spring.testing.annotations;

import javax.inject.Inject;
import javax.inject.Named;


public class LoggerInject {

	private LogWriter consoleWriter;
	private LogWriter fileWriter;
	
	@Inject
	@Named(value="consoleWriter")
	public void setConsoleWriter(LogWriter writer) {
		this.consoleWriter = writer;
	}

	@Inject
	@Named(value="fileWriter")
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
	
	public void init() {
		System.out.println("init");
	}

	public void destroy() {
		System.out.println("destroy");
	}
}
