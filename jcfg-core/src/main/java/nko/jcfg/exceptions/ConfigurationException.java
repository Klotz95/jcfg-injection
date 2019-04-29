package nko.jcfg.exceptions;

/**
 * The base class of all {@link Exception}s which will be thrown by this framework
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public class ConfigurationException extends RuntimeException {

    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String message) {
        super(message);
    }
}
