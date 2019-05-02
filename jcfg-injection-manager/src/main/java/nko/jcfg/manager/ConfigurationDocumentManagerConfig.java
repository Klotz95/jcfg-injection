package nko.jcfg.manager;

import nko.jcfg.storage.ConfigurationStorageType;

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
    private final ConfigurationStorageType baseStorage;
    private final String mergableConfigurationFilePath;
    private final ConfigurationStorageType mergableStorage;


    public ConfigurationDocumentManagerConfig(String baseConfigurationFilePath, ConfigurationStorageType baseStorage){
        this.baseConfigurationFilePath = baseConfigurationFilePath;
        this.baseStorage = baseStorage;
        this.mergableStorage = null;
        this.mergableConfigurationFilePath = null;
    }

    public ConfigurationDocumentManagerConfig(String baseConfigurationFilePath, ConfigurationStorageType baseStorage, String mergableConfigurationFilePath, ConfigurationStorageType mergableStorage) {
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

    public ConfigurationStorageType getBaseStorage() {
        return baseStorage;
    }

    public Optional<ConfigurationStorageType> getMergableStorage() {
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
