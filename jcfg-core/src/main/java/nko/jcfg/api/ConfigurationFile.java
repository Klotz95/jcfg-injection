package nko.jcfg.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * This class represents the structure of a {@link ConfigurationFile} which will be written
 * to the specified storage
 *
 * @author: NKO (NicoKotlenga@live.de)
 * @since: 29.04.2019
 */
public class ConfigurationFile {

    private static final String CONFIGURATION_DOCUMENTS_PARAMETER_NAME = "configuration-documents";
    private static final String GLOBAL_PARAMETERS_PARAMETER_NAME = "global-parameters";

    @JsonProperty(CONFIGURATION_DOCUMENTS_PARAMETER_NAME)
    private final Map<String, JsonNode> configurationDocuments;
    @JsonProperty(GLOBAL_PARAMETERS_PARAMETER_NAME)
    private final Map<String, String> globalParameters;

    public ConfigurationFile(Map<String, JsonNode> configurationDocuments, Map<String, String> globalParameters) {
        this.configurationDocuments = configurationDocuments;
        this.globalParameters = globalParameters;
    }

    public Map<String, JsonNode> getConfigurationDocuments() {
        return configurationDocuments;
    }

    public Map<String, String> getGlobalParameters() {
        return globalParameters;
    }
}
