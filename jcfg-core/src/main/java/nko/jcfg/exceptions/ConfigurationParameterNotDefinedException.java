package nko.jcfg.exceptions;

/**
 * This exception will be thrown, whenever a parameter field has been requested, which hasn't been
 * defined inside the {@link nko.jcfg.api.ConfigurationDocument}
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public class ConfigurationParameterNotDefinedException extends ConfigurationException {

    private final String parameterName;
    private final String configurationDocumentName;

    /**
     * This method will construct a {@link ConfigurationParameterNotDefinedException}
     *
     * @param parameterName             of the parameter which hasn't been defined inside the
     *                                  {@link nko.jcfg.api.ConfigurationDocument}
     * @param configurationDocumentName of the {@link nko.jcfg.api.ConfigurationDocument} which has a missing parameter
     */
    public ConfigurationParameterNotDefinedException(String parameterName, String configurationDocumentName) {
        super("No field with name " + parameterName + " has been defined inside the configurationdocument " + configurationDocumentName);

        this.configurationDocumentName = configurationDocumentName;
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getConfigurationDocumentName() {
        return configurationDocumentName;
    }
}
