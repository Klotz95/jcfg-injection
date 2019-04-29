package nko.jcfg.mapper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import nko.jcfg.api.ConfigurationFileType;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.Optional;

/**
 * This class is an implementation of the {@link ConfigurationFileMapper} which handles the mapping of XML-configuraiton-
 * files to {@link nko.jcfg.api.ConfigurationFile} objects in both directions
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
@Service
@Named("xml-configuration-file-mapper")
public class XmlConfigurationFileMapper extends ConfigurationFileMapper{

    private static final Logger logger = LoggerFactory.getLogger(XmlConfigurationFileMapper.class);


    @Override
    public ConfigurationFileType getConfigurationFileType() {
        logger.trace("Returning the xml-configuration-file type as compatible type of this ConfigurationFileMapper");
        return ConfigurationFileType.XML;
    }

    @Override
    protected Optional<JsonFactory> getObjectMapperTypeFactory() {
        logger.trace("Returning the XmlJsonFactory as object mapper type factory");
        return Optional.of(new XmlFactory());
    }
}
