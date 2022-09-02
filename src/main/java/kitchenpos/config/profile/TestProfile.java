package kitchenpos.config.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TestProfile implements Condition {

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        final Environment environment = context.getEnvironment();

        return Profile.isTestProfile(environment.getActiveProfiles());
    }
}
