package nko.jcfg.storage;

import nko.jcfg.api.ConfigurationFile;

/**
 * This interface describes the functions required by a {@link ConfigurationFileStore} to save and load
 * values of an {@link nko.jcfg.api.ConfigurationFile}
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 2019-04-29
 */
public interface ConfigurationFileStore {

    public ConfigurationFile readConfigurationFile();

    public void writeConfigurationFile(ConfigurationFile configurationFile);
}
