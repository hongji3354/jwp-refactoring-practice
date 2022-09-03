package kitchenpos.domain;

public class MenuGroup {
    private Long id;
    private String name;

    public MenuGroup() {
    }

    public MenuGroup(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("메뉴 그룹의 이름이 존재해야 합니다.");
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
