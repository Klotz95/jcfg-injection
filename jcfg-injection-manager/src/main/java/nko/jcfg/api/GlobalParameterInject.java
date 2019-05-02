package nko.jcfg.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation can get used to inject a global parameter value
 * It has to get notated beside a parameter inside the definition part of a method
 *
 * @author Nico Kotlenga (Nico.Kotlenga.Student@atis-systems.com)
 * @since 02.05.19
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalParameterInject {

    public String parameterName();
}
