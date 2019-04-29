package nko.jcfg.exceptions;

import nko.jcfg.api.ConfigurationFileType;

/**
 * This {@link RuntimeException} will be thrown when an inputstream
 * can't get mapped to a {@link nko.jcfg.api.ConfigurationFile} object
 * by the {@link nko.jcfg.mapper.ConfigurationFileMapper}
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public class ConfigurationFileNotParsableException extends ConfigurationException{

    private final ConfigurationFileType configurationFileType;
    private final String pathToFile;

    public ConfigurationFileNotParsableException(ConfigurationFileType configurationFileType, String pathToFile){
        super("Parsing error during handling file " + pathToFile + " of type " + configurationFileType);

        this.configurationFileType = configurationFileType;
        this.pathToFile = pathToFile;
    }
}
