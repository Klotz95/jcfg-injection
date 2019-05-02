package nko.jcfg.storage;

import nko.jcfg.api.ConfigurationFile;
import nko.jcfg.api.ConfigurationFileType;
import nko.jcfg.exceptions.ConfigurationException;
import nko.jcfg.exceptions.ConfigurationFileNotFoundException;
import nko.jcfg.exceptions.ConfigurationFileNotParsableException;
import nko.jcfg.mapper.ConfigurationFileMapper;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;

/**
 * This class is an implementation of a {@link ConfigurationFileStore}
 * It uses the filesystem to load and save the {@link nko.jcfg.api.ConfigurationFile}s
 * To make an request to this storage an {@link ConfigurationFileFileSystemRequest} has to get used.
 * <p>
 * This class will map the {@link IOException}s thrown by the {@link ConfigurationFileMapper} to {@link ConfigurationException}
 * so that more clear when and where an error happens
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 29.04.19
 */
@Service
@Named("configuration-file-file-system-store")
public class ConfigurationFileFileSystemStore extends ConfigurationFileStore {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    public ConfigurationFileFileSystemStore(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public ConfigurationFile readConfigurationFile(ConfigurationFileStoreRequest configurationFileStoreRequest) throws ConfigurationException {
        ConfigurationFileFileSystemRequest convertedRequest = (ConfigurationFileFileSystemRequest) configurationFileStoreRequest;
        String path = convertedRequest.getPath();
        logger.info("Reading configuration file located at {}", path);

        File configurationFileReference = new File(convertedRequest.getPath());
        if (configurationFileReference.exists()) {
            int lastDot = path.lastIndexOf(".");
            String fileSuffix = path.substring(lastDot + 1);

            ConfigurationFileType fileType = ConfigurationFileType.getFileTypeBySuffix(fileSuffix);
            ConfigurationFileMapper fileMapper = configurationFileMapperByType.get(fileType);
            InputStream configIn = null;
            ConfigurationFile res;

            try {
                configIn = new FileInputStream(path);
                res = fileMapper.readStream(configIn);

            } catch (FileNotFoundException e) {
                logger.warn("The file couldn't get found at path {}", path, e);
                throw new ConfigurationFileNotFoundException(path);
            } catch (IOException e) {
                logger.error("An unknown error occured during the access of file {}", path, e);
                throw new ConfigurationFileNotParsableException(fileType, path);
            } finally {
                if (configIn != null) {
                    try {
                        configIn.close();
                    } catch (IOException e) {
                        logger.error("Unexpected error during closing the inputstream", e);
                        throw new ConfigurationFileNotParsableException(fileType, path);
                    }
                }
            }

            return res;
        }

        logger.warn("No configuration file has been found at the specified location {}", path);
        throw new ConfigurationFileNotFoundException(path);
    }

    @Override
    public void writeConfigurationFile(ConfigurationFile configurationFile, ConfigurationFileStoreRequest configurationFileStoreRequest) {
        ConfigurationFileFileSystemRequest convertedRequest = (ConfigurationFileFileSystemRequest)configurationFileStoreRequest;
        String path = convertedRequest.getPath();
        logger.info("Writing configuration file to {}", path);

        String fileSuffix = getFileSuffix(path);
        ConfigurationFileType configurationFileType = ConfigurationFileType.getFileTypeBySuffix(fileSuffix);
        ConfigurationFileMapper configurationFileMapper = configurationFileMapperByType.get(configurationFileType);

        File configurationFileReference = new File(convertedRequest.getPath());
        OutputStream configOut = null;


        try {
            configOut = new FileOutputStream(configurationFileReference);
            configurationFileMapper.writeToStream(configOut, configurationFile);
            configOut.flush();

        } catch (FileNotFoundException e) {
            logger.error("Couldn't access file located at {}. Maybe it's a directory or you don't have the permission to access this file", path, e);
            throw new ConfigurationFileNotFoundException(path);
        } catch (IOException e) {
            logger.error("Couldn't save configuration file. An unexpected error occured", e);
            throw new ConfigurationFileNotParsableException(configurationFileType, path);
        } finally {
            logger.trace("Closing output stream of file {}", path);

            if (configOut != null) {
                try {
                    configOut.close();
                } catch (IOException e) {
                    logger.error("Error during closing the outputstream of file {}", path, e);
                    throw new ConfigurationFileNotParsableException(configurationFileType, path);
                }

            }
        }

    }

    @Override
    public ConfigurationStorageType getConfigurationStorageType() {
        return ConfigurationStorageType.FILE_SYSTEM;
    }
}
