package br.com.leonardoferreira.jbacon.util;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Wrapper around Spring BeanUtils, to provide {@link BeanUtils#copyPropertiesNotNull}
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeanUtils extends org.springframework.beans.BeanUtils {

    public static void copyPropertiesNotNull(final Object source, final Object target) {
        BeanWrapper targetWrapper = new BeanWrapperImpl(target);

        String[] ignoreProperties = Stream.of(targetWrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(it -> targetWrapper.getPropertyValue(it) != null)
                .toArray(String[]::new);

        copyProperties(source, target, ignoreProperties);
    }

}
