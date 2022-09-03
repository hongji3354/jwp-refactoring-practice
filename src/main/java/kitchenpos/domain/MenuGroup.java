package kitchenpos.domain;

public class MenuGroup {
    private Long id;
    private String name;

    public MenuGroup(final Long id, final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("메뉴 그룹의 이름이 존재해야 합니다.");
        }
        this.id = id;
        this.name = name;
    }

    public MenuGroup(final String name) {
        this(null, name);
    }

    public MenuGroup() {
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
