package nko.jcfg.exceptions;

/**
 * This {@link Exception} will be thrown when the mergable configuration file contains a parameter
 * which hasn't been defined inside the base configuration file
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 02.05.19
 */
public class ConfigurationParameterNotDefinedException extends ConfigurationException{

    private final String parameterName;

    public ConfigurationParameterNotDefinedException(String parameterName){
        super("No configuration parameter with name " + parameterName + " had been defined");
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
