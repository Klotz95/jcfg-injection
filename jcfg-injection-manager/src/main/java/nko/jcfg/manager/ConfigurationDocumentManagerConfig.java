package nko.jcfg.manager;

import nko.jcfg.storage.ConfigurationStorageTypes;
import sun.security.krb5.Config;

import java.util.Optional;

/**
 * This class has to get added to the {@link com.fasterxml.jackson.databind.ObjectMapper} before the first accessing
 * of the {@link ConfigurationDocumentManager}
 * otherwise the {@link ConfigurationDocumentManager} will search for the configuration files at the predefined location
 *
 * @author Nico Kotlenga (NicoKotlenga@live.de)
 * @since 29.04.19
 */
public class ConfigurationDocumentManagerConfig {

    private final String baseConfigurationFilePath;
    private final ConfigurationStorageTypes baseStorage;
    private final String mergableConfigurationFilePath;
    private final ConfigurationStorageTypes mergableStorage;


    public ConfigurationDocumentManagerConfig(String baseConfigurationFilePath, ConfigurationStorageTypes baseStorage){
        this.baseConfigurationFilePath = baseConfigurationFilePath;
        this.baseStorage = baseStorage;
        this.mergableStorage = null;
        this.mergableConfigurationFilePath = null;
    }

    public ConfigurationDocumentManagerConfig(String baseConfigurationFilePath, ConfigurationStorageTypes baseStorage, String mergableConfigurationFilePath, ConfigurationStorageTypes mergableStorage) {
        this.baseConfigurationFilePath = baseConfigurationFilePath;
        this.baseStorage = baseStorage;
        this.mergableConfigurationFilePath = mergableConfigurationFilePath;
        this.mergableStorage = mergableStorage;
    }

    /**
     * @return the path to the configuration file which will be used for the initial fill of the {@link ConfigurationDocumentManager}
     */
    public String getBaseConfigurationFilePath() {
        return baseConfigurationFilePath;
    }

    /**
     * @return the path to the configuration file which will be used to overwrite the attributes defined inside the base
     * configuration file. Because of being an optional configuration parameter this value will be delivered as
     * {@link Optional}
     */
    public Optional<String> getMergableConfigurationFilePath() {
        return Optional.of(mergableConfigurationFilePath);
    }

    public ConfigurationStorageTypes getBaseStorage() {
        return baseStorage;
    }

    public Optional<ConfigurationStorageTypes> getMergableStorage() {
        return Optional.of(mergableStorage);
    }

    @Override
    public String toString() {
        return "ConfigurationDocumentManagerConfig{" +
                "baseConfigurationFilePath='" + baseConfigurationFilePath + '\'' +
                ", mergableConfigurationFilePath='" + mergableConfigurationFilePath + '\'' +
                '}';
    }
}
