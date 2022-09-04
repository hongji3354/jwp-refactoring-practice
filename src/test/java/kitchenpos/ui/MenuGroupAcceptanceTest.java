package kitchenpos.ui;

import io.restassured.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.domain.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("전체 메뉴 그룹을 조회한다.")
    void findAllMenuGroup() {
        // given
        메뉴_그룹_생성을_요청(new MenuGroup("추천메뉴"));
        메뉴_그룹_생성을_요청(new MenuGroup("추천메뉴1"));

        // when
        final ExtractableResponse<Response> 전체_메뉴_그룹_조회를_요청_응답 = 전체_메뉴_그룹_조회를_요청();

        // then
        전체_메뉴_그룹_조회됨(전체_메뉴_그룹_조회를_요청_응답);

    }

    private void 전체_메뉴_그룹_조회됨(final ExtractableResponse<Response> response) {
        final List<MenuGroup> body = response.as(new TypeRef<List<MenuGroup>>() {
        });
        final List<String> menuGroupNames = body.stream()
                .map(MenuGroup::getName)
                .collect(Collectors.toList());

        assertThat(menuGroupNames).contains("추천메뉴", "추천메뉴1");
    }

    private ExtractableResponse<Response> 전체_메뉴_그룹_조회를_요청() {
        return get("/api/menu-groups");
    }
}
