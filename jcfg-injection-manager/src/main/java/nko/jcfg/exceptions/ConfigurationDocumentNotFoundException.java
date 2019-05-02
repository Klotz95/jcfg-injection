package nko.jcfg.exceptions;

/**
 * This exception will be thrown by the {@link nko.jcfg.manager.ConfigurationDocumentManager} when
 * a {@link nko.jcfg.api.ConfigurationDocument} has been requested which can't get found
 * inside the current cache storage
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 02.05.19
 */
public class ConfigurationDocumentNotFoundException extends ConfigurationException{

    private final String configurationDocumentName;

    public ConfigurationDocumentNotFoundException(String configurationDocumentName) {
        super("No configuration document with name " + configurationDocumentName + " has been defined.");
        this.configurationDocumentName = configurationDocumentName;
    }

    public String getConfigurationDocumentName() {
        return configurationDocumentName;
    }
}
