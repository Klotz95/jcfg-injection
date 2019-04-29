package nko.jcfg.mapper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import nko.jcfg.api.ConfigurationFile;
import nko.jcfg.api.ConfigurationFileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

/**
 * This abstract class describes the basic structure of a {@link ConfigurationFileMapper}
 * which is writing and saving the configuration file having the specified format
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public abstract class ConfigurationFileMapper {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationFileMapper.class);
    private static final String DEFAULT_JSON_FACTORY_DESCRIPTION = "DEFAULT JSON";

    private final ObjectMapper objectMapper;

    public ConfigurationFileMapper() {
        this.objectMapper = initObjectMapper();
    }

    /**
     * This method will return the {@link ConfigurationFileType} which this {@link ConfigurationFileMapper}
     * can parse
     *
     * @return the {@link ConfigurationFileType} for which this {@link ConfigurationFileMapper}
     * is comaptible to
     */
    public abstract ConfigurationFileType getConfigurationFileType();

    /**
     * This method returns the {@link JsonFactory} used to write the specified {@link ConfigurationFileType}
     * with the help of the {@link ObjectMapper} of the jackson-library.
     * If the optional is empty the default {@link com.fasterxml.jackson.databind.MappingJsonFactory} will be
     * used
     *
     * @return the {@link JsonFactory} which should get used with this {@link ConfigurationFileMapper}
     */
    protected abstract Optional<JsonFactory> getObjectMapperTypeFactory();

    /**
     * This method will create the {@link ObjectMapper} used to parse and write the
     * configuration files
     *
     * @return the generated {@link ObjectMapper}
     */
    protected ObjectMapper initObjectMapper() {
        Optional<JsonFactory> jsonFactory = getObjectMapperTypeFactory();
        logger.debug("Init object mapper with format {}", jsonFactory.map(JsonFactory::getFormatName).orElseGet(() -> DEFAULT_JSON_FACTORY_DESCRIPTION));

        ObjectMapper res = jsonFactory.map(ObjectMapper::new).orElseGet(ObjectMapper::new);
        res.findAndRegisterModules();
        res.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return res;
    }

    /**
     * Reads the passed {@link InputStream} and map it to a {@link ConfigurationFile}
     *
     * @param inputstream which need to get mapped to an {@link ConfigurationFile}
     * @return the generated {@link ConfigurationFile} representing the information
     * which were defined inside the {@link InputStream}
     * @throws IOException when the parsing process went wrong or the {@link InputStream}
     *                     is corrupt
     */
    public ConfigurationFile readStream(InputStream inputstream) throws IOException {
        logger.info("Reading configuration file out of inputstream");

        return objectMapper.readValue(inputstream, ConfigurationFile.class);
    }

    /**
     * Writes the configuration file to the passed {@link OutputStream}.
     * The format will be defined by the use {@link ConfigurationFileMapper}
     *
     * @param outputStream      which will be used to save the configuration-file
     * @param configurationFile which need to get saved in the passed {@link OutputStream}
     * @throws IOException will be thrown when an parsing error occured or the {@link OutputStream}
     *                     is corrupt
     */
    public void writeToStream(OutputStream outputStream, ConfigurationFile configurationFile) throws IOException {
        logger.info("Writing configuration file to the passed outputstream");

        objectMapper.writeValue(outputStream, configurationFile);
    }

}
