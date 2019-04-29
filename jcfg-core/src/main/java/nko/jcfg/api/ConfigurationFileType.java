package nko.jcfg.api;

/**
 * This enumeration contains all configuration file formats which will be accepted
 * by the jcfg-framework
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public enum ConfigurationFileType {
    INI, //TODO: Write an ConfigurationFileMapper for this type
    YAML,
    XML,
    JSON;
}
