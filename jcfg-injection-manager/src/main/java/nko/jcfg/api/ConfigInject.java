package nko.jcfg.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation can get used to mark a method as entry point of the configuration injection.
 * The annotation provides information about the name of the
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 29.04.19
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigInject {

    /**
     * Defines the {@link Class} of the configuration document. Please be patient that the {@link ConfigurationDocument}
     * has to extend the {@link ConfigurationDocument} class.
     *
     * @return the {@link Class} in which the {@link ConfigurationDocument} can get mapped
     */
    public Class<? extends ConfigurationDocument> configurationDocumentType();

    /**
     * Returns the name of the {@link ConfigurationDocument}
     *
     * @return the {@link String} representing the name of the {@link ConfigurationDocument}
     */
    public String configurationName();
}
