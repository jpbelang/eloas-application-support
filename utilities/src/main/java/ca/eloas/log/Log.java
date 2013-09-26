package ca.eloas.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JP
 */
public class Log {


    private final Logger logger;

    private Log(Logger logger) {

        this.logger = logger;
    }

    public static Log forClass(Class c) {

        return new Log(LoggerFactory.getLogger(c));
    }

    public void warn(String format, Object... s) {

        logger.warn(format, s);
    }

    public void info(String format, Object... s ) {

        logger.info(format, s);
    }

    public void error(String format, Object... s) {

        logger.error(format, s);
    }
}
