package nko.jcfg.manager;

import nko.jcfg.api.ConfigurationDocument;
import nko.jcfg.api.ConfigurationFile;
import nko.jcfg.exceptions.ConfigurationDocumentNotFoundException;
import nko.jcfg.exceptions.ConfigurationParameterNotDefinedException;
import nko.jcfg.exceptions.GlobalParameterNotDefinedException;
import nko.jcfg.storage.ConfigurationFileFileSystemRequest;
import nko.jcfg.storage.ConfigurationFileStore;
import nko.jcfg.storage.ConfigurationStorageType;
import org.glassfish.hk2.api.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class will handle the loading and merging of {@link nko.jcfg.api.ConfigurationDocument}s
 * The procedure will be as follow:
 * <p>
 * - first load the base configuration file
 * - after that load the mergable configuration file (if present) and overwrite redundant values of the base
 * configuration file
 *
 * This class can get used to handle manual the injection of {@link ConfigurationDocument}s without the help
 * of the configurationServiceLocator
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 29.04.19
 */
public class ConfigurationDocumentManager {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ConfigurationDocumentManagerConfig config;
    private final Map<String, ConfigurationDocument> configurationDocumentsByName;
    private final Map<String, String> globalParameters;
    private final Map<ConfigurationStorageType, ConfigurationFileStore> stores;

    public ConfigurationDocumentManager(ServiceLocator serviceLocator, ConfigurationDocumentManagerConfig config) {
        this.config = config;
        this.configurationDocumentsByName = new HashMap<>();
        this.globalParameters = new HashMap<>();
        stores = serviceLocator.getAllServices(ConfigurationFileStore.class).stream()
                .collect(Collectors.toMap(ConfigurationFileStore::getConfigurationStorageType, curStorage -> curStorage));

        initConfigurationDocumentManager();
    }

    /**
     * This method will return the value of the specified global parameter
     *
     * @param parameterName of the global parameter which value has been requested
     * @return the value of the specified global parameter
     * @throws nko.jcfg.exceptions.GlobalParameterNotDefinedException will be thrown when the parameter can't get
     *                                                                found inside the current cache
     */
    public String getGlobalParameter(String parameterName) {
        logger.trace("Global parameter with name {} has been requested", parameterName);

        if (globalParameters.containsKey(parameterName)) {
            return globalParameters.get(parameterName);
        } else {
            logger.warn("No global parameter with name {} has been found", parameterName);
            throw new GlobalParameterNotDefinedException(parameterName);
        }
    }

    public <T extends ConfigurationDocument> T getConfigurationDocument(String configurationDocumentName) {
        logger.trace("Configuration document with name {} has been requested", configurationDocumentName);

        if (configurationDocumentsByName.containsKey(configurationDocumentName)) {
            return (T) configurationDocumentsByName.get(configurationDocumentName);
        } else {
            logger.warn("No configuration document with name {} has been specified", configurationDocumentName);
            throw new ConfigurationDocumentNotFoundException(configurationDocumentName);
        }
    }

    /**
     * This method will init the {@link ConfigurationDocumentManager} by reading
     * the defined config files and put them inside the cache
     */
    private void initConfigurationDocumentManager() {
        readConfigurationFile(config.getBaseConfigurationFilePath(), ConfigurationStorageType.FILE_SYSTEM, false);
        config.getMergableConfigurationFilePath().
                ifPresent(mergableFilePath -> readConfigurationFile(mergableFilePath, ConfigurationStorageType.FILE_SYSTEM, true));
    }

    /**
     * This method will read out the defined configuration file from the specified storage
     * TODO: Implement a possibility to use other ConfigurationStorageTypes. Currently only filesystem is a valid storage type
     * TODO: Deep configuration merge (Currently only the configuration documents get replaced...)
     *
     * @param path        where the configuration file is located
     * @param storageType on which the configuration file is loacted
     * @param merge       defines whether the values inside the configuration file should get merged. A {@link nko.jcfg.exceptions.ConfigurationParameterNotDefinedException}
     *                    will be thrown when this value is true and a parameter has been found which isn't defined in the current cache
     */
    private void readConfigurationFile(String path, ConfigurationStorageType storageType, boolean merge) {
        logger.info("Reading configuration file located at {}", path);
        ConfigurationFileFileSystemRequest request = new ConfigurationFileFileSystemRequest(path);

        ConfigurationFile configFile = stores.get(storageType).readConfigurationFile(request);
        Map<String, String> configurationGlobalParameters = configFile.getGlobalParameters();
        Map<String, ConfigurationDocument> configurationParameters = configFile.getConfigurationDocuments();

        insertIntoCacheMap(globalParameters, configurationGlobalParameters, merge);
        insertIntoCacheMap(configurationDocumentsByName, configurationParameters, merge);
    }

    /**
     * This method will insert values inside a cache map by merging or just inserting the values
     *
     * @param cacheMap         which hold inside the cache
     * @param insertableValues which need to get transfered to the cacheMap
     * @param merge            defines whether the values should get merged or just inserted inside the cache map
     * @param <T>              the type of the values saved inside the maps
     */
    private <T> void insertIntoCacheMap(Map<String, T> cacheMap, Map<String, T> insertableValues, boolean merge) {
        insertableValues.forEach((key, value) -> {
            if (!merge || cacheMap.containsKey(key)) {
                cacheMap.put(key, value);
            } else {
                logger.error("Couldn't merge configuration parameter inside cache. No parameter with name {} has been defined", key);
                throw new ConfigurationParameterNotDefinedException(key);
            }
        });
    }
}
