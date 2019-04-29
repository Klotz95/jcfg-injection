package nko.jcfg.exceptions;

/**
 * This {@link Exception} will be thrown when the {@link nko.jcfg.api.ConfigurationFile} can't get found
 * at the specified path
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 29.04.19
 */
public class ConfigurationFileNotFoundException extends ConfigurationException{

    private final String path;

    public ConfigurationFileNotFoundException(String path){
        super("Couldn't find configuration file located at " + path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
