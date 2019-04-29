package nko.jcfg.storage;

import nko.jcfg.api.ConfigurationFile;
import nko.jcfg.api.ConfigurationFileType;
import nko.jcfg.mapper.ConfigurationFileMapper;
import org.glassfish.hk2.api.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class describes the functions required by a {@link ConfigurationFileStore} to save and load
 * values of an {@link nko.jcfg.api.ConfigurationFile}
 * <p>
 * T defines the type of the {@link ConfigurationFileStoreRequest} which will be used to point to the destination of the
 * configuration file
 * To work with the {@link ConfigurationFileMapper} an input/outputstream has to get open to the destination on
 * which the configuration file should get located
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 2019-04-29
 */
public abstract class ConfigurationFileStore<T extends ConfigurationFileStoreRequest> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    final Map<ConfigurationFileType, ConfigurationFileMapper> configurationFileMapperByType;

    @Inject
    public ConfigurationFileStore(ServiceLocator serviceLocator) {
        this.configurationFileMapperByType = new HashMap<>();

        initConfigurationFileMapper(serviceLocator);
    }

    /**
     * Returns the {@link ConfigurationFile} which has been defined with the passed {@link ConfigurationFileStoreRequest}
     *
     * @param configurationFileStoreRequest which defines the parameters to point to a specific configuration file location
     * @return the requested {@link ConfigurationFile} if present
     */
    public abstract ConfigurationFile readConfigurationFile(T configurationFileStoreRequest);

    /**
     * This method will save a {@link ConfigurationFile} object to the {@link ConfigurationFileStore} at the location
     * defined by the {@link ConfigurationFileStoreRequest}
     *
     * @param configurationFile             which need to get saved inside the {@link ConfigurationFileStore}
     * @param configurationFileStoreRequest defines
     */
    public abstract void writeConfigurationFile(ConfigurationFile configurationFile, T configurationFileStoreRequest);

    /**
     * This method returns the suffix of the passed path. It will search for the last dot position and returns a substring
     * containing all letters after that position
     *
     * @param path which includes the suffix of the file
     * @return a String containing the suffix of the passed path
     */
    protected String getFileSuffix(String path) {
        int lastDotPosition = path.lastIndexOf(".");
        int substringStartPosition = lastDotPosition + 1;

        return path.substring(substringStartPosition);
    }

    /**
     * Loads the currently hk2 registered {@link ConfigurationFileMapper}
     *
     * @param serviceLocator used to access the registered {@link ConfigurationFileMapper}
     */
    private void initConfigurationFileMapper(ServiceLocator serviceLocator) {
        logger.debug("Load registered configuration file mapper");
        List<? extends ConfigurationFileMapper> registeredConfigurationFileMapper = serviceLocator.getAllServices(ConfigurationFileMapper.class);

        for (ConfigurationFileMapper currentMapper : registeredConfigurationFileMapper) {
            logger.debug("Adding configuration file mapper for type {}", currentMapper.getConfigurationFileType());
            configurationFileMapperByType.put(currentMapper.getConfigurationFileType(), currentMapper);
        }
    }


}
