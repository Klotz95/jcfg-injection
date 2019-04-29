package nko.jcfg.exceptions;

/**
 * This {@link Exception} will be thrown when a file-suffix can't get mapped to an {@link nko.jcfg.api.ConfigurationFileType}
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 29.04.19
 */
public class ConfigurationFileUnknownFormatException extends ConfigurationException{

    private final String fileSuffix;

    public ConfigurationFileUnknownFormatException(String fileSuffix){
        super("Unknown format " + fileSuffix + ". Please enter a file suffix wich can get mapped to an known type of configuration.");
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }
}
