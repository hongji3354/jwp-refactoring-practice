package kitchenpos.config.profile;

import java.util.Arrays;

public class Profile {

    public static final String TEST = "TEST";

    private Profile() {
    }

    public static boolean isTestProfile(String[] profiles) {
        return Arrays.stream(profiles)
                .filter(TEST::equals)
                .anyMatch(TEST::equals);
    }
}
