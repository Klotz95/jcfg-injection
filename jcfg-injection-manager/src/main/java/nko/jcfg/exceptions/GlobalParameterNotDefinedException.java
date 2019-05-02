package nko.jcfg.exceptions;

/**
 * This {@link ConfigurationException} will be thrown when a global parameter has been requested
 * which can't get found inside the current cache.
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 02.05.19
 */
public class GlobalParameterNotDefinedException extends ConfigurationException{

    private final String globalParameterName;

    public GlobalParameterNotDefinedException(String globalParameterName){
        this.globalParameterName = globalParameterName;
    }

    public String getGlobalParameterName() {
        return globalParameterName;
    }
}
