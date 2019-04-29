package nko.jcfg.manager;

import nko.jcfg.api.ConfigurationDocument;
import nko.jcfg.storage.ConfigurationFileStore;
import nko.jcfg.storage.ConfigurationStorageTypes;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will handle the loading and merging of {@link nko.jcfg.api.ConfigurationDocument}s
 * The procedure will be as follow:
 *
 * - first load the base configuration file
 * - after that load the mergable configuration file (if present) and overwrite redundant values of the base
 *   configuration file
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 29.04.19
 */
@Service
@Named("configuration-document-manager")
public class ConfigurationDocumentManager {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String BASE_CONFGURATION_DOCUMENT_NAME = "reference.conf";
    private static final ConfigurationDocumentManagerConfig DEFAULT_CONFIG = new ConfigurationDocumentManagerConfig(BASE_CONFGURATION_DOCUMENT_NAME);

    private final ConfigurationDocumentManagerConfig config;
    private final Map<String, ConfigurationDocument> configurationDocumentsByName;
    private final Map<ConfigurationStorageTypes, ConfigurationFileStore<?>> configurationFileStores;

    @Inject
    public ConfigurationDocumentManager(ServiceLocator serviceLocator){
        ConfigurationDocumentManagerConfig registeredConfig = serviceLocator.getService(ConfigurationDocumentManagerConfig.class);
        this.config = registeredConfig != null ? registeredConfig : DEFAULT_CONFIG;
        this.configurationDocumentsByName = new HashMap<>();
        this.configurationFileStores = new HashMap<>();
    }

    /**
     * This method will init the {@link ConfigurationDocumentManager} by reading
     * the defined config files and
     */
    private void initConfigurationDocumentManager(ServiceLocator serviceLocator){
        logger.info("init configuration document manager with config file {}", config);


    }
}
