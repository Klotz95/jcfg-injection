package nko.jcfg.mapper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import nko.jcfg.api.ConfigurationFileType;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.Optional;

/**
 * This is an implementation of the {@link ConfigurationFileMapper} handling
 * YAML-configuration-files
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
@Service
@Named("yaml-configuration-file-mapper")
public class YamlConfigurationFileMapper extends ConfigurationFileMapper{

    private static final Logger logger = LoggerFactory.getLogger(YamlConfigurationFileMapper.class);


    @Override
    public ConfigurationFileType getConfigurationFileType() {
        logger.trace("Returning YAML-configuration-file-type as compatible type");
        return ConfigurationFileType.YAML;
    }

    @Override
    protected Optional<JsonFactory> getObjectMapperTypeFactory() {
        logger.trace("Returing YAML factory as object mapper type factory");
        return Optional.of(new YAMLFactory());
    }
}
