package spring.testing.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component( "loggerJSR" )
public class LoggerJSR {

    private LogWriter consoleWriter;
    private LogWriter fileWriter;

    @Resource( name = "consoleWriter" )
    public void setConsoleWriter(LogWriter writer) {
        this.consoleWriter = writer;
    }

    @Resource( name = "fileWriter" )
    public void setFileWriter(LogWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void writeFile(String text) {
        fileWriter.write(text);
    }

    public void writeConsole(String text) {
        if (this.consoleWriter != null) {
            consoleWriter.write(text);
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }
}
