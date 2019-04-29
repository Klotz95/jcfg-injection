package nko.jcfg.api;

import nko.jcfg.exceptions.ConfigurationFileUnknownFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

/**
 * This enumeration contains all configuration file formats which will be accepted
 * by the jcfg-framework
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public enum ConfigurationFileType {
    INI("ini"), //TODO: Write an ConfigurationFileMapper for this type
    YAML("yml"),
    XML("xml"),
    JSON("json");

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Map<String, ConfigurationFileType> configurationFileTypeByFileSuffix = new HashMap<>();

    private final String filenameSuffix;

    ConfigurationFileType(String filenameSuffix) {
        this.filenameSuffix = filenameSuffix;
    }

    public String getFilenameSuffix() {
        return filenameSuffix;
    }

    /**
     * Will return the {@link ConfigurationFileType} which belongs to the passed filename suffix
     *
     * @param filenameSuffix which should get resolved to an {@link ConfigurationFileType}
     *
     * @return the {@link ConfigurationFileType} which ends with the passed filenameSuffix
     */
    public static ConfigurationFileType getFileTypeBySuffix(String filenameSuffix) {
        logger.info("Loading file type having {} as suffix", filenameSuffix);

        if (configurationFileTypeByFileSuffix.isEmpty()) {
            fillConfigurationFileTypeFileSuffixMap();
        }

        if (configurationFileTypeByFileSuffix.containsKey(filenameSuffix)) {
            return configurationFileTypeByFileSuffix.get(filenameSuffix);
        }

        throw new ConfigurationFileUnknownFormatException(filenameSuffix);
    }

    /**
     * This method will fill a {@link Map} containing as key the suffix and as value the related {@link ConfigurationFileType}
     */
    private static void fillConfigurationFileTypeFileSuffixMap() {
        logger.debug("Filling configuration file type by suffix map");

        for (ConfigurationFileType curType : ConfigurationFileType.values()) {
            configurationFileTypeByFileSuffix.put(curType.getFilenameSuffix(), curType);
        }
    }
}
