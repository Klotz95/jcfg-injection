package nko.jcfg.storage;

import nko.jcfg.api.ConfigurationFileType;

/**
 * This class describes an {@link ConfigurationFileStoreRequest} which will be used by the {@link ConfigurationFileFileSystemStore}
 * It only holds the path to the {@link nko.jcfg.api.ConfigurationFile} which should get used
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 29.04.19
 */
public class ConfigurationFileFileSystemRequest implements ConfigurationFileStoreRequest {

    private final String path;

    public ConfigurationFileFileSystemRequest(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
