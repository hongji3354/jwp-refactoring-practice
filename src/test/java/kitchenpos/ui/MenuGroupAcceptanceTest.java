package kitchenpos.ui;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.domain.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuGroupAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("메뉴 그룹을 생성한다.")
    void createMenuGroup() {
        // given
        final MenuGroup menuGroup = new MenuGroup("추천메뉴");

        // when
        final ExtractableResponse<Response> 메뉴_그룹_생성_요청_응답 = 메뉴_그룹_생성을_요청(menuGroup);

        // then
        메뉴_그룹_생성됨(메뉴_그룹_생성_요청_응답);
    }

    private void 메뉴_그룹_생성됨(final ExtractableResponse<Response> response) {
        final MenuGroup body = response.as(MenuGroup.class);

        assertAll(
                () -> {
                    assertEquals(HttpStatus.CREATED.value(), response.statusCode());
                },
                () -> {
                    assertNotNull(body.getId());
                },
                () -> {
                    assertEquals("추천메뉴", body.getName());
                }
        );

    }

    private ExtractableResponse<Response> 메뉴_그룹_생성을_요청(MenuGroup body) {
        return post("/api/menu-groups", body);
    }
}
