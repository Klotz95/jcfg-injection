package nko.jcfg.mapper;

import com.fasterxml.jackson.core.JsonFactory;
import nko.jcfg.api.ConfigurationFileType;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.Optional;

/**
 * This class is an implementation of the {@link ConfigurationFileMapper} which will
 * handle the mapping of {@link nko.jcfg.api.ConfigurationFile}s to a JSON-File in both
 * directions
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
@Service
@Named("json-configuration-file-mapper")
public class JsonConfigurationFileMapper extends ConfigurationFileMapper{

    private static final Logger logger = LoggerFactory.getLogger(JsonConfigurationFileMapper.class);

    @Override
    public ConfigurationFileType getConfigurationFileType() {
        logger.trace("Returning JSON-configuration-file-type as compatible typ of this ConfigurationFileMapper");
        return ConfigurationFileType.JSON;
    }

    @Override
    protected Optional<JsonFactory> getObjectMapperTypeFactory() {
      return Optional.empty();
    }

}
