package kitchenpos.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MenuGroupTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("메뉴 그룹의 이름이 없다면 예외가 발생한다.")
    void When_MenuGroupNameIsEmpty_Expect_Exception(String menuGroupName) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new MenuGroup(menuGroupName)
        );
    }
}
