package kitchenpos.ui;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuGroup;
import kitchenpos.domain.MenuProduct;
import kitchenpos.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuAcceptanceTest extends AcceptanceTest {

    private MenuGroup menuGroup;
    private Product product;


    @BeforeEach
    void setUp() {
        menuGroup = 메뉴_그룹_생성을_요청();
        product = 상품_생성을_요청();
    }

    @Test
    @DisplayName("메뉴를 생성한다.")
    void createMenu() {
        // given
        final MenuProduct menuProduct = new MenuProduct(product.getId(), 2);
        final Menu menu = new Menu("후라이드+후라이드", BigDecimal.valueOf(19000), menuGroup.getId(), Arrays.asList(menuProduct));

        // when
        final ExtractableResponse<Response> 메뉴_생성_응답 = 메뉴_생성을_요청(menu);

        // then
        메뉴_생성됨(메뉴_생성_응답);
    }

    private void 메뉴_생성됨(final ExtractableResponse<Response> 메뉴_생성_응답) {
        final Menu 메뉴 = 메뉴_생성_응답.as(Menu.class);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED.value(), 메뉴_생성_응답.statusCode()),
                () -> assertNotNull(메뉴.getId()),
                () -> assertEquals("후라이드+후라이드", 메뉴.getName()),
                () -> assertThat(메뉴.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(19000)),
                () -> assertEquals(menuGroup.getId(), 메뉴.getMenuGroupId()),
                () -> assertThat(메뉴.getMenuProducts()).extracting("productId", "quantity").contains(tuple(product.getId(), 2L))
        );
    }

    private ExtractableResponse<Response> 메뉴_생성을_요청(final Menu menu) {
        return post("/api/menus", menu);
    }

    private Product 상품_생성을_요청() {
        final ExtractableResponse<Response> response = post("/api/products", new Product("후라이드", BigDecimal.valueOf(17000)));
        return response.as(Product.class);
    }

    private MenuGroup 메뉴_그룹_생성을_요청() {
        final ExtractableResponse<Response> response = post("/api/menu-groups", new MenuGroup("추천메뉴"));
        return response.as(MenuGroup.class);
    }
}
