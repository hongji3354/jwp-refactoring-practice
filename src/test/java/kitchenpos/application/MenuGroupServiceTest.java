package kitchenpos.application;

import kitchenpos.dao.MenuGroupDao;
import kitchenpos.domain.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MenuGroupServiceTest {

    @Mock
    private MenuGroupDao menuGroupDao;

    @InjectMocks
    private MenuGroupService menuGroupService;

    @Test
    @DisplayName("메뉴 그룹을 생성한다.")
    void createMenuGroup() {
        // given
        Mockito.when(menuGroupDao.save(any()))
                .thenReturn(new MenuGroup(1L, "추천메뉴"));

        // when
        final MenuGroup createMenuGroup = menuGroupService.create(new MenuGroup("추천메뉴"));

        // then
        assertEquals(1L, createMenuGroup.getId());
        assertEquals("추천메뉴", createMenuGroup.getName());
    }
}
