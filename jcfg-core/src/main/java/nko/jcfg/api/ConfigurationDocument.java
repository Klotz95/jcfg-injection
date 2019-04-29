package nko.jcfg.api;

import nko.jcfg.exceptions.ConfigurationParameterNotDefinedException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines the basic function required to define a class as {@link ConfigurationDocument}.
 * A class, which is representing the structure of a {@link ConfigurationDocument} has to extend this class
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public abstract class ConfigurationDocument {

    protected final Map<String, Object> configurationParameters;


    public ConfigurationDocument() {
        this.configurationParameters = new HashMap<>();
    }

    /**
     * This method will return a configuration parameter with the specified name
     *
     * @param parameterName of the parameter which should get returned by this method cal
     * @return the parameter as {@link Object}
     */
    public Object getConfigurationParameter(String parameterName) {
        if (configurationParameters.containsKey(parameterName)) {
            return configurationParameters.get(parameterName);
        }

        throw new ConfigurationParameterNotDefinedException(parameterName, this.getConfigurationDocumentName());
    }

    public <T> T getConfigurationParameter (String parameterName, Class<T> type){
        Object configurationParameter = getConfigurationParameter(parameterName);

        return (T)configurationParameter;
    }

    /**
     * Set the parameter with the specified name to the specified value
     *
     * @param parameterName of the parameter which value should get changed
     * @param value which will be saved for the specified parameter
     */
    public void setConfigurationParameter(String parameterName, Object value){
        this.configurationParameters.put(parameterName, value);
    }

    /**
     * Returns the {@link Map} having the parameters of the {@link ConfigurationDocument} inside
     *
     * @return the {@link Map} object which is currently holding the parameters of the {@link ConfigurationDocument}
     */
    public Map<String, ? extends Object> getConfigurationParameters() {
        return configurationParameters;
    }

    /**
     * This method will return the name of the {@link ConfigurationDocument}
     *
     * @return the name of the {@link ConfigurationDocument} as {@link String}
     */
    public abstract String getConfigurationDocumentName();
}
